package com.example.coursework.service.impl;

import com.example.coursework.dto.UserDto;
import com.example.coursework.mapper.UserMapper;
import com.example.coursework.repositories.UserRepository;
import com.example.coursework.security.Role;
import com.example.coursework.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    @Override
    public UserDto saveToDataBase(UserDto userDto) {
//        return userMapper.toDto(repository.save(userMapper.toEntity(userDto)));
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        userDto.setRole(Role.USER);
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
    }
}
