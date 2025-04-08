package com.example.dreamjob.repository;

import com.example.dreamjob.entity.MessageDetailEntity;
import com.example.dreamjob.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface MessageDtRepository extends JpaRepository<MessageDetailEntity, Long> {
    List<MessageDetailEntity> findByMessage_MessageId(Long messageId);
    MessageDetailEntity findFirstByMessage_ApplicantIdAndUserIdNotOrderBySendingTimeDesc(
            Long applicantId,
            Long userId
    );
    MessageDetailEntity findFirstByMessage_EmployerIdAndUserIdNotOrderBySendingTimeDesc(Long employerId, Long userId);




}
