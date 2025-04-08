package com.example.dreamjob.repository;

import com.example.dreamjob.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    UserEntity findUserEntitiesByEmail(String email);

    // Tổng số người dùng
    @Query("SELECT COUNT(u) FROM UserEntity u")
    Long countAllUsers();
    // Tổng số người dùng có role = 1
    @Query("SELECT COUNT(u) FROM UserEntity u WHERE u.roles = 1")
    Long countUsersWithRole1();
}
