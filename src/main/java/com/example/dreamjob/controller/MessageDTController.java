package com.example.dreamjob.controller;

import com.example.dreamjob.dto.MessageDetailDTO;
import com.example.dreamjob.dto.request.MessageDetailRequest;
import com.example.dreamjob.service.MessageDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class MessageDTController {
    @Autowired
    private MessageDtService messageDtService;


    @GetMapping(value="/message_detail/{userId}", produces = "application/json; charset=utf-8")
    public ResponseEntity<List<MessageDetailDTO>> getMessageDetail(@PathVariable Long userId) {
        try {
            List<MessageDetailDTO> messageDetailDTO = messageDtService.getMessageDetailByMessageId(userId);
            return ResponseEntity.ok(messageDetailDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/message_detail/save")
    public ResponseEntity<?> saveMessageDetail(@RequestBody MessageDetailRequest messageDetailRequest) {
        try {
            MessageDetailDTO messageDetailDTO = messageDtService.saveMessageDetail(messageDetailRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(messageDetailDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/message_detail/NotiApplicant/{applicantId}/{userId}")
    public ResponseEntity<?> getNotificationByApplicant(@PathVariable Long applicantId, @PathVariable Long userId) {
        try {
            MessageDetailDTO messageDetailDTOS = messageDtService.getAllMessageDetailByApplicantIdAndUserId(applicantId, userId);
            return ResponseEntity.ok(messageDetailDTOS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/message_detail/NotiEmployer/{employerId}/{userId}")
    public ResponseEntity<?> getNotificationByEmployer(@PathVariable Long employerId, @PathVariable Long userId) {
        try {
            MessageDetailDTO messageDetailDTOS = messageDtService.getAllMessageDetailByEmployerIdAndUserId(employerId, userId);
            return ResponseEntity.ok(messageDetailDTOS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
