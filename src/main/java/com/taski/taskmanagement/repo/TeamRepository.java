package com.taski.taskmanagement.repo;

import com.taski.taskmanagement.entity.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends MongoRepository<Team, String> {
    Optional<Team> findTeamByNameContainingIgnoreCase(String id);
}
