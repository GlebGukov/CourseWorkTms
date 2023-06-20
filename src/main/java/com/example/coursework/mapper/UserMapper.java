package com.example.coursework.mapper;

import com.example.coursework.dto.UserDto;
import com.example.coursework.models.UserEntity;
import com.example.coursework.security.UserDetailsImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface UserMapper {

    UserDto toDto(UserEntity userEntity);

    UserEntity toEntity(UserDto userDto);

    List<UserDto> toDto(List<UserEntity> userEntityList);

    UserDetailsImpl toUserDetails(UserEntity userEntity);

    UserEntity toUserEntity(UserDetailsImpl userDetails);
}
