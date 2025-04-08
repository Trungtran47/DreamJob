package com.example.dreamjob.dto.response;
import com.example.dreamjob.entity.CompanyEntity;
import com.example.dreamjob.entity.PostEntity;
import com.example.dreamjob.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostWithCompanyDTO {
    private Long postId;
    private String title;
    private String salary;
    private String location;
    private String experience;
    private String employmentType;
    private Long vacancies; // Số lượng tuyển
    private String gender; // Giới tính yêu cầu
    private String level; // Cấp bậc (nhân viên, trưởng phòng, v.v.)
    private String jobDescription; // Mô tả công việc
    private String applicationRequirements; // Yêu cầu ứng tuyển
    private String benefits; // Quyền lợi
    private String workLocation; // Địa điểm làm việc
    private String workingHours; // Thời gian làm việc
    private LocalDateTime postedDate; // Ngày đăng bài viết
    private LocalDateTime expirationDate; // Ngày hết hạn bài viết

    // Thông tin từ UserEntity
    private Long userId;
    private String username;

    // Thông tin từ CompanyEntity
    private Long companyId;
    private String companyName;
    private String companyIntroduce;
    private String companyLocation;
    private String companyWebsite;
    private String companySize;
    private String companyCategory;
    private String companyLogo;

    // Constructor để tạo DTO từ PostEntity
    public PostWithCompanyDTO(PostEntity post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.salary = post.getSalary();
        this.location = post.getLocation();
        this.experience = post.getExperience();
        this.employmentType = post.getEmploymentType();
        this.vacancies = post.getVacancies();
        this.gender = post.getGender();
        this.level = post.getLevel();
        this.jobDescription = post.getJobDescription();
        this.applicationRequirements = post.getApplicationRequirements();
        this.benefits = post.getBenefits();
        this.workLocation = post.getWorkLocation();
        this.workingHours = post.getWorkingHours();
        this.postedDate = post.getPostedDate();
        this.expirationDate = post.getExpirationDate();


        // Lấy thông tin từ UserEntity
        UserEntity user = post.getUser();
        if (user != null) {
            this.userId = user.getUserId();
            this.username = user.getUsername();

            // Lấy thông tin từ CompanyEntity thông qua UserEntity
            CompanyEntity company = user.getCompany();
            if (company != null) {
                this.companyId = company.getCompanyId();
                this.companyName = company.getCompanyName();
                this.companyIntroduce = company.getCompanyIntroduce();
                this.companyLocation = company.getCompanyLocation();
                this.companyWebsite = company.getCompanyWebsite();
                this.companySize = company.getCompanySize();
                this.companyCategory = company.getCompanyCategory();
                this.companyLogo = company.getCompanyLogo();
            }
        }
    }
}
