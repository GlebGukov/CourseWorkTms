package com.example.coursework.mapper;

import com.example.coursework.dto.UserDto;
import com.example.coursework.models.UserEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Controller;

@Mapper(
        componentModel = "spring"
)
@Controller
public interface UserMapper {

    UserDto toDto(UserEntity userEntity);

    UserEntity toEntity(UserDto userDto);
}
