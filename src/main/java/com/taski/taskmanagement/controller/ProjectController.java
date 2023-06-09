package com.taski.taskmanagement.controller;

import com.taski.taskmanagement.entity.Project;
import com.taski.taskmanagement.model.ProjectModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProjectController {

    ResponseEntity<?> createNewProject(ProjectModel projectModel);
    List<Project> findAll();

    ResponseEntity<Project> findProjectById(@PathVariable("id") String id);

    List<Project> findProjectsForUserId(String userId);

    ResponseEntity<?> getProjectCompletionStatus(String projectId);
}
