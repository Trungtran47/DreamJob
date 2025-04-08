package com.example.dreamjob.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
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
    @Column(name = "message_Id")
    private Long messageId;
    @Column(name="applicant_id")
    private Long applicantId;
    @Column(name="applicant_name", columnDefinition = "nvarchar(255)", nullable = true)
    private String applicantName;
    @Column(name="employer_id")
    private Long employerId;
    @Column(name="employ_name", columnDefinition = "nvarchar(255)", nullable = true)
    private String employerName;
    @Column(name="avatar_chat", columnDefinition = "nvarchar(255)", nullable = true)
    private String avatarChat;
//    @JsonManagedReference("message_post")
//    @OneToOne(mappedBy = "message", cascade = CascadeType.ALL, orphanRemoval = true)
//    private PostEntity post;
    @JsonManagedReference("message_dt")
    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MessageDetailEntity> messageDetails;

    @JsonBackReference("user_message")
    @ManyToOne
    @JoinColumn(name = "user_Id")
    private UserEntity user;
}
