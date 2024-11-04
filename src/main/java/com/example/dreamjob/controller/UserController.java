package com.example.dreamjob.controller;

import com.example.dreamjob.dto.UserDTO;
import com.example.dreamjob.dto.response.UserCv;
import com.example.dreamjob.dto.response.UserLogin;
import com.example.dreamjob.entity.CompanyEntity;
import com.example.dreamjob.entity.UserEntity;
import com.example.dreamjob.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    private UserService userService;
    private static final String UPLOAD_DIR = "uploads/";
    @PostMapping(value="/register",consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> register(@RequestBody UserEntity user) {
        try {
            UserEntity createdUser = userService.register(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO user) {
        try {
            UserLogin existingUser = userService.login(user);  // lấy đối tượng từ login
            return ResponseEntity.status(HttpStatus.OK).body(existingUser);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        try {
            UserDTO createdUser =   userService.getUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body(createdUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register/employer")
    public ResponseEntity<?> registerEmployer(@RequestBody CompanyEntity employer) {
        try {
            CompanyEntity companyEntity = userService.createEmployer(employer);
            return ResponseEntity.status(HttpStatus.CREATED).body(companyEntity);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping(value = "/{userId}/update_employer", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateEmployer(@PathVariable Long userId,
                                          @RequestParam(value="username") String username,
                                          @RequestParam(value="fullName") String fullName,
                                          @RequestParam(value="phone") int phone,
                                          @RequestParam(value="image", required = false) MultipartFile image) {
        try {
            UserEntity userEn = new UserEntity();
            userEn.setUsername(username);
            userEn.setFullName(fullName);
            userEn.setPhone(phone);

            if (image == null || image.isEmpty()) {
                userEn.setAvatar("0");
            }
            // Cập nhật user với ảnh mới
            userService.updateUser(userId, userEn, image);
            return ResponseEntity.ok().body(userEn);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi tải ảnh lên: " + e.getMessage());
        }
    }
    @PostMapping(value = "/{userId}/update_applicant", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateApplicant(@PathVariable Long userId,
                                          @RequestParam(value="username") String username,
                                          @RequestParam(value="fullName") String fullName,
                                          @RequestParam(value="experience") String experience,
                                          @RequestParam(value= "desiredJob") String desiredJob,
                                          @RequestParam(value="location") String location,
                                          @RequestParam(value="phone") int phone,
                                          @RequestParam(value="image", required = false) MultipartFile image) {
        try {
            UserDTO userEntity = new UserDTO();
            userEntity.setUsername(username);
            userEntity.setFullName(fullName);
            userEntity.setPhone(phone);
            userEntity.setExperience(experience);
            userEntity.setDesiredJob(desiredJob);
            userEntity.setLocation(location);
            if (image == null || image.isEmpty()) {
                userEntity.setAvatar("0");
            }
            // Cập nhật user với ảnh mới
            userService.updateApplicant(userId, userEntity, image);
            return ResponseEntity.ok().body(userEntity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi tải ảnh lên: " + e.getMessage());
        }
    }
    @PostMapping("/update_cv/{userId}/cv")
    public ResponseEntity<?> uploadCv(@PathVariable Long userId, @RequestParam(value="cv", required = false) MultipartFile cv){
        try {
            if (cv == null || cv.isEmpty()) {
                throw new IllegalArgumentException("CV file is required.");
            }
            UserCv userCv = userService.updateCv(userId, cv);
            return ResponseEntity.ok().body(userCv);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete_cv/{userId}/cv")
    public ResponseEntity<?> deleteCv(@PathVariable Long userId){
        try {
            userService.deleteCv(userId);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
