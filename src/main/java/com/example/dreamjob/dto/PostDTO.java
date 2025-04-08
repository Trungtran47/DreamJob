package com.example.dreamjob.dto;

import com.example.dreamjob.entity.UserEntity;
import com.example.dreamjob.entity.CompanyEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {
    private Long postId;
    private String title;
    private String salary;
    private String location;
    private String experience;
    private String employmentType;
    private Long vacancies;
    private String gender;
    private String level;
    private String jobDescription;
    private String applicationRequirements;
    private String benefits;
    private String workLocation;
    private String workingHours;
    private LocalDateTime postedDate;
    private LocalDateTime expirationDate;
    private UserDTO user; // DTO cho UserEntity
//    private CompanyDTO company; // DTO cho CompanyEntity (nếu cần)

    // Bạn có thể thêm các phương thức chuyển đổi từ PostEntity nếu cần
}
