package com.example.dreamjob.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter
@Setter
@Table(name= "app_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Long userId;

    @Column(name = "username",columnDefinition = "nvarchar(50)", nullable = false)
    String username;
    @Column(nullable = false)
    String password;
    @Column(nullable = false)
    String email;
    @Column(name = "full_name", columnDefinition = "nvarchar(255)", nullable = true)
    String fullName;
    @Column(nullable = true, length = 15)
    int phone;
    @Column(name = "file_cv",columnDefinition = "nvarchar(255)",nullable = true)
    String cv;
    @Column(name = "user_experience",columnDefinition = "nvarchar(255)",nullable = true)
    String experience;
    @Column(name= "user_location",columnDefinition = "nvarchar(255)",nullable = true)
    String location;
    @Column(name= "desired_job",columnDefinition = "nvarchar(255)", nullable = true)
    String desiredJob;
    @Column(name="roles", nullable = true)
    Long roles;
    @Column(name="avatar", nullable = true)
    String avatar;

//    @JsonBackReference
//    @OneToOne
//    @JoinColumn(name = "message_Id", nullable = true)
//    private MessageEntity employer; // Trường này ánh xạ tới message_Id
//
//    @JsonBackReference
//    @OneToOne
//    @JoinColumn(name = "message_Id", nullable = true)
//    private MessageEntity applicant; // Trường này ánh xạ tới message_Id
    @JsonManagedReference("user_post")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<PostEntity> post = new ArrayList<>();

    @JsonManagedReference("user_company")
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private CompanyEntity company;

    @JsonManagedReference("user_blog")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BlogEntity> blog;


    @JsonManagedReference("user_message")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MessageEntity> message;

}
