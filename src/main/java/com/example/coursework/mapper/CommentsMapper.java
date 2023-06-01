package com.example.coursework.mapper;

import com.example.coursework.dto.CommentsDto;
import com.example.coursework.models.CommentsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface CommentsMapper {
    CommentsDto toDto(CommentsEntity commentsEntity);
    CommentsEntity toEntity(CommentsDto commentsDto);
}
