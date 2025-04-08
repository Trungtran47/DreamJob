package com.example.dreamjob.service;

import com.example.dreamjob.dto.UserDTO;
import com.example.dreamjob.dto.response.UserCv;
import com.example.dreamjob.dto.response.UserLogin;
import com.example.dreamjob.entity.CompanyEntity;
import com.example.dreamjob.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    UserEntity register(UserEntity user);
    UserLogin login(UserDTO user);
    UserEntity updateEmployer(Long id, UserEntity user, MultipartFile image);
    UserDTO getUserById(Long id);
    void deleteUser(Long id);
    UserDTO updateApplicant(Long id, UserDTO user, MultipartFile image);

    CompanyEntity createEmployer(CompanyEntity employer);
    UserCv updateCv(Long userId, MultipartFile cv);
    void deleteCv(Long userIdm );
    // login admin
    UserLogin LoginAdmin(UserDTO userDTO);
    List<UserDTO> getAllUsers();
}
