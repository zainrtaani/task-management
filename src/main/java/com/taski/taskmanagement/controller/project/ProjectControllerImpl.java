package com.taski.taskmanagement.controller.project;

import com.taski.taskmanagement.controller.ProjectController;
import com.taski.taskmanagement.entity.Project;
import com.taski.taskmanagement.model.ProjectModel;
import com.taski.taskmanagement.repo.ProjectRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class ProjectControllerImpl implements ProjectController {


    private final ProjectRepository projectRepository;

    public ProjectControllerImpl(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    @Override
    @PostMapping("/project")
    public ResponseEntity<?> createNewProject(@NotNull @Valid @RequestBody ProjectModel projectModel) {
        Project project = new Project();
        project.setDescription(projectModel.getDescription());
        project.setTitle(projectModel.getTitle());
        project.setOwnerId(projectModel.getOwnerId());
        project.setEndDate(projectModel.getEndDate());
        project.setStartDate(projectModel.getStartDate());

        return ResponseEntity.ok(this.projectRepository.save(project));
    }


    @Override
    @GetMapping("/projects")
    public List<Project> findAll(){
        return projectRepository.findAll();
    }


    @Override
    @GetMapping("/projects/{ownerId}")
    public List<Project> findProjectsForUserId(@PathVariable("ownerId") String ownerId) {
        return projectRepository.findProjectsByOwnerId(ownerId);
    }

    @Override
    public ResponseEntity<?> getProjectCompletionStatus(String projectId) {
        return null;
    }
}
