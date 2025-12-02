package com.verdeemcasa.api.Application.Controllers;

import com.verdeemcasa.api.Application.DTOs.PostCommentRequestDto;
import com.verdeemcasa.api.Application.DTOs.PostCommentResponseDto;
import com.verdeemcasa.api.Domain.Services.PostCommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/community/comments")
public class PostCommentController {

    private final PostCommentService commentService;

    public PostCommentController(PostCommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<PostCommentResponseDto>> getCommentsByPost(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPost(postId));
    }

    @PostMapping("/post/{postId}/user/{userId}")
    public ResponseEntity<PostCommentResponseDto> addComment(
            @PathVariable Long postId,
            @PathVariable Long userId,
            @RequestBody @Valid PostCommentRequestDto data,
            UriComponentsBuilder uriBuilder
    ) {
        var comment = commentService.addComment(userId, postId, data);
        var uri = uriBuilder.path("/community/comments/{id}").buildAndExpand(comment.id()).toUri();
        return ResponseEntity.created(uri).body(comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}