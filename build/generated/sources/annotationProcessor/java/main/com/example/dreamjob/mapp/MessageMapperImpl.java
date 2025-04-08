package com.example.dreamjob.mapp;

import com.example.dreamjob.dto.MessageDTO;
import com.example.dreamjob.dto.request.MessageRequest;
import com.example.dreamjob.dto.response.MessageResponse;
import com.example.dreamjob.entity.MessageDetailEntity;
import com.example.dreamjob.entity.MessageEntity;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class MessageMapperImpl implements MessageMapper {

    @Override
    public MessageDTO toMessageDTO(MessageEntity message) {

        MessageDTO.MessageDTOBuilder messageDTO = MessageDTO.builder();

        if ( message != null ) {
            messageDTO.messageId( message.getMessageId() );
            messageDTO.applicantId( message.getApplicantId() );
            messageDTO.applicantName( message.getApplicantName() );
            messageDTO.employerId( message.getEmployerId() );
            messageDTO.employerName( message.getEmployerName() );
            messageDTO.avatarChat( message.getAvatarChat() );
        }

        return messageDTO.build();
    }

    @Override
    public List<MessageDTO> toMessageDTOs(List<MessageEntity> messages) {
        if ( messages == null ) {
            return new ArrayList<MessageDTO>();
        }

        List<MessageDTO> list = new ArrayList<MessageDTO>( messages.size() );
        for ( MessageEntity messageEntity : messages ) {
            list.add( toMessageDTO( messageEntity ) );
        }

        return list;
    }

    @Override
    public MessageEntity toMessageEntity(MessageRequest request) {

        MessageEntity messageEntity = new MessageEntity();

        if ( request != null ) {
            messageEntity.setApplicantId( request.getApplicantId() );
            messageEntity.setApplicantName( request.getApplicantName() );
            messageEntity.setEmployerId( request.getEmployerId() );
            messageEntity.setEmployerName( request.getEmployerName() );
            messageEntity.setAvatarChat( request.getAvatarChat() );
            messageEntity.setMessageDetails( messageDetailListToMessageDetailEntityList( request.getMessageDetails() ) );
        }

        return messageEntity;
    }

    @Override
    public MessageDetailEntity toMessageDetailEntity(MessageRequest.MessageDetail detail) {

        MessageDetailEntity messageDetailEntity = new MessageDetailEntity();

        if ( detail != null ) {
            messageDetailEntity.setUserId( detail.getUserId() );
            messageDetailEntity.setContent( detail.getContent() );
            messageDetailEntity.setCv( detail.getCv() );
            if ( detail.getSendingTime() != null ) {
                messageDetailEntity.setSendingTime( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( detail.getSendingTime() ) );
            }
        }

        return messageDetailEntity;
    }

    @Override
    public MessageResponse.MessageDetailResponse toMessageDetailResponse(MessageDetailEntity messageDetailEntity) {

        Long messageDtId = null;
        Long userId = null;
        String content = null;
        String cv = null;
        String sendingTime = null;
        if ( messageDetailEntity != null ) {
            messageDtId = messageDetailEntity.getMessageDtId();
            userId = messageDetailEntity.getUserId();
            content = messageDetailEntity.getContent();
            cv = messageDetailEntity.getCv();
            sendingTime = messageDetailEntity.getSendingTime();
        }

        MessageResponse.MessageDetailResponse messageDetailResponse = new MessageResponse.MessageDetailResponse( messageDtId, userId, content, cv, sendingTime );

        if ( messageDetailEntity != null ) {
        }

        return messageDetailResponse;
    }

    @Override
    public MessageResponse toMessageResponse(MessageEntity messageEntity) {

        Long messageId = null;
        Long applicantId = null;
        String applicantName = null;
        Long employerId = null;
        String employerName = null;
        String avatarChat = null;
        if ( messageEntity != null ) {
            messageId = messageEntity.getMessageId();
            applicantId = messageEntity.getApplicantId();
            applicantName = messageEntity.getApplicantName();
            employerId = messageEntity.getEmployerId();
            employerName = messageEntity.getEmployerName();
            avatarChat = messageEntity.getAvatarChat();
        }

        List<MessageResponse.MessageDetailResponse> messageDetails = toMessageDetailResponseList(messageEntity.getMessageDetails());

        MessageResponse messageResponse = new MessageResponse( messageId, applicantId, applicantName, employerId, employerName, avatarChat, messageDetails );

        if ( messageEntity != null ) {
        }

        return messageResponse;
    }

    @Override
    public List<MessageResponse> toMessageResponseList(List<MessageEntity> messageEntities) {
        if ( messageEntities == null ) {
            return new ArrayList<MessageResponse>();
        }

        List<MessageResponse> list = new ArrayList<MessageResponse>( messageEntities.size() );
        for ( MessageEntity messageEntity : messageEntities ) {
            list.add( toMessageResponse( messageEntity ) );
        }

        return list;
    }

    protected List<MessageDetailEntity> messageDetailListToMessageDetailEntityList(List<MessageRequest.MessageDetail> list) {
        if ( list == null ) {
            return new ArrayList<MessageDetailEntity>();
        }

        List<MessageDetailEntity> list1 = new ArrayList<MessageDetailEntity>( list.size() );
        for ( MessageRequest.MessageDetail messageDetail : list ) {
            list1.add( toMessageDetailEntity( messageDetail ) );
        }

        return list1;
    }
}
