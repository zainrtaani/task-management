package com.taski.taskmanagement.controller.role;

import com.taski.taskmanagement.controller.UserTypeController;
import com.taski.taskmanagement.entity.user.UserType;
import com.taski.taskmanagement.repo.UserTypeRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class UserTypeControllerImpl implements UserTypeController {

    private final UserTypeRepository roleRepository;

    public UserTypeControllerImpl(UserTypeRepository roleRepository){
        this.roleRepository = roleRepository;
    }
    @Override
    @PreAuthorize("@authUtil.isAdmin()")
    @GetMapping("/roles")
    public List<UserType> findAll() {
        return (List<UserType>) this.roleRepository.findAll();
    }
}
