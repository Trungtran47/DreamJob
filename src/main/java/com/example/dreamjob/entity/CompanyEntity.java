package com.example.dreamjob.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="app_company")
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long companyId;
    @Column(name = "company_Name", columnDefinition = "nvarchar(255)", nullable = true)
    private String companyName;
    @Column(name = "company_Introduce", columnDefinition = "NVARCHAR(MAX)", nullable = true)
    private String companyIntroduce;
    @Column(name = "company_Location", columnDefinition = "nvarchar(255)", nullable = true)
    private String companyLocation;
    @Column(name = "company_Website", columnDefinition = "nvarchar(255)", nullable = true)
    private String companyWebsite;
    @Column(name = "company_Size", columnDefinition = "nvarchar(255)", nullable = true)
    private String companySize;
    @Column(name = "company_Logo",nullable = true)
    private String companyLogo;



    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "user_Id", nullable = true) // Tạo cột khóa ngoại user_id trong bảng Company
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "message_id",nullable = true) // tên cột khóa ngoại
    private MessageEntity message; // trường này phải tồn tại


}
