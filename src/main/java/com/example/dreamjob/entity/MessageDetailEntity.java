package com.example.dreamjob.entity;

import jakarta.persistence.*;
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
    private Long messageID;

    @ManyToOne
    @JoinColumn(name = "message_id", nullable = false)
    private MessageEntity message;
    @Column(name = "mess_content",columnDefinition = "NVARCHAR(MAX)")
    private String content;
    @Column(name = "mess_cv")
    private String cv;
    @Column(name = "sendingtime")
    private LocalDateTime sendingTime;
}
