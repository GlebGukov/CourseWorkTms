package com.example.coursework.service.impl;

import com.example.coursework.converter.ConverterStringToRole;
import com.example.coursework.dto.UserDto;
import com.example.coursework.mapper.UserMapper;
import com.example.coursework.models.UserEntity;
import com.example.coursework.repositories.UserRepository;
import com.example.coursework.security.Role;
import com.example.coursework.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor

@Validated

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final ConverterStringToRole converterStringToRole;

    @Override
    public void saveToDataBase(@Valid UserDto userDto) {
        userDto.setId(UUID.randomUUID());
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        userRepository.save(userMapper.toEntity(userDto));
    }

    public List<UserDto> getAllUsers() {
        List<UserEntity> userEntityList = userRepository.findAllByIdNotNull();
        return userMapper.toDto(userEntityList);
    }

    public void changeRole(UUID id, String type) {
        UserEntity user = getUser(id);
        Role role = converterStringToRole.converterStringToRole(type);
        user.setRole(role);
        userRepository.save(user);
    }

    public void banUser(UUID id) {
        UserEntity user = getUser(id);
        user.setStatus(false);
        userRepository.save(user);

    }

    private UserEntity getUser(UUID id) {
        return userRepository.findById(id).orElseThrow();
    }
}
