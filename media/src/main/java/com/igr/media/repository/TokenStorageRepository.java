package com.igr.media.repository;

import com.igr.media.entity.TokenStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenStorageRepository extends JpaRepository<TokenStorage,String> {
}
