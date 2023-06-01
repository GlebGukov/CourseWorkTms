package com.example.coursework.mapper;

import com.example.coursework.dto.PostNewsDto;
import com.example.coursework.models.PostNewsEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface NewsMapper {

    PostNewsDto toDto(PostNewsEntity postNews);

    PostNewsEntity toEntity(PostNewsDto postNews);

    List<PostNewsDto> toListNewsDto(List<PostNewsEntity> listNewsEntity);
}
