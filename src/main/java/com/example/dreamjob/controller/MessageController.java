package com.example.dreamjob.controller;

import com.example.dreamjob.dto.MessageDTO;
import com.example.dreamjob.dto.request.MessageRequest;
import com.example.dreamjob.dto.response.MessageResponse;
import com.example.dreamjob.service.MessageDtService;
import com.example.dreamjob.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageDtService messageDtService;

    @PostMapping("/message/create")
    public ResponseEntity<?> createMessage(@RequestBody MessageRequest messageRequest) {
        try {
            MessageResponse messageResponse = messageService.createMessage(messageRequest);
            return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/applicant/list/{applicantId}")
    public ResponseEntity<List<?>> getMessageList(@PathVariable Long applicantId) {
        try {
            List<MessageDTO> message = messageService.getMessageByApplicantId(applicantId);
            return ResponseEntity.ok(message);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/employer/list/{employerId}")
    public ResponseEntity<List<MessageResponse>> getMessageListByEmployerId(@PathVariable Long employerId) {
        try {
            List<MessageResponse> messageResponse = messageService.getMessageResponsesByEmployerId(employerId);
            return ResponseEntity.ok(messageResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
