package com.example.coursework.service.impl;

import com.example.coursework.converter.ConverterStringToTypeNews;
import com.example.coursework.dto.PostNewsDto;
import com.example.coursework.mapper.NewsMapper;
import com.example.coursework.models.PostNewsEntity;
import com.example.coursework.models.TypeOfNews;
import com.example.coursework.repositories.PostNewsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class NewsServiceImplTest {

    @Mock
    private PostNewsRepository postNewsRepository;
    @Mock
    private NewsMapper newsMapper;
    @Mock
    private ConverterStringToTypeNews converterStringToTypeNews;
    @InjectMocks
    private NewsServiceImpl newsService;

    @Test
    void getNews() {
        //given
        var id = UUID.randomUUID();
        var newsDto = PostNewsDto.builder()
                .id(id)
                .build();
        Mockito.when(postNewsRepository.findById(id)).thenReturn(
                Optional.of(PostNewsEntity.builder()
                        .id(id).build()));
        Mockito.when(newsMapper.toDto(Mockito.any())).thenReturn(newsDto);
        //when and then
        Assertions.assertEquals(newsDto, newsService.getNews(id));
    }

    @Test
    void saveToDataBase() {
        //given
        var type = "World";
        var newsDto = PostNewsDto.builder()
                .id(UUID.randomUUID())
                .anons("testAnons")
                .approved(false)
                .archived(false)
                .title("testTitle")
                .fullText("testText")
                .build();
        PostNewsEntity postNewsEntity = newsMapper.toEntity(newsDto);
        Mockito.when(converterStringToTypeNews.convertStringToTypeNews(type)).thenReturn(TypeOfNews.World);
        //when
        newsService.saveToDataBase(newsDto,type);
        //then
        Mockito.verify(postNewsRepository,Mockito.times(1)).save(postNewsEntity);
    }

    @Test
    void makeChanges() {
        //given
        var newsDto = PostNewsDto.builder()
                .anons("newAnons")
                .fullText("newText")
                .title("newTitle")
                .build();
        var newsEntity = PostNewsEntity.builder()
                .anons("oldAnons")
                .fullText("oldText")
                .title("oldTitle")
                .build();
        Mockito.when(postNewsRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(newsEntity));
        //when
        newsService.makeChanges(UUID.randomUUID(),newsDto);
        //then
        assert newsEntity != null;
        Assertions.assertAll("news",
                () -> Assertions.assertEquals("newAnons",newsEntity.getAnons()),
                () -> Assertions.assertEquals("newTitle",newsEntity.getTitle()),
                () -> Assertions.assertEquals("newText",newsEntity.getFullText()));
    }

    @Test
    void deleteFromDataBase() {
        //given
        var id = UUID.randomUUID();
        //when
        newsService.deleteFromDataBase(id);
        //then
        Mockito.verify(postNewsRepository,Mockito.times(1)).deleteById(id);
    }

    @Test
    void getListNews() {
        //given
        var type = "World";
        Mockito.when(converterStringToTypeNews.convertStringToTypeNews(type)).thenReturn(TypeOfNews.World);
        //when
        newsService.getListNews(type);
        //then
        Mockito.verify(postNewsRepository,Mockito.times(1))
                .findByTypeOfNewsAndArchivedIsFalseAndApprovedIsTrue(TypeOfNews.World);
    }

    @Test
    void upView() {
        //given
        var id = UUID.randomUUID();
        //when
        newsService.upView(id);
        //then
        Mockito.verify(postNewsRepository,Mockito.times(1)).updateViews(id);
    }

    @Test
    void newsListArchive() {
        //when
        newsService.newsListArchive();
        //then
        Mockito.verify(postNewsRepository,Mockito.times(1)).findByArchivedIsTrue();
    }

    @Test
    void addNewsToArchiveOrActual() {
        //given
        var id = UUID.randomUUID();
        //when
        newsService.addNewsToArchiveOrActual(false,id);
        //then
        Mockito.verify(postNewsRepository,Mockito.times(1)).changeParamArchive(false,id);
    }

    @Test
    void getRandomNews() {
        //given
        //when
        newsService.getRandomNews();
        //then
        Mockito.verify(postNewsRepository,Mockito.times(1)).randomNewsFromDataBase();
    }

    @Test
    void randomWorldNews() {
        //when
        newsService.randomWorldNews();
        //then
        Mockito.verify(postNewsRepository,Mockito.times(1)).randomWorldNewsFromDataBase();
    }

    @Test
    void randomDesignNews() {
        //when
        newsService.randomDesignNews();
        //then
        Mockito.verify(postNewsRepository,Mockito.times(1)).randomDesignNewsFromDataBase();
    }

    @Test
    void getSuggestedNews() {
        //when
        newsService.getSuggestedNews();
        //then
        Mockito.verify(postNewsRepository,Mockito.times(1)).findByApprovedFalse();
    }

    @Test
    void publishNews() {
        //given
        var id = UUID.randomUUID();
        //when
        newsService.publishNews(true,id);
        //then
        Mockito.verify(postNewsRepository,Mockito.times(1))
                .publishNews(true,id);
    }
}