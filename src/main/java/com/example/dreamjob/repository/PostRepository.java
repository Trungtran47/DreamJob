package com.example.dreamjob.repository;

import com.example.dreamjob.entity.PostEntity;
import com.example.dreamjob.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long>{
    List<PostEntity> findByLocation(String title);
    List<PostEntity> findByUser_UserId(Long userId);
    List<PostEntity> findBySaved_UserId(Long userId);


    @Query("SELECT COUNT(p) FROM PostEntity p")
    Long countTotalPosts();
}
