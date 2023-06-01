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
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

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
    public void saveToDataBase(UserDto userDto) {
        if (StringUtils.hasLength(userDto.getLogin()) && StringUtils.hasLength(userDto.getPassword())) {
            userDto.setPassword(encoder.encode(userDto.getPassword()));
            userDto.setRole(Role.ROLE_USER);
            userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
        } else throw new IllegalArgumentException("incorrect data entered, please check you login or password");
    }

    public List<UserDto> getAllUsers() {
        List<UserEntity> userEntityList = userRepository.findAllByIdNotNull();
        return userMapper.toDto(userEntityList);
    }

    public void changeRole(UUID id, String type) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow();
        Role role = converterStringToRole.converterStringToRole(type);
        userEntity.setRole(role);
        userRepository.save(userEntity);
    }
}
