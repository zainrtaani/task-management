package com.taski.taskmanagement.controller.status;


import com.taski.taskmanagement.entity.Status;
import com.taski.taskmanagement.exception.EntityNotFoundException;
import com.taski.taskmanagement.repo.StatusRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class StatusController {


    private final StatusRepository statusRepository;

    public StatusController(StatusRepository statusRepository){
        this.statusRepository = statusRepository;
    }
    @GetMapping("/status/{id}")
    public ResponseEntity<Status> findById(@PathVariable("id") String id){
        return ResponseEntity.ok(statusRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Status id not found")));
    }

    @GetMapping("/status")
    public List<Status> findAll(){
        return statusRepository.findAll();
    }

    @PostMapping("/status")
    public ResponseEntity<Status> create(@RequestBody Status status){
        return ResponseEntity.ok(statusRepository.save(status));
    }
}
