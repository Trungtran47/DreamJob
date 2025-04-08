package com.example.dreamjob.repository;

import com.example.dreamjob.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
//    List<MessageEntity> findByApplicantId(Long id);
//    List<MessageEntity> findByEmployerId(Long id);
    Optional<MessageEntity> findByEmployerIdAndApplicantId(Long employerId, Long applicantId);
    @Query("SELECT m FROM MessageEntity m JOIN m.messageDetails d " +
            "WHERE m.applicantId = :applicantId " +
            "ORDER BY d.sendingTime DESC")
    List<MessageEntity> findByApplicantIdOrderBySendTimeDesc(@Param("applicantId") Long applicantId);

    @Query("SELECT m FROM MessageEntity m JOIN m.messageDetails d " +
            "WHERE m.employerId = :employerId " +
            "ORDER BY d.sendingTime DESC")
    List<MessageEntity> findByEmployerIdOrderBySendTimeDesc(@Param("employerId") Long employerId);

}
