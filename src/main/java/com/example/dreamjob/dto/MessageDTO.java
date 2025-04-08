package com.example.dreamjob.dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDTO {
    private Long messageId;
    private Long applicantId;
    private String applicantName;
    private Long employerId;
    private String employerName;
    private String avatarChat;
}
