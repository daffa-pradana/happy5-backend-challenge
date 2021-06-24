package com.happy5.app.messaging.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class MessageGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long groupId;

    @Column(nullable = false)
    private String members;

    // constructor - default
    public MessageGroup() {}

    // constructor - param
    public MessageGroup(String members) {
        this.members = members;
    }

    // getters & setters
    public Long getGroupId() { return groupId; }
    public String getMembers() { return members; }
    public void setGroupId(Long groupId) { this.groupId = groupId; }
    public void setMembers(String members) { this.members = members; }
}
