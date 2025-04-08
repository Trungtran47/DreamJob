package com.example.dreamjob.dto.response;

import com.example.dreamjob.dto.PostDTO;
import lombok.Data;

import java.util.List;
@Data
public class MessageResponse {
    private Long messageId;
    private Long applicantId;
    private String applicantName;
    private Long employerId;
    private String employerName;
    private String avatarChat;
//    private Long postId;
    private List<MessageDetailResponse> messageDetails;

    // Constructor, getters, and setters
    public MessageResponse(Long messageId, Long applicantId, String applicantName,
                           Long employerId, String employerName, String avatarChat,
                           List<MessageDetailResponse> messageDetails) {
        this.messageId = messageId;
        this.applicantId = applicantId;
        this.applicantName = applicantName;
        this.employerId = employerId;
        this.employerName = employerName;
        this.avatarChat = avatarChat;
        this.messageDetails = messageDetails;
    }

    // Lớp nội bộ MessageDetailResponse để chứa chi tiết của mỗi MessageDetail
    @Data
    public static class MessageDetailResponse {
        private Long messageDtId;
        private Long userId;
        private String content;
        private String cv;
        private String sendingTime;

        public MessageDetailResponse(Long messageDtId, Long userId,String content, String cv, String sendingTime) {
            this.messageDtId = messageDtId;
            this.userId = userId;
            this.content = content;
            this.cv = cv;
            this.sendingTime = sendingTime;
        }


    }
}
