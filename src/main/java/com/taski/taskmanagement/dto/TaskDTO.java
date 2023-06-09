package com.taski.taskmanagement.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.taski.taskmanagement.controller.project.ProjectDeserializer;
import com.taski.taskmanagement.controller.status.StatusDeserializer;
import com.taski.taskmanagement.controller.user.UserDeserializer;
import com.taski.taskmanagement.entity.Project;
import com.taski.taskmanagement.entity.Status;
import com.taski.taskmanagement.entity.user.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TaskDTO {

    private String title;
    private String description;
    private int priority;
    private Date dueDate;

    @JsonDeserialize(using = StatusDeserializer.class)
    private Status status;
    private String categoryId;
    @JsonDeserialize(using = UserDeserializer.class)
    private User assignee;

    @JsonDeserialize(using = ProjectDeserializer.class)
    private Project project;

    @JsonDeserialize(using = UserDeserializer.class)
    private User createdBy;
    public TaskDTO(String title,
                   String description,
                   int priority,
                   @DateTimeFormat(pattern="yyyy-MM-dd") Date dueDate,
                   Status status,
                   String categoryId,
                   User assignee, Project project) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.status = status;
        this.categoryId = categoryId;
        this.assignee = assignee;
        this.project = project;
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

    public String getCategoryId() {
        return categoryId;
    }

    public User getAssignee() {
        return assignee;
    }

    public Project getProject() {
        return this.project;
    }

    public User getCreatedBy() {
        return createdBy;
    }
}
