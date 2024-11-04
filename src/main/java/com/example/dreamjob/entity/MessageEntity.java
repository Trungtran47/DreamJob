package com.example.dreamjob.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "app_message")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<UserEntity> applicant;
    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<UserEntity>  employer;
    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<PostEntity> post;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MessageDetailEntity> messageDetails;

}
