package com.taski.taskmanagement.controller.task;

import com.taski.taskmanagement.dto.TaskDTO;
import com.taski.taskmanagement.entity.Task;
import com.taski.taskmanagement.exception.EntityNotFoundException;
import com.taski.taskmanagement.repo.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class TaskControllerImpl {


    private final TaskRepository taskRepository;

    public TaskControllerImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }


    @PostMapping("/task")
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDto){
        Task taskEntity = new Task(taskDto.getTitle(),
                taskDto.getDescription(),
                taskDto.getPriority(),
                taskDto.getDueDate(),
                taskDto.getStatus(),
                taskDto.getCategoryId(), taskDto.getAssignee(), taskDto.getProject());

        taskRepository.save(taskEntity);
        return ResponseEntity.ok(taskEntity);
    }

    @GetMapping("/task")
    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<Task> findById(@PathVariable("id") String taskId){
        return ResponseEntity.ok(taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException(Task.class)));
    }
}
