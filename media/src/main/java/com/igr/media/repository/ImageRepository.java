package com.igr.media.repository;

import com.igr.media.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Репозиторий для фото
 */
@Repository
@Transactional
public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {
}
