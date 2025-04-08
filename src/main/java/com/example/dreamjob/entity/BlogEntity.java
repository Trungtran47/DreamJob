package com.example.dreamjob.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "app_blog")
public class BlogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private Long blogId;

    @Column(name ="blog_title", columnDefinition = "NVARCHAR(255)")
    private String title;

    @Column(name = "blog_content", columnDefinition = "nvarchar(MAX)")
    private String content;

    @Column(name = "blog_author", columnDefinition = "nvarchar(255)")
    private String author;

    @Column(name = "blog_image")
    private String image;

    @Column(name = "blog_time")
    private String time;

    @Column(name = "blog_status")
    private  int status;

    @JsonBackReference("user_blog")
    @ManyToOne
    @JoinColumn(name = "user_Id")
    private UserEntity user;
}
