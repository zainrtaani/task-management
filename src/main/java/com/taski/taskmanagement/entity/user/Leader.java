package com.taski.taskmanagement.entity.user;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document
public class Leader extends com.taski.taskmanagement.entity.user.User {

    private Set<String> memberIds;
    private Set<String> projectIds;


    public Set<String> getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(Set<String> memberIds) {
        this.memberIds = memberIds;
    }

    public Set<String> getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(Set<String> projectIds) {
        this.projectIds = projectIds;
    }
}
