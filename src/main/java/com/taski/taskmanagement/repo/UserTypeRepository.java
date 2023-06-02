package com.taski.taskmanagement.repo;

import com.taski.taskmanagement.entity.user.UserType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserTypeRepository extends MongoRepository<UserType, String> {
    Optional<UserType> findByType(String name);
}
