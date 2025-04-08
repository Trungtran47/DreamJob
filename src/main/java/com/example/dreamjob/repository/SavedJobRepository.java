package com.example.dreamjob.repository;

import com.example.dreamjob.entity.SavedJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavedJobRepository extends JpaRepository<SavedJobEntity, Long> {
    List<SavedJobEntity> findAllByUserId(Long userId);
    void deleteByPost_PostIdAndUserId(Long userId, Long postId);
    SavedJobEntity findByPost_PostIdAndUserId( Long userId, Long postId);
}
