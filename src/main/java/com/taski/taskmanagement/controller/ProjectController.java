package com.taski.taskmanagement.controller;

import com.taski.taskmanagement.entity.Project;
import com.taski.taskmanagement.model.ProjectModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectController {

    ResponseEntity<?> createNewProject(ProjectModel projectModel);
    List<Project> findAll();
    List<Project> findProjectsForUserId(String userId);

    ResponseEntity<?> getProjectCompletionStatus(String projectId);
}
