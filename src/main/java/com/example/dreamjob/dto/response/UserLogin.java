package com.example.dreamjob.dto.response;

import com.example.dreamjob.dto.CompanyDTO;
import com.example.dreamjob.dto.PostDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLogin {
    private Long userId;
    private String username;
    private String email;
    private String fullName;
    private int phone;
    private String cv; // Sử dụng chữ thường cho tên thuộc tính
    private String experience;
    private String location;
    private String desiredJob; // Sử dụng underscore để nối từ
    private Long roles;
    private String avatar;
}
