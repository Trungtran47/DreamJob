package com.example.dreamjob.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "app_post")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_Id")
    private Long postId;


    @ManyToOne
    @JoinColumn(name = "user_Id", nullable = true)
    @JsonBackReference
    private UserEntity user;

//    @JsonBackReference
//    @ManyToOne
//    @JoinColumn(name="company_Id", nullable = false)
//    private CompanyEntity company;

    @Column(name = "title", columnDefinition = "nvarchar(255)")
    private String title; // Tiêu đề bài đăng

    @Column(name = "salary", columnDefinition = "nvarchar(255)")
    private String salary; // Mức lương

    @Column(name = "location", columnDefinition = "nvarchar(255)")
    private String location; // Địa điểm

    @Column(name = "experience", columnDefinition = "nvarchar(255)")
    private String experience; // Kinh nghiệm

    @Column(name = "employment_type", columnDefinition = "nvarchar(255)")
    private String employmentType; // Hình thức (toàn thời gian, bán thời gian, thực tập, v.v.)

    @Column(name = "vacancies")
    private Long vacancies; // Số lượng tuyển

    @Column(name = "gender", columnDefinition = "nvarchar(50)")
    private String gender; // Giới tính yêu cầu

    @Column(name = "level", columnDefinition = "nvarchar(255)")
    private String level; // Cấp bậc (nhân viên, trưởng phòng, v.v.)

    @Column(name = "job_description", columnDefinition = "NVARCHAR(MAX)")
    private String jobDescription; // Mô tả công việc

    @Column(name = "application_requirements", columnDefinition = "NVARCHAR(MAX)")
    private String applicationRequirements; // Yêu cầu ứng tuyển

    @Column(name = "benefits", columnDefinition = "NVARCHAR(MAX)")
    private String benefits; // Quyền lợi

    @Column(name = "work_location", columnDefinition = "nvarchar(255)")
    private String workLocation; // Địa điểm làm việc

    @Column(name = "working_hours", columnDefinition = "nvarchar(255)")
    private String workingHours; // Thời gian làm việc

    @Column(name = "posted_date")
    private LocalDateTime postedDate; // Ngày đăng bài viết

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate; // Ngày hết hạn bài viết
}
