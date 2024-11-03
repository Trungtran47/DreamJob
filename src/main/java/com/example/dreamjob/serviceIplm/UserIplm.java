package com.example.dreamjob.serviceIplm;

import com.example.dreamjob.dto.PostDTO;
import com.example.dreamjob.dto.UserDTO;
import com.example.dreamjob.dto.response.UserLogin;
import com.example.dreamjob.entity.CompanyEntity;
import com.example.dreamjob.entity.UserEntity;
import com.example.dreamjob.mapp.PostMapper;
import com.example.dreamjob.mapp.PostMapperImpl;
import com.example.dreamjob.mapp.UserMapper;
import com.example.dreamjob.repository.CompanyRepository;
import com.example.dreamjob.repository.UserRepository;
import com.example.dreamjob.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class UserIplm implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserEntity register(UserEntity user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email đã được dùng.");
        }
        // Mã hóa mật khẩu trước khi lưu
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }
    @Override
    public UserLogin login(UserDTO user) {
        UserEntity existingUser = userRepository.findUserEntitiesByEmail(user.getEmail());
        if (existingUser == null) {
            throw new RuntimeException("Email không tồn tại.");
        } else {
            if (passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
                System.out.println("Đăng nhập thành công");
            } else {
                throw new RuntimeException("Mật khẩu không đúng");
            }
        }

        // Đảm bảo rằng existingUser không phải là null trước khi ánh xạ
        UserLogin userLogin = userMapper.UserEntityToUserLogin(existingUser);
        System.out.println("Thông tin UserLogin: " + userLogin);
        return userLogin;
    }

    @Override
    public UserEntity updateUser(Long id, UserEntity user, MultipartFile image) {
        UserEntity existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User không tồn tại."));
        if (image != null && !image.isEmpty()) {
            String fileName = id + "_" + image.getOriginalFilename();
            Path imagePath = Paths.get("uploads/" + fileName);
            try {
                Files.createDirectories(imagePath.getParent());
                Files.write(imagePath, image.getBytes());
                existingUser.setAvatar(fileName);  // Cập nhật đường dẫn ảnh
            } catch (IOException e) {
                throw new RuntimeException("Lỗi khi lưu ảnh"+ e.getMessage(), e);
            }
        }
        // Cập nhật các thông tin khác
        existingUser.setFullName(user.getFullName());
        existingUser.setPhone(user.getPhone());
        existingUser.setUserId(existingUser.getUserId());
        existingUser.setEmail(existingUser.getEmail());
        existingUser.setPassword(existingUser.getPassword());
        existingUser.setUsername(user.getUsername());
        existingUser.setRoles(existingUser.getRoles());
        existingUser.setPost(existingUser.getPost());

        return userRepository.save(existingUser);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return null;
    }

    @Override
    public UserEntity getUserById(Long id) {
        try {
            return userRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
       userRepository.deleteById(id);
    }

    @Override
    public CompanyEntity createEmployer(CompanyEntity employer) {
        return companyRepository.save(employer);

    }
}
