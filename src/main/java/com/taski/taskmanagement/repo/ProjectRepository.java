package com.taski.taskmanagement.repo;

import com.taski.taskmanagement.entity.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {

    Optional<Project> findProjectById(String projectId);
    Optional<Project> findProjectByOwnerId(String ownerId);

    List<Project> findProjectsByOwnerId(String ownerId);
}
