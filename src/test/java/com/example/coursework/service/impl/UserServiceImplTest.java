package com.example.coursework.service.impl;

import com.example.coursework.converter.ConverterStringToRole;
import com.example.coursework.dto.UserDto;
import com.example.coursework.mapper.UserMapper;
import com.example.coursework.repositories.UserRepository;
import com.example.coursework.security.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;

import java.util.UUID;


class UserServiceImplTest {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private ConverterStringToRole converterStringToRole;
    private UserServiceImpl userService;

    @Test
    void saveToDataBaseComplete() {
        //given
        var user = UserDto.builder()
                .email("test@mail.ru")
                .login("testLogin")
                .first_name("testFName")
                .last_name("testLName")
                .id(UUID.randomUUID())
                .role(Role.ROLE_USER)
                .status(true)
                .build();
        //when
        userService.saveToDataBase(user);

        //then
        Assertions.
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void changeRole() {
    }
}