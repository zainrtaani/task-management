package com.taski.taskmanagement.service;

import com.taski.taskmanagement.entity.user.User;
import com.taski.taskmanagement.exception.EntityNotFoundException;
import com.taski.taskmanagement.model.auth.UserDetailsImpl;
import com.taski.taskmanagement.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> userEntity = userRepository.findByEmail(s);
        return new UserDetailsImpl.Builder(
                userEntity.orElseThrow(() -> new EntityNotFoundException("User not found!"))
        ).build();
    }
}
