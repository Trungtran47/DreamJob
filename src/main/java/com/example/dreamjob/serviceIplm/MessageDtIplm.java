package com.example.dreamjob.serviceIplm;

import com.example.dreamjob.dto.MessageDTO;
import com.example.dreamjob.dto.MessageDetailDTO;
import com.example.dreamjob.dto.request.MessageDetailRequest;
import com.example.dreamjob.entity.MessageDetailEntity;
import com.example.dreamjob.entity.MessageEntity;
import com.example.dreamjob.mapp.MessageDtMapper;
import com.example.dreamjob.repository.MessageDtRepository;
import com.example.dreamjob.repository.MessageRepository;
import com.example.dreamjob.repository.PostRepository;
import com.example.dreamjob.repository.UserRepository;
import com.example.dreamjob.service.MessageDtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageDtIplm implements MessageDtService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessageDtRepository messageDtRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MessageDtMapper messageDtMapper;
    @Override
    public MessageDetailDTO saveMessageDetail(MessageDetailRequest request) {
        try {
            MessageDetailEntity messageDetailEntity = new MessageDetailEntity();
            messageDetailEntity.setUserId(request.getUserId());
            messageDetailEntity.setCv(request.getCv());
            messageDetailEntity.setContent(request.getContent());
            messageDetailEntity.setSendingTime(request.getSendingTime());
            messageDetailEntity.setMessage(messageRepository.findById(request.getMessageId()).orElse(null));
            return messageDtMapper.MessageToMessageDetailDTO(messageDtRepository.save(messageDetailEntity));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MessageDetailDTO> getMessageDetailByMessageId(Long messageId) {
        try {
            List<MessageDetailEntity> messageDetailEntity = messageDtRepository.findByMessage_MessageId(messageId);
            return messageDtMapper.MessageToMessageDetailDTOs(messageDetailEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MessageDetailDTO getAllMessageDetailByApplicantIdAndUserId(Long applicantId, Long userId) {
        try {
            MessageDetailEntity latestMessage = messageDtRepository.findFirstByMessage_ApplicantIdAndUserIdNotOrderBySendingTimeDesc(applicantId, userId);
            return latestMessage != null ? messageDtMapper.MessageToMessageDetailDTO(latestMessage) : null;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MessageDetailDTO getAllMessageDetailByEmployerIdAndUserId(Long employerId, Long userId) {
        try {
            MessageDetailEntity latestMessage = messageDtRepository.findFirstByMessage_EmployerIdAndUserIdNotOrderBySendingTimeDesc(employerId, userId);
            return latestMessage != null ? messageDtMapper.MessageToMessageDetailDTO(latestMessage) : null;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
