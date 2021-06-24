package com.happy5.app.messaging.repository;

import com.happy5.app.messaging.model.MessageGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageGroupRepository extends JpaRepository<MessageGroup, Long> {

    // find message group by members

}
