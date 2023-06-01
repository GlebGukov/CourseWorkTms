package com.example.coursework.service.impl;

import com.example.coursework.mapper.UserMapper;
import com.example.coursework.models.UserEntity;
import com.example.coursework.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity byLogin = userRepository.findByLogin(username);
        return mapper.toUserDetails(byLogin);
    }
}
