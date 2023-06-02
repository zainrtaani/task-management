package com.taski.taskmanagement.model;

import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import java.sql.Date;

public class ProjectModel {

    @NotBlank
    @NonNull
    private String title;

    @NotBlank
    @NonNull
    private String description;

    private Date startDate;

    private Date endDate;

    @NotBlank
    @NonNull
    private String ownerId;

    public ProjectModel(@NotNull String title, @NotNull String description, @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate, @DateTimeFormat(pattern="yyyy-MM-dd")Date endDate, @NotNull String ownerId) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ownerId = ownerId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getOwnerId() {
        return ownerId;
    }
}
