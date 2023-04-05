package com.example.coursework.service.impl;

import com.example.coursework.converter.ConverterStringToTypeNews;
import com.example.coursework.dto.PostNewsDto;
import com.example.coursework.mapper.NewsMapper;
import com.example.coursework.models.PostNewsEntity;
import com.example.coursework.models.TypeOfNews;
import com.example.coursework.repositories.PostNewsRepository;
import com.example.coursework.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class NewsServiceImpl implements NewsService {

    private final PostNewsRepository postNewsRepository;

    private final NewsMapper newsMapper;

    private final ConverterStringToTypeNews converterStringToTypeNews;

    @Override
    public PostNewsDto getNews(UUID id) {
        return newsMapper.toDto(postNewsRepository.findById(id).orElseThrow());
    }

    @Override
    public void saveToDataBase(PostNewsDto postNewsDto, String type) {
        TypeOfNews typeOfNews = converterStringToTypeNews.convertStringToTypeNews(type);
        postNewsDto.setTypeOfNews(typeOfNews);
        newsMapper.toDto(postNewsRepository.save
                (newsMapper.toEntity(postNewsDto)));
    }

    public void makeChanges(UUID id, PostNewsDto postNewsDto) {
        PostNewsEntity postNewsEntity = postNewsRepository.findById(id).orElseThrow();
        postNewsEntity.setAnons(postNewsDto.getAnons());
        postNewsEntity.setTitle(postNewsDto.getTitle());
        postNewsEntity.setFullText(postNewsDto.getFullText());
        postNewsRepository.save(postNewsEntity);
    }

    @Override
    public void deleteFromDataBase(UUID id) {
        postNewsRepository.deleteById(id);
    }

    @Override
    public List<PostNewsDto> getListNews(String type) {
        TypeOfNews typeOfNews = converterStringToTypeNews.convertStringToTypeNews(type);
        List<PostNewsEntity> postNewsEntities =
                postNewsRepository.findByTypeOfNewsAndArchivedIsFalse(typeOfNews);
        return newsMapper.iterableNewsToDto(postNewsEntities);
    }

    @Override
    @Transactional
    public void upView(UUID id) {
        postNewsRepository.updateViews(id);
    }

    @Override
    public List<PostNewsEntity> newsListArchive() {
        return postNewsRepository.findByArchivedIsTrue();
    }

    @Override
    @Transactional
    public void addNewsToArchiveOrActual(boolean arg, UUID id) {
        postNewsRepository.changeParamArchive(arg, id);
    }

    @Override
    public PostNewsDto getRandomNews() {
        PostNewsEntity postNewsEntity = postNewsRepository.randomNewsFromDataBase();
        return newsMapper.toDto(postNewsEntity);
    }
}
