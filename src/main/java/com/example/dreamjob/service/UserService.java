package com.example.dreamjob.service;

import com.example.dreamjob.dto.UserDTO;
import com.example.dreamjob.dto.response.UserLogin;
import com.example.dreamjob.entity.CompanyEntity;
import com.example.dreamjob.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    UserEntity register(UserEntity user);
    UserLogin login(UserDTO user);
    UserEntity updateUser(Long id, UserEntity user, MultipartFile image);
    UserEntity getUserByEmail(String email);
    UserEntity getUserById(Long id);
    List<UserEntity> getAllUsers();
    void deleteUser(Long id);

    CompanyEntity createEmployer(CompanyEntity employer);


}
