package com.example.dreamjob.service;

import com.example.dreamjob.dto.MessageDTO;
import com.example.dreamjob.dto.request.MessageRequest;
import com.example.dreamjob.dto.response.MessageResponse;

import java.util.List;

public interface MessageService {
    MessageResponse createMessage(MessageRequest messageRequest);
    List<MessageDTO> getMessageByApplicantId(Long applicantId);
    List<MessageResponse> getMessageResponsesByEmployerId(Long employerId);
}
