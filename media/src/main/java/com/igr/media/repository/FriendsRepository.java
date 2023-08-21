package com.igr.media.repository;

import com.igr.media.entity.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Репозиторий для друзей
 */
@Repository
public interface FriendsRepository extends JpaRepository<Friends, Integer> {
    Optional<Friends> findByName(String Name);
}
