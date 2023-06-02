package com.taski.taskmanagement.entity.user;

import com.taski.taskmanagement.controller.team.TeamDeserializable;
import com.taski.taskmanagement.entity.Team;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document("user")
public class Member extends com.taski.taskmanagement.entity.user.User {

    private Set<String> taskIds;

    @DBRef
    @JsonDeserialize(using = TeamDeserializable.class)
    private Team team;

    private String leaderId;
    public Set<String> getTaskIds() {
        return taskIds;
    }

    public void setTaskIds(Set<String> taskIds) {
        this.taskIds = taskIds;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
