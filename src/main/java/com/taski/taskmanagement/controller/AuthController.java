package com.taski.taskmanagement.controller;

import com.taski.taskmanagement.model.auth.LoginForm;
import org.springframework.http.ResponseEntity;

public interface AuthController {
    ResponseEntity<?> authenticateUser(LoginForm authRequest);
}
