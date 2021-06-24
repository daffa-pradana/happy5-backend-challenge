package com.happy5.app.messaging.repository;

import com.happy5.app.messaging.model.MessageGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageGroupRepository extends JpaRepository<MessageGroup, Long> {

    Optional<MessageGroup> findMessageGroupByMembers(String members);

}
