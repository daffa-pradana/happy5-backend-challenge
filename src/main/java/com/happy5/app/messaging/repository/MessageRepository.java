package com.happy5.app.messaging.repository;

import com.happy5.app.messaging.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
