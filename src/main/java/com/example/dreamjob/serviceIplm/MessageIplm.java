package com.example.dreamjob.serviceIplm;

import com.example.dreamjob.dto.MessageDTO;
import com.example.dreamjob.dto.request.MessageRequest;
import com.example.dreamjob.dto.response.MessageResponse;
import com.example.dreamjob.entity.MessageDetailEntity;
import com.example.dreamjob.entity.MessageEntity;
import com.example.dreamjob.mapp.MessageDtMapper;
import com.example.dreamjob.mapp.MessageMapper;
import com.example.dreamjob.repository.MessageDtRepository;
import com.example.dreamjob.repository.MessageRepository;
import com.example.dreamjob.repository.PostRepository;
import com.example.dreamjob.repository.UserRepository;
import com.example.dreamjob.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageIplm implements MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessageDtRepository messageDtRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private MessageDtMapper messageDtMapper;


    @Override
    public MessageResponse createMessage(MessageRequest request) {
        // Kiểm tra xem đã có MessageEntity nào với employerId và applicantId chưa
        Optional<MessageEntity> existingMessage = messageRepository.findByEmployerIdAndApplicantId(request.getEmployerId(), request.getApplicantId());

        MessageEntity messageEntity;
        if (existingMessage.isPresent()) {
            // Nếu đã có message, dùng message cũ và chỉ thêm MessageDetail
            messageEntity = existingMessage.get();
        } else {
            // Nếu chưa có message, tạo mới messageEntity
            messageEntity = messageMapper.toMessageEntity(request);
            messageRepository.save(messageEntity);  // Lưu messageEntity vào DB
        }

        // Tạo các MessageDetailEntity từ request và gán vào messageEntity
        List<MessageDetailEntity> messageDetails = request.getMessageDetails().stream()
                .map(messageMapper::toMessageDetailEntity)
                .collect(Collectors.toList());
        messageDetails.forEach(detail -> detail.setMessage(messageEntity));  // Gán messageEntity cho mỗi detail

        // Lưu các MessageDetail vào cơ sở dữ liệu
        messageDtRepository.saveAll(messageDetails);

        // Chuyển messageEntity thành response để trả về
        MessageResponse response = messageMapper.toMessageResponse(messageEntity);
        return response;
    }

    @Override
    public List<MessageDTO> getMessageByApplicantId(Long applicantId) {
        try {
            List<MessageEntity> messageEntity = messageRepository.findByApplicantIdOrderBySendTimeDesc(applicantId);
            return messageMapper.toMessageDTOs(messageEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<MessageResponse> getMessageResponsesByEmployerId(Long employerId) {
        try {
            List<MessageEntity> messageEntity = messageRepository.findByEmployerIdOrderBySendTimeDesc(employerId);
            return messageMapper.toMessageResponseList(messageEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
