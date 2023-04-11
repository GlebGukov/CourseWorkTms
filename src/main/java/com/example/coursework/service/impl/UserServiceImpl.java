package com.example.coursework.service.impl;

import com.example.coursework.dto.UserDto;
import com.example.coursework.mapper.UserMapper;
import com.example.coursework.repositories.UserRepository;
import com.example.coursework.security.Role;
import com.example.coursework.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

@RequiredArgsConstructor

@Validated
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    @Override
    public void saveToDataBase(UserDto userDto) {
        if (StringUtils.hasLength(userDto.getLogin()) && StringUtils.hasLength(userDto.getPassword())) {
            userDto.setPassword(encoder.encode(userDto.getPassword()));
            userDto.setRole(Role.ROLE_USER);
            userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
        } else throw new IllegalArgumentException("incorrect data entered, please check you login or password");
    }
}
