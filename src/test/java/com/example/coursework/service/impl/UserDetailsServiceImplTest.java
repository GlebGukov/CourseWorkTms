package com.example.coursework.service.impl;

import com.example.coursework.mapper.UserMapper;
import com.example.coursework.models.UserEntity;
import com.example.coursework.repositories.UserRepository;
import com.example.coursework.security.UserDetailsImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper mapper;
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void loadUserByUsername() {
        //given
        var userEntity = UserEntity.builder()
                .login("loginForTest")
                .build();
        var username = userEntity.getLogin();
        Mockito.when(userRepository.findByLogin(username)).thenReturn(userEntity);
        Mockito.when(mapper.toUserDetails(userEntity)).thenReturn(
                UserDetailsImpl.builder()
                        .login("loginForTest")
                        .build());
        //when
        Assertions.assertEquals(userEntity.getLogin(),userDetailsService.loadUserByUsername(username).getUsername());
        //then
    }
}