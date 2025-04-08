package com.example.dreamjob.dto.request;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDetailRequest {
    private Long messageDtId;
    private Long messageId; // Tham chiếu đến MessageEntity
    private Long userId;
    private String content;
    private String cv;
    private String sendingTime;
}
