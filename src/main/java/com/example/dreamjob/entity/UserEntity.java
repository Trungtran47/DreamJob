package com.example.dreamjob.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
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
    @Column(name = "file_cv",nullable = true)
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

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<PostEntity> post = new ArrayList<>();

    @JsonManagedReference
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private CompanyEntity company;

}
