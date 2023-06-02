package com.taski.taskmanagement.controller.user;

import com.taski.taskmanagement.controller.UserController;
import com.taski.taskmanagement.controller.util.JwtGenerator;
import com.taski.taskmanagement.controller.util.UserUtil;
import com.taski.taskmanagement.entity.user.Member;
import com.taski.taskmanagement.entity.user.User;
import com.taski.taskmanagement.exception.DuplicateEntityException;
import com.taski.taskmanagement.exception.EntityNotFoundException;
import com.taski.taskmanagement.model.auth.JwtAuthResponse;
import com.taski.taskmanagement.model.auth.LoginForm;
import com.taski.taskmanagement.model.auth.UserDetailsImpl;
import com.taski.taskmanagement.repo.MemberRepository;
import com.taski.taskmanagement.repo.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class UserControllerImpl<T extends User> implements UserController<T> {


    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;
    private final UserUtil userUtil;

    private final JwtGenerator jwtGenerator;
    private final MemberRepository memberRepository;

    public UserControllerImpl(UserRepository userRepository, MemberRepository memberRepository, UserUtil userUtil,
                              AuthenticationManager authenticationManager, JwtGenerator jwtGenerator) {
        this.userRepository = userRepository;
        this.userUtil = userUtil;
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
        this.memberRepository = memberRepository;
    }

    @SuppressWarnings("unchecked")
    @Override
    @PostMapping("/register")
    public ResponseEntity<JwtAuthResponse> registerByUsernameAndPassword(@Valid @RequestBody @NonNull LoginForm userInfo) {

        try{
            userUtil.checkDuplicateEmail(userInfo.getEmail());
        }catch(DuplicateEntityException e){
            throw new RuntimeException(e);
        }
        User user = userUtil.prepareUserForCreate(userInfo);
        userRepository.save(user);

        Authentication authentication;
        try{

            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(userInfo.getEmail(), userInfo.getPassword()));
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect username or password", e);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String accessToken = jwtGenerator.generateJwtToken(userDetails);

        return ResponseEntity.ok(new JwtAuthResponse(userDetails, accessToken, jwtGenerator.getTokenExpirationTime()));
    }

    @SuppressWarnings("unchecked")
    @Override
    @PreAuthorize("@authUtil.isUserGranted(#id)")
    @GetMapping("/users/{id}")
    public ResponseEntity<T> findUserById(@PathVariable("id") String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return (ResponseEntity<T>) ResponseEntity.ok().body(user);
    }

    @Override
    @PreAuthorize("@authUtil.isAdmin()")
    @GetMapping("/users")
    public List<T> findAllUsers() {
        return (List<T>) userRepository.findAll();
    }



    @GetMapping("/user/{leaderId}")
    public List<Member> findAllByLeaderId(@PathVariable("leaderId") String leaderId){
        return new ArrayList<>();
    }



}
