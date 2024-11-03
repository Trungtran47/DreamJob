package com.example.dreamjob.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long userId;
    private String username;
    private String password; // Nếu không cần thiết, có thể bỏ qua trong DTO
    private String email;
    private String fullName;
    private int phone;
    private String cv; // Sử dụng chữ thường cho tên thuộc tính
    private String experience;
    private String location;
    private String desiredJob; // Sử dụng underscore để nối từ
    private Long roles;
    private String avatar;
    private List<PostDTO> posts; // Danh sách bài viết liên quan đến người dùng
    private CompanyDTO company; // Thông tin công ty liên quan (nếu cần)
}
