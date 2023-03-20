package com.example.coursework.mapper;

import com.example.coursework.dto.PostNewsDto;
import com.example.coursework.models.PostNewsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.springframework.stereotype.Controller;

@Mapper(
        componentModel = "spring"
)
@Controller
public interface NewsMapper {

    PostNewsDto toDto (PostNewsEntity postNews);

    PostNewsEntity toEntity (PostNewsDto postNews);

    Iterable<PostNewsEntity> iterableNewsToEntity (Iterable<PostNewsDto> iterableNewsDto) ;
    Iterable<PostNewsDto> iterableNewsToDto (Iterable<PostNewsEntity> iterableNewsEntity) ;
}
