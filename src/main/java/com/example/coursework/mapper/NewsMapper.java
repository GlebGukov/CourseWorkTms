package com.example.coursework.mapper;

import com.example.coursework.dto.PostNewsDto;
import com.example.coursework.models.PostNewsEntity;
import org.mapstruct.Mapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
@Controller
public interface NewsMapper {

    PostNewsDto toDto(PostNewsEntity postNews);

    PostNewsEntity toEntity(PostNewsDto postNews);

    List<PostNewsEntity> iterableNewsToEntity(List<PostNewsDto> iterableNewsDto);

    List<PostNewsDto> iterableNewsToDto(List<PostNewsEntity> iterableNewsEntity);
}
