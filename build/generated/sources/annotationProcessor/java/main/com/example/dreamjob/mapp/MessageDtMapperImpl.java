package com.example.dreamjob.mapp;

import com.example.dreamjob.dto.MessageDetailDTO;
import com.example.dreamjob.entity.MessageDetailEntity;
import com.example.dreamjob.entity.MessageEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class MessageDtMapperImpl implements MessageDtMapper {

    @Override
    public MessageDetailDTO MessageToMessageDetailDTO(MessageDetailEntity message) {

        MessageDetailDTO.MessageDetailDTOBuilder messageDetailDTO = MessageDetailDTO.builder();

        if ( message != null ) {
            messageDetailDTO.messageId( messageMessageMessageId( message ) );
            messageDetailDTO.messageDtId( message.getMessageDtId() );
            messageDetailDTO.userId( message.getUserId() );
            messageDetailDTO.content( message.getContent() );
            messageDetailDTO.cv( message.getCv() );
            messageDetailDTO.sendingTime( message.getSendingTime() );
        }

        return messageDetailDTO.build();
    }

    @Override
    public List<MessageDetailDTO> MessageToMessageDetailDTOs(List<MessageDetailEntity> messages) {
        if ( messages == null ) {
            return new ArrayList<MessageDetailDTO>();
        }

        List<MessageDetailDTO> list = new ArrayList<MessageDetailDTO>( messages.size() );
        for ( MessageDetailEntity messageDetailEntity : messages ) {
            list.add( MessageToMessageDetailDTO( messageDetailEntity ) );
        }

        return list;
    }

    private Long messageMessageMessageId(MessageDetailEntity messageDetailEntity) {
        if ( messageDetailEntity == null ) {
            return null;
        }
        MessageEntity message = messageDetailEntity.getMessage();
        if ( message == null ) {
            return null;
        }
        Long messageId = message.getMessageId();
        if ( messageId == null ) {
            return null;
        }
        return messageId;
    }
}
