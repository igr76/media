package com.igr.media.repository;

import com.igr.media.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
    @Query(nativeQuery = true, value = "SELECT * FROM posts LEFT JOIN user_subscriptions ON posts_authorId = user_subscriptions_index ORDER BY data")
    Collection<Post> getAllPostsNewSubscriptions();

}
