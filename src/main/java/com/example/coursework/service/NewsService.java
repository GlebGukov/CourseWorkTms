package com.example.coursework.service;

import com.example.coursework.dto.PostNewsDto;
import com.example.coursework.models.PostNewsEntity;

import java.util.UUID;

public interface NewsService {

    PostNewsDto saveToDataBase(PostNewsDto postNewsDto, String type);

    PostNewsDto makeChanges(PostNewsDto postNewsDto, UUID id);

    void deleteFromDataBase(UUID id);
//
    Iterable<PostNewsDto> getNews(String typeOfNews);

    PostNewsDto toDetails(UUID id);

    Iterable<PostNewsEntity> newsArchive();

    void addNewsToArchiveOrActual(boolean arg, UUID id);
}
