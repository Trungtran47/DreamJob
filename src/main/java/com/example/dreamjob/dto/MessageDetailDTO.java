package com.example.dreamjob.dto;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDetailDTO {
    private Long messageDtId;
    private Long messageId;
    private Long userId;
    private String content;
    private String cv;
    private String sendingTime;
}
