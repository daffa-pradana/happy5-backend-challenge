package com.happy5.app.messaging.repository;

import com.happy5.app.messaging.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    // find messages by group id
    List<Message> findMessageByGroupId(Long groupId);

}
