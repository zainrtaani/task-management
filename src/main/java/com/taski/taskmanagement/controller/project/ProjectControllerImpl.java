package com.taski.taskmanagement.controller.project;

import com.taski.taskmanagement.controller.ProjectController;
import com.taski.taskmanagement.entity.Project;
import com.taski.taskmanagement.exception.EntityNotFoundException;
import com.taski.taskmanagement.model.ProjectModel;
import com.taski.taskmanagement.repo.ProjectRepository;
import com.taski.taskmanagement.repo.TaskRepository;
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
    private final TaskRepository taskRepository;

    public ProjectControllerImpl(ProjectRepository projectRepository, TaskRepository taskRepository){
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
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
        List<Project> projects = projectRepository.findAll();
        projects.forEach(project -> project.setTasks(taskRepository.findByProject(project)));

        return projects;
    }



    @Override
    @GetMapping("/project/{id}")
    public ResponseEntity<Project> findProjectById(@PathVariable("id") String id){

        Project projectResponse = projectRepository.findById(id).map(project -> {
            project.setTasks(taskRepository.findByProject(project));
            return project;
        }).orElseThrow(() -> new EntityNotFoundException(Project.class));

        return ResponseEntity.ok(projectResponse);
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
