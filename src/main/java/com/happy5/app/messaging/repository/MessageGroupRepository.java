package com.happy5.app.messaging.repository;

import com.happy5.app.messaging.model.MessageGroup;
import com.happy5.app.messaging.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageGroupRepository extends JpaRepository<MessageGroup, Long> {

    // find group by members
    Optional<MessageGroup> findMessageGroupByMembers(String members);

    // find group by group id
    Optional<MessageGroup> findMessageGroupByGroupId(Long groupId);

    // find group by members like user id
    List<MessageGroup> findMessageGroupByMembersContains(String userId);
}
