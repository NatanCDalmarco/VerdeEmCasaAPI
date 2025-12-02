package com.verdeemcasa.api.Application.Controllers;

import com.verdeemcasa.api.Application.DTOs.CommunityPostRequestDto;
import com.verdeemcasa.api.Application.DTOs.CommunityPostResponseDto;
import com.verdeemcasa.api.Domain.Models.Enums.PostCategory;
import com.verdeemcasa.api.Domain.Services.CommunityPostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/community/posts")
public class CommunityPostController {

    private final CommunityPostService postService;

    public CommunityPostController(CommunityPostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<CommunityPostResponseDto>> getRecentPosts() {
        return ResponseEntity.ok(postService.getAllRecent());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<CommunityPostResponseDto>> getByCategory(@PathVariable PostCategory category) {
        return ResponseEntity.ok(postService.getByCategory(category));
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<CommunityPostResponseDto> createPost(
            @PathVariable Long userId,
            @RequestBody @Valid CommunityPostRequestDto data,
            UriComponentsBuilder uriBuilder
    ) {
        var post = postService.createPost(userId, data);
        var uri = uriBuilder.path("/community/posts/{id}").buildAndExpand(post.id()).toUri();
        return ResponseEntity.created(uri).body(post);
    }

    @PatchMapping("/{id}/like")
    public ResponseEntity<Void> likePost(@PathVariable Long id) {
        postService.likePost(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}