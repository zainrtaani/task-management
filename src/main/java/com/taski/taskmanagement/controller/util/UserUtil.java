package com.taski.taskmanagement.controller.util;

import com.taski.taskmanagement.entity.user.UserType;
import com.taski.taskmanagement.entity.user.UserTypeE;
import com.taski.taskmanagement.entity.user.User;
import com.taski.taskmanagement.exception.DuplicateEntityException;
import com.taski.taskmanagement.exception.EntityNotFoundException;
import com.taski.taskmanagement.model.auth.LoginForm;
import com.taski.taskmanagement.repo.UserTypeRepository;
import com.taski.taskmanagement.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserUtil {


    private final UserRepository userRepo;
    private PasswordEncoder passwordEncoder;
    private final UserTypeRepository roleRepository;

    public UserUtil(UserRepository userRepository, UserTypeRepository roleRepository){
        this.userRepo = userRepository;
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * <P>Method to check if the user is already exist in the database, throws {@link DuplicateEntityException} if true otherwise ignore</P>
     * @param email userdata
     * @throws DuplicateEntityException if user's username is already exist in the database
     */
    public void checkDuplicateEmail(@NonNull String email){
        userRepo.findByEmail(email).ifPresent(user -> {
            throw new DuplicateEntityException("User with "+ user.getEmail() + " is already exist");
        });
    }

    /**
     *  Hash the user password and add user's role
     * @param userInfo User to be created
     * @param roleType User role whether it's admin or user
     * @return
     */
    @NonNull
    public User prepareUserForCreate(LoginForm userInfo){

        User user = new User();
        user.setPassword(userInfo.getPassword());
        user.setEmail(userInfo.getEmail());

        String hashedPassword = passwordEncoder.encode(userInfo.getPassword());
        user.setPassword(hashedPassword);
        user.setUserTypeE(UserTypeE.ADMIN);

        return user;
    }

    /**
     * Find or create {@link UserType}
     * @param roleType
     * @return
     */
    public UserType findOrCreate(UserTypeE roleType){
        return roleRepository.findByType(roleType.getRoleName())
                .orElseGet(() -> {
                    UserType userType = new UserType();
                    userType.setType(roleType.getRoleName());
                    roleRepository.save(userType);
                    return userType;
                });

    }

    public boolean hasRole(String userId, UserTypeE roleType){
      User user = userRepo.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
      return user.getUserTypes().stream().anyMatch(role -> role.getType().equals(roleType.getRoleName()));
    }
}
