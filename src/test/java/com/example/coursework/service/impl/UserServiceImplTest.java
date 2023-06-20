package com.example.coursework.service.impl;

import com.example.coursework.converter.ConverterStringToRole;
import com.example.coursework.dto.UserDto;
import com.example.coursework.mapper.UserMapper;
import com.example.coursework.models.UserEntity;
import com.example.coursework.repositories.UserRepository;
import com.example.coursework.security.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private ConverterStringToRole converterStringToRole;
    @Mock
    private PasswordEncoder encoder;
    @InjectMocks
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
                .status(true)
                .build();
        UserEntity userEntity = userMapper.toEntity(user);
        //when
        userService.saveToDataBase(user);
        //then
        Mockito.verify(userRepository, Mockito.times(1)).save(userEntity);
    }

    @Test
    void savedUserHasTheUserRole() {
        //given
        var user = UserDto.builder()
                .email("test@mail.ru")
                .login("testLogin")
                .first_name("testFName")
                .last_name("testLName")
                .id(UUID.randomUUID())
                .status(true)
                .role(Role.ROLE_USER)
                .build();
        //when
        userService.saveToDataBase(user);
        //then
        Assertions.assertEquals(user.getRole(), Role.ROLE_USER);
    }

    @Test
    void getAllUsers() {
        //given
        List<UserEntity> allUsersEntity = List.of(new UserEntity(),new UserEntity(),new UserEntity());
        List<UserDto> allUsersDto = List.of(new UserDto(),new UserDto(),new UserDto());

        Mockito.when(userRepository.findAllByIdNotNull()).thenReturn(allUsersEntity);
        Mockito.when(userMapper.toDto(allUsersEntity)).thenReturn(allUsersDto);

        //when and then
        Assertions.assertEquals(allUsersDto,userService.getAllUsers());
    }

    @Test
    void changeRole() {
        //given
        var userEntity = UserEntity.builder()
                .role(Role.ROLE_USER)
                .build();
        var newRole = "ADMIN";
        Mockito.when(converterStringToRole.converterStringToRole(newRole)).thenReturn(Role.ROLE_ADMIN);
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(userEntity));
        //when
        userService.changeRole(UUID.randomUUID(),newRole);
        //then
        assert userEntity != null;
        Assertions.assertEquals(Role.ROLE_ADMIN,userEntity.getRole());
    }

    @Test
    void banUser() {
        //given
        var userEntity = UserEntity.builder()
                .status(true)
                .build();
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(userEntity));
        //when
        userService.banUser(UUID.randomUUID());
        //then
        assert userEntity != null;
        Assertions.assertNotEquals(true,userEntity.getStatus());
    }
}