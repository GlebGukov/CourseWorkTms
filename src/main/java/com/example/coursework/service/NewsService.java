package com.example.coursework.service;

import com.example.coursework.dto.PostNewsDto;

public interface NewsService {

    PostNewsDto saveToDB(PostNewsDto postNewsDto, String type);

    PostNewsDto makeChanges(PostNewsDto postNewsDto, long id);

    void deleteFromDB(long id);

    Iterable<PostNewsDto> getNews(String typeOfNews);

    PostNewsDto toDetails(long id);
}
