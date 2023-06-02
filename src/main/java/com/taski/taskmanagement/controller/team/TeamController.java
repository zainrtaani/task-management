package com.taski.taskmanagement.controller.team;


import com.taski.taskmanagement.entity.Team;
import com.taski.taskmanagement.exception.EntityNotFoundException;
import com.taski.taskmanagement.repo.TeamRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class TeamController {


    private final TeamRepository teamRepository;

    public TeamController(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }


    @PostMapping("/team")
    public ResponseEntity<Team> createNew(@NotNull @Valid @RequestBody Team team){
        return ResponseEntity.ok(this.teamRepository.save(team));
    }

    @GetMapping("/team")
    public List<Team> findAll(){
        return teamRepository.findAll();
    }

    @GetMapping("/team/{id}")
    public ResponseEntity<Team> findById(@PathVariable("id") String teamId){
        return teamRepository.findById(teamId).map(ResponseEntity::ok).orElseThrow(() -> new EntityNotFoundException(Team.class));
    }

}
