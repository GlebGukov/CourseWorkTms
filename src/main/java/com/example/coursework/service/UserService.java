package com.example.coursework.service;

import com.example.coursework.dto.UserDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface UserService {

    void saveToDataBase(@Valid UserDto userDto);

}
