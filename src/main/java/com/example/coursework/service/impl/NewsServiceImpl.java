package com.example.coursework.service.impl;

import com.example.coursework.TypeOfNews;
import com.example.coursework.converter.ConverterStringToType;
import com.example.coursework.dto.PostNewsDto;
import com.example.coursework.mapper.NewsMapper;
import com.example.coursework.models.PostNewsEntity;
import com.example.coursework.repositories.PostNewsRepository;
import com.example.coursework.service.NewsService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@NoArgsConstructor

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private PostNewsRepository postNewsRepository;
    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private ConverterStringToType converterStringToType;

    @Override
    public PostNewsDto saveToDB(PostNewsDto postNewsDto, String type) {
        TypeOfNews typeOfNews = converterStringToType.convertStringToTypeNews(type);
        postNewsDto.setTypeOfNews(typeOfNews);
        return newsMapper.toDto(postNewsRepository.save
                (newsMapper.toEntity(postNewsDto)));
    }

    @Override
    public PostNewsDto makeChanges(PostNewsDto postNewsDto, long id) {
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
    public void deleteFromDB(long id) {
        postNewsRepository.deleteById(id);
    }

    @Override
    public Iterable<PostNewsDto> getNews(String type) {
        TypeOfNews typeOfNews = converterStringToType.convertStringToTypeNews(type);
        Iterable<PostNewsEntity> postNewsEntities =
                postNewsRepository.findByTypeOfNewsAndArchivedIsFalse(typeOfNews);
        return newsMapper.iterableNewsToDto(postNewsEntities);
    }

    @Override
    public PostNewsDto toDetails(long id) {
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
    public void addNewsToArchiveOrActual(boolean arg, long id) {
        postNewsRepository.changeParamArchive(arg,id);
    }
}
