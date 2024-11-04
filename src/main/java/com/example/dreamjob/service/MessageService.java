package com.example.dreamjob.service;

import com.example.dreamjob.entity.MessageEntity;

public interface MessageService {
    MessageEntity getMessageByUserId(Long userId);
    MessageEntity saveMessage(MessageEntity message);
}
