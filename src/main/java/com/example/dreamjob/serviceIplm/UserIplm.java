package com.example.dreamjob.serviceIplm;

import com.example.dreamjob.dto.PostDTO;
import com.example.dreamjob.dto.UserDTO;
import com.example.dreamjob.dto.response.UserCv;
import com.example.dreamjob.dto.response.UserLogin;
import com.example.dreamjob.entity.CompanyEntity;
import com.example.dreamjob.entity.UserEntity;
import com.example.dreamjob.mapp.PostMapper;
//import com.example.dreamjob.mapp.PostMapperImpl;
import com.example.dreamjob.mapp.UserMapper;
import com.example.dreamjob.repository.CompanyRepository;
import com.example.dreamjob.repository.UserRepository;
import com.example.dreamjob.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserIplm implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private final UserMapper userMapper;


    @Override
    public UserLogin LoginAdmin(UserDTO user) {
        UserEntity existingUser = userRepository.findUserEntitiesByEmail(user.getEmail());

        if (existingUser == null || existingUser.getRoles() != 3) {
            throw new RuntimeException("Email không tồn tại hoặc không được phép đăng nhập.");
        }
        if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            throw new RuntimeException("Mật khẩu không đúng");
        }
        System.out.println("Đăng nhập thành công");

        return userMapper.UserEntityToUserLogin(existingUser);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        try {
            List<UserEntity> users = userRepository.findAll();
            return userMapper.UsersToUserDTOs(users);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


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
        }
        if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            throw new RuntimeException("Mật khẩu không đúng");
        }
        System.out.println("Đăng nhập thành công");

        return userMapper.UserEntityToUserLogin(existingUser);

    }
    @Override
    public UserEntity updateEmployer(Long id, UserEntity user, MultipartFile image) {
        try {
            UserEntity existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User không tồn tại."));
            // Kiểm tra và xử lý ảnh
            if (image != null && !image.isEmpty()) {
                // Xóa ảnh cũ nếu có
                String existingAvatar = existingUser.getAvatar();
                if (existingAvatar != null) {
                    Path existingAvatarPath = Paths.get("uploads/" + existingAvatar);
                    if (Files.exists(existingAvatarPath)) {
                        Files.delete(existingAvatarPath);
                    }
                }
                // Lưu ảnh mới
                String fileName = id + "_" + image.getOriginalFilename();
                Path imagePath = Paths.get("uploads/" + fileName);
                Files.createDirectories(imagePath.getParent());
                Files.write(imagePath, image.getBytes());
                existingUser.setAvatar(fileName);  // Cập nhật đường dẫn ảnh
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDTO getUserById(Long id) {
        try {
            return userMapper.UserToUserDTO(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User không tồn tại.")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
    @Override
    public UserDTO updateApplicant(Long id, UserDTO user, MultipartFile image) {
        try {
            UserEntity userEntity  = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User không tồn tại."));
            // Cập nhật các thông tin khác
            // Kiểm tra và xử lý ảnh
            if (image != null && !image.isEmpty()) {
                // Xóa ảnh cũ nếu có
                String existingAvatar = userEntity.getAvatar();
                if (existingAvatar != null) {
                    Path existingAvatarPath = Paths.get("uploads/" + existingAvatar);
                    if (Files.exists(existingAvatarPath)) {
                        Files.delete(existingAvatarPath);
                    }
                }
                // Lưu ảnh mới
                String fileName = id + "_" + image.getOriginalFilename();
                Path imagePath = Paths.get("uploads/" + fileName);
                Files.createDirectories(imagePath.getParent());
                Files.write(imagePath, image.getBytes());
                userEntity.setAvatar(fileName);  // Cập nhật đường dẫn ảnh
            }
            userEntity.setFullName(user.getFullName());
            userEntity.setPhone(user.getPhone());
            userEntity.setUserId(userEntity.getUserId());
            userEntity.setEmail(userEntity.getEmail());
            userEntity.setPassword(userEntity.getPassword());
            userEntity.setUsername(user.getUsername());
            userEntity.setRoles(userEntity.getRoles());
            userEntity.setPost(userEntity.getPost());
            userEntity.setExperience(user.getExperience());
            userEntity.setDesiredJob(user.getDesiredJob());
            userEntity.setLocation(user.getLocation());
            userEntity.setCv(userEntity.getCv());
            return userMapper.UserToUserDTO(userRepository.save(userEntity));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void deleteUser(Long id) {
       userRepository.deleteById(id);
    }



    @Override
    public CompanyEntity createEmployer(CompanyEntity employer) {
        return companyRepository.save(employer);

    }

    @Override
    public UserCv updateCv(Long userId, MultipartFile cv) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User không tồn tại."));
        try {
            if (cv != null && !cv.isEmpty()) {
                // Xóa CV hiện tại nếu đã tồn tại
                String existingCvFileName = userEntity.getCv();
                if (existingCvFileName != null) {
                    Path existingCvPath = Paths.get("uploads/Cv/" + existingCvFileName);
                    if (Files.exists(existingCvPath)) {
                        try {
                            Files.delete(existingCvPath); // Xóa file CV hiện tại
                        } catch (DirectoryNotEmptyException e) {
                            System.err.println("Thư mục không rỗng: " + e.getMessage());
                        } catch (IOException e) {
                            System.err.println("Không thể xóa file: " + e.getMessage());
                        }
                    }
                }
                // Lưu CV mới
                String originalFileName = cv.getOriginalFilename();
                String fileName = userId + "_" + originalFileName; // Tạo tên file duy nhất
                Path imagePath = Paths.get("uploads/Cv/" + fileName);
                Files.createDirectories(imagePath.getParent()); // Tạo thư mục nếu chưa có
                Files.write(imagePath, cv.getBytes()); // Lưu file mới
                userEntity.setCv(fileName); // Cập nhật đường dẫn file mới vào user
            }

            return userMapper.UserEntityToUserCv(userRepository.save(userEntity));
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi cập nhật CV: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteCv(Long userId ) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow();
            try {
                String existingCvFileName = userEntity.getCv();
                if (existingCvFileName != null) {
                    Path existingCvPath = Paths.get("uploads/Cv/" + existingCvFileName);
                    if (Files.exists(existingCvPath)) {
                        try {
                            Files.delete(existingCvPath); // Xóa file CV hiện tại
                        } catch (DirectoryNotEmptyException e) {
                            System.err.println("Thư mục không rỗng: " + e.getMessage());
                        } catch (IOException e) {
                            System.err.println("Không thể xóa file: " + e.getMessage());
                        }
                    }
                }
                userEntity.setCv(null);
                userRepository.save(userEntity);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }


}
