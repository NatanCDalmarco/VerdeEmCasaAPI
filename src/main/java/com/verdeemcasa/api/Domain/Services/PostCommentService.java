package com.verdeemcasa.api.Domain.Services;

import com.verdeemcasa.api.Application.DTOs.PostCommentRequestDto;
import com.verdeemcasa.api.Application.DTOs.PostCommentResponseDto;
import com.verdeemcasa.api.Domain.Models.CommunityPost;
import com.verdeemcasa.api.Domain.Models.PostComment;
import com.verdeemcasa.api.Domain.Models.User;
import com.verdeemcasa.api.Domain.Repositories.CommunityPostRepository;
import com.verdeemcasa.api.Domain.Repositories.PostCommentRepository;
import com.verdeemcasa.api.Domain.Repositories.UserRepository;
import com.verdeemcasa.api.Infra.Mapper.PostCommentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostCommentService {

    private final PostCommentRepository commentRepository;
    private final CommunityPostRepository postRepository;
    private final UserRepository userRepository;
    private final PostCommentMapper commentMapper;

    public PostCommentService(PostCommentRepository commentRepository, CommunityPostRepository postRepository, UserRepository userRepository, PostCommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentMapper = commentMapper;
    }

    @Transactional
    public PostCommentResponseDto addComment(Long userId, Long postId, PostCommentRequestDto dto) {
        User author = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        PostComment comment = commentMapper.toEntity(dto);
        comment.setAuthor(author);
        comment.setPost(post);

        return commentMapper.toResponse(commentRepository.save(comment));
    }

    public List<PostCommentResponseDto> getCommentsByPost(Long postId) {
        return commentRepository.findByPostIdOrderByCreatedAtAsc(postId).stream()
                .map(commentMapper::toResponse)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}