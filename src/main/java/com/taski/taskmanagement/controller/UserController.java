package com.taski.taskmanagement.controller;

import com.taski.taskmanagement.model.auth.LoginForm;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserController<T> {
    /**
     *  <p>Fetch all users from the database table name user</p>
     * @return List of Users
     */
    List<T> findAllUsers();

    ResponseEntity<?> registerByUsernameAndPassword(LoginForm user);

    ResponseEntity<T> findUserById(String id);
}
