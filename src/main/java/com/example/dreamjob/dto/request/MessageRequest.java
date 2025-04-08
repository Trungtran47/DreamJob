package com.example.dreamjob.dto.request;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class MessageRequest {
    // Thông tin chính của Message
    private Long applicantId;
    private String applicantName;
    private Long employerId;
    private String employerName;
    private String avatarChat; // Dùng để ánh xạ đến PostEntity, nếu cần

    // Thông tin chi tiết của MessageDetail
    private List<MessageDetail> messageDetails;

    @Data
    public static class MessageDetail {
        private Long userId;
        private String content;
        private String cv;
        private LocalDateTime sendingTime;

    }
}
