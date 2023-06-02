package com.taski.taskmanagement.repo;

import com.taski.taskmanagement.entity.Status;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends MongoRepository<Status, String> {

    @NotNull Optional<Status> findById(@NotNull String id);

    @NotNull Optional<Status> findByName(@NotNull  String name);
}
