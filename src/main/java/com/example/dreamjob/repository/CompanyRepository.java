package com.example.dreamjob.repository;

import com.example.dreamjob.entity.CompanyEntity;
import com.example.dreamjob.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    CompanyEntity findByUser_UserId(Long userId);

}