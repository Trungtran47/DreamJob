package com.example.dreamjob.mapp;

import com.example.dreamjob.dto.MessageDTO;
import com.example.dreamjob.dto.request.MessageRequest;
import com.example.dreamjob.dto.response.MessageResponse;
import com.example.dreamjob.entity.MessageDetailEntity;
import com.example.dreamjob.entity.MessageEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface MessageMapper {
    MessageDTO toMessageDTO(MessageEntity message);
    List<MessageDTO> toMessageDTOs(List<MessageEntity> messages);
//    @Mapping(target = "post.postId", source = "postId") // Nếu postId cần ánh xạ đến PostEntity
    MessageEntity toMessageEntity(MessageRequest request);
    MessageDetailEntity toMessageDetailEntity(MessageRequest.MessageDetail detail);
    MessageResponse.MessageDetailResponse toMessageDetailResponse(MessageDetailEntity messageDetailEntity);
    // Ánh xạ từ MessageEntity sang MessageResponse
    @Mappings({
//            @Mapping(source = "messageId", target = "messageId"),
//            @Mapping(source = "applicantId", target = "applicantId"),
//            @Mapping(source = "applicantName", target = "applicantName"),
//            @Mapping(source = "employerId", target = "employerId"),
//            @Mapping(source = "employerName", target = "employerName"),
//            @Mapping(source = "avatarChat", target = "avatarChat"),
            @Mapping(target = "messageDetails", expression = "java(toMessageDetailResponseList(messageEntity.getMessageDetails()))")
    })
    MessageResponse toMessageResponse(MessageEntity messageEntity);
    List<MessageResponse> toMessageResponseList(List<MessageEntity> messageEntities);
    // Ánh xạ danh sách MessageDetailEntity sang MessageDetailResponse
    default List<MessageResponse.MessageDetailResponse> toMessageDetailResponseList(List<MessageDetailEntity> messageDetails) {
        return messageDetails.stream()
                .map(this::toMessageDetailResponse)
                .collect(java.util.stream.Collectors.toList());
    }
}
