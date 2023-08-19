package com.igr.media.repository;
import com.igr.media.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для пользователей
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {


  Optional<UserEntity> findByEmail(String email);


}
