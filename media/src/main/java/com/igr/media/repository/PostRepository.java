package com.igr.media.repository;

import com.igr.media.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query(nativeQuery = true, value = "SELECT MAX(ID) FROM posts")
    int findMaxID();
}
