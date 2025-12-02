package com.verdeemcasa.api.Domain.Services;

import com.verdeemcasa.api.Application.DTOs.CommunityPostRequestDto;
import com.verdeemcasa.api.Application.DTOs.CommunityPostResponseDto;
import com.verdeemcasa.api.Domain.Models.CommunityPost;
import com.verdeemcasa.api.Domain.Models.Enums.PostCategory;
import com.verdeemcasa.api.Domain.Models.User;
import com.verdeemcasa.api.Domain.Repositories.CommunityPostRepository;
import com.verdeemcasa.api.Domain.Repositories.UserRepository;
import com.verdeemcasa.api.Infra.Mapper.CommunityPostMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommunityPostService {

    private final CommunityPostRepository postRepository;
    private final UserRepository userRepository;
    private final CommunityPostMapper postMapper;

    public CommunityPostService(CommunityPostRepository postRepository, UserRepository userRepository, CommunityPostMapper postMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postMapper = postMapper;
    }

    @Transactional
    public CommunityPostResponseDto createPost(Long userId, CommunityPostRequestDto dto) {
        User author = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CommunityPost post = postMapper.toEntity(dto);
        post.setAuthor(author);

        return postMapper.toResponse(postRepository.save(post));
    }

    public List<CommunityPostResponseDto> getAllRecent() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(postMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<CommunityPostResponseDto> getByCategory(PostCategory category) {
        return postRepository.findByCategoryOrderByCreatedAtDesc(category).stream()
                .map(postMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void likePost(Long postId) {
        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        post.setLikes(post.getLikes() + 1);
        postRepository.save(post);
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}