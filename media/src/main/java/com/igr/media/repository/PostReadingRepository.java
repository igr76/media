package com.igr.media.repository;

import com.igr.media.entity.PostReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostReadingRepository extends JpaRepository<PostReading,Integer> {
    @Query(nativeQuery = true, value = "DELETE FROM post_reading WHERE user_id = :user")
    void deleteAllByUser_id(@Param("user") int user1);
    @Query(nativeQuery = true, value = "DELETE FROM post_reading WHERE post_id = :post")
    void deleteAllByPost_id(@Param("post") int user1);
}
