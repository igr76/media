package com.igr.media.repository;

import com.igr.media.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Репозиторий для друзей
 */
@Repository
public interface FriendsRepository extends JpaRepository<Friend, Integer> {
    Optional<Friend> findByName(String Name);
}
