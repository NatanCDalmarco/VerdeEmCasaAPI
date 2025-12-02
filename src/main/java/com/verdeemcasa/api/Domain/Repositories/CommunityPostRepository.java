package com.verdeemcasa.api.Domain.Repositories;

import com.verdeemcasa.api.Domain.Models.CommunityPost;
import com.verdeemcasa.api.Domain.Models.Enums.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityPostRepository extends JpaRepository<CommunityPost, Long> {

    List<CommunityPost> findAllByOrderByCreatedAtDesc();

    List<CommunityPost> findByCategoryOrderByCreatedAtDesc(PostCategory category);

    List<CommunityPost> findByAuthorIdOrderByCreatedAtDesc(Long authorId);
}