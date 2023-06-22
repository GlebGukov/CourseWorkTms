package com.example.coursework.service;

import com.example.coursework.dto.PostNewsDto;
import com.example.coursework.models.PostNewsEntity;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Validated

public interface NewsService {

    PostNewsDto getNews(UUID id);

    void saveToDataBase(@Valid PostNewsDto postNewsDto,@NotNull String type);

    void makeChanges(UUID id, PostNewsDto postNewsDto);

    void deleteFromDataBase(UUID id);

    List<PostNewsDto> getListNews(String typeOfNews);

    void upView(UUID id);

    List<PostNewsEntity> newsListArchive();

    void addNewsToArchiveOrActual(boolean arg, UUID id);

    PostNewsDto getRandomNews();

    List<PostNewsDto> getSuggestedNews();

    void publishNews(boolean arg, UUID id);

}
