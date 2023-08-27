package com.igr.media.repository;

import com.igr.media.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Collection;
/**
 * Репозиторий для Поста
 */
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query(nativeQuery = true, value = "SELECT MAX(ID) FROM posts")
    int findMaxID();
    @Query(nativeQuery = true, value = "CREATE PROCEDURE  @data TIMESTAMP" +
            "AS SELECT data FROM posts WHERE data > '%@data%'")
    Collection<Post> findAllNew(LocalDateTime data);
    @Query(nativeQuery = true, value = "SELECT * FROM posts " +
            " JOIN post_reading ON posts.Id = post_reading.post_id " +
            " WHERE post_reading.user_id = :userId ORDER BY data")
    Collection<Post> getAllPostsNew(@Param("userId") int userId);
    @Query(nativeQuery = true, value = "SELECT * FROM posts " +
            " JOIN friends ON posts.authorId = friends.user_id2 " +
            "JOIN post_reading ON posts.Id = post_reading.post_id" +
            " WHERE friends.user_id1 = :userId  AND NO post_reading.user_id = :userId ORDER BY data")
    Collection<Post> getAllPostsNewSubscriptions(@Param("userId") int userId);

}
