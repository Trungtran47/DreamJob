package com.example.dreamjob.mapp;

import com.example.dreamjob.dto.MessageDetailDTO;
import com.example.dreamjob.entity.MessageDetailEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface MessageDtMapper {
    @Mapping(source = "message.messageId", target = "messageId")
    MessageDetailDTO MessageToMessageDetailDTO(MessageDetailEntity message);
    List<MessageDetailDTO> MessageToMessageDetailDTOs(List<MessageDetailEntity> messages);
}
