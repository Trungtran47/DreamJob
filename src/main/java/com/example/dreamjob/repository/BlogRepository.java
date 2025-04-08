package com.example.dreamjob.repository;

import com.example.dreamjob.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Long> {
    List<BlogEntity> findByUser_UserIdOrderByTimeDesc(Long userId);
//    List<BlogEntity> findAllOrderByTimeDesc();
//    List<BlogEntity> findAllOrderByTimeDesc();
    List<BlogEntity> findByStatusOrderByTimeDesc(int status);
    List<BlogEntity> findAllByOrderByTimeDesc();


    // Tổng số blog
    @Query("SELECT COUNT(b) FROM BlogEntity b")
    long countAllBlogs();
}
