package com.igr.media.repository;

import com.igr.media.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
/**
 * Репозиторий для друзей
 */
@Repository
public interface FriendsRepository extends JpaRepository<Friend, Integer> {
    @Query(nativeQuery = true, value = "DELETE FROM posts WHERE user1 = :user1")
    void deleteAllByUser1(@Param("user1") int user1);
    Optional<Friend> findByUser1AndUser2(int user1,int user2);
}
