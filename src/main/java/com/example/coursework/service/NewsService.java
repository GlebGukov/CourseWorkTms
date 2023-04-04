package com.example.coursework.service;

import com.example.coursework.dto.PostNewsDto;
import com.example.coursework.models.PostNewsEntity;

public interface NewsService {

    PostNewsDto saveToDB(PostNewsDto postNewsDto, String type);

    PostNewsDto makeChanges(PostNewsDto postNewsDto, long id);

    void deleteFromDB(long id);

    Iterable<PostNewsDto> getNews(String typeOfNews);

    PostNewsDto toDetails(long id);

    Iterable<PostNewsEntity> newsArchive();

    void addNewsToArchiveOrActual(boolean arg, long id);
}
