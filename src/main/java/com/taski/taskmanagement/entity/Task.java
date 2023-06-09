package com.taski.taskmanagement.entity;


import com.taski.taskmanagement.controller.project.ProjectDeserializer;
import com.taski.taskmanagement.controller.status.StatusDeserializer;
import com.taski.taskmanagement.controller.user.UserDeserializer;
import com.taski.taskmanagement.entity.user.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Task {

    @Id
    private String id;

    private final String title;
    private final String description;
    private final int priority;
    private final Date dueDate;

    @DBRef
    private Status status;
    private final String categoryId;

    @DBRef
    private User assignee;

    @DBRef
    private Project project;

    @DBRef
    private User createdBy;
    public Task(String title,
                String description,
                int priority,
                Date dueDate,
                Status status,
                String categoryId,
                User assignee, Project project, User createdBy) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.project = project;
        this.status = status;
        this.categoryId = categoryId;
        this.assignee = assignee;
        this.createdBy = createdBy;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
