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

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class NewsServiceImpl implements NewsService {

    private final PostNewsRepository postNewsRepository;

    private final NewsMapper newsMapper;

    private final ConverterStringToTypeNews converterStringToTypeNews;

    @Override
    public PostNewsDto saveToDataBase(PostNewsDto postNewsDto, String type) {
        TypeOfNews typeOfNews = converterStringToTypeNews.convertStringToTypeNews(type);
        postNewsDto.setTypeOfNews(typeOfNews);
        return newsMapper.toDto(postNewsRepository.save
                (newsMapper.toEntity(postNewsDto)));
    }

    @Override
    public PostNewsDto makeChanges(PostNewsDto postNewsDto, UUID id) {
        PostNewsEntity postNewsEntity = postNewsRepository.findById(id).orElseThrow();
        if (postNewsDto == null) {
            return newsMapper.toDto(postNewsEntity);
        } else {
            postNewsEntity.setAnons(postNewsDto.getAnons());
            postNewsEntity.setTitle(postNewsDto.getTitle());
            postNewsEntity.setFullText(postNewsDto.getFullText());
            return newsMapper.toDto(postNewsRepository.save(postNewsEntity));
        }
    }

    @Override
    public void deleteFromDataBase(UUID id) {
        postNewsRepository.deleteById(id);
    }

    @Override
    public Iterable<PostNewsDto> getNews(String type) {
        TypeOfNews typeOfNews = converterStringToTypeNews.convertStringToTypeNews(type);
        Iterable<PostNewsEntity> postNewsEntities =
                postNewsRepository.findByTypeOfNewsAndArchivedIsFalse(typeOfNews);
        return newsMapper.iterableNewsToDto(postNewsEntities);
    }

    @Override
    @Transactional
    public PostNewsDto toDetails(UUID id) {
        PostNewsEntity postNewsEntity = postNewsRepository.findById(id).orElseThrow();
        postNewsRepository.updateViews(id);
        return newsMapper.toDto(postNewsEntity);
    }

    @Override
    public Iterable<PostNewsEntity> newsArchive() {
        return postNewsRepository.findByArchivedIsTrue();
    }

    @Override
    @Transactional
    public void addNewsToArchiveOrActual(boolean arg, UUID id) {
        postNewsRepository.changeParamArchive(arg, id);
    }
}
