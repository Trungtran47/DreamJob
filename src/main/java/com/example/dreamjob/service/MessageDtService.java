package com.example.dreamjob.service;

import com.example.dreamjob.dto.MessageDTO;
import com.example.dreamjob.dto.MessageDetailDTO;
import com.example.dreamjob.dto.request.MessageDetailRequest;

import java.util.List;

public interface MessageDtService {
    MessageDetailDTO saveMessageDetail(MessageDetailRequest messageDetailRequest);
    List<MessageDetailDTO> getMessageDetailByMessageId(Long messageId);
    MessageDetailDTO getAllMessageDetailByApplicantIdAndUserId(Long applicantId, Long userId);
    MessageDetailDTO getAllMessageDetailByEmployerIdAndUserId(Long employerId, Long userId);

}
