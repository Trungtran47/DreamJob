package com.example.dreamjob.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name= "app_messageDetail")
public class MessageDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_dt_Id")
    private Long messageDtId;
    @JsonBackReference("message_dt")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_Id", nullable = true)
    private MessageEntity message;
    @Column(name = "user_send_id", nullable = true)
    private Long userId;
    @Column(name = "mess_content",columnDefinition = "NVARCHAR(MAX)")
    private String content;
    @Column(name = "mess_cv",columnDefinition = "NVARCHAR(MAX)")
    private String cv;
    @Column(name = "sendingtime",columnDefinition = "NVARCHAR(MAX)")
    private String sendingTime;
}
