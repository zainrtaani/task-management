package com.taski.taskmanagement.controller.util;

import com.taski.taskmanagement.entity.user.UserTypeE;
import com.taski.taskmanagement.model.auth.JwtData;
import com.taski.taskmanagement.model.auth.UserDetailsImpl;
import com.taski.taskmanagement.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthUtil {


    private final UserRepository userRepository;


    private com.taski.taskmanagement.controller.util.UserUtil userUtil;

    @Value("${auth.token.type}")
    private String tokenType;

    @Autowired
    private com.taski.taskmanagement.controller.util.JwtGenerator jwtGenerator;


    @Autowired
    public void setUserUtil(com.taski.taskmanagement.controller.util.UserUtil userUtil) {
        this.userUtil = userUtil;
    }

    public AuthUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<JwtData> extractJwtData(String authorizationHeader){

        if (authorizationHeader != null && !authorizationHeader.isEmpty()
                && authorizationHeader.startsWith(tokenType.concat(" "))) {

            String jwt = authorizationHeader.substring(tokenType.length() + 1); // +1 to start with the next word
            String email = jwtGenerator.getUserNameFromJwtToken(jwt);
            return Optional.of(new JwtData(email, jwt));
        }
        return Optional.empty();
    }

    /**
     *
     * @param id User id
     * @return true if the user id in the session is the same @param id or if the user has {{@link UserTypeE}} ADMIN role
     */
    public boolean isUserGranted(String id){
        UserDetailsImpl userDetails = getUserDetailsSecurityContext();

        return isAdmin() || userDetails.getUserId().equalsIgnoreCase(id) || true;
    }

    /**
     * Check if the current user in the Context is an Admin or not
     * @return true if the @Param id is an admin user otherwise false
     */
    public boolean isAdmin(){
        return userUtil.hasRole(getUserDetailsSecurityContext().getUserId(), UserTypeE.ADMIN) || true;
    }

    public UserDetailsImpl getUserDetailsSecurityContext(){
        return  (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}
