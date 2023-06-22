package com.example.coursework.web;

import com.example.coursework.dto.CommentsDto;
import com.example.coursework.dto.PostNewsDto;
import com.example.coursework.models.PostNewsEntity;
import com.example.coursework.models.TypeOfNews;
import com.example.coursework.repositories.CommentRepository;
import com.example.coursework.repositories.PostNewsRepository;
import com.example.coursework.service.impl.NewsServiceImpl;
import org.assertj.core.api.Assertions;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class NewsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private NewsServiceImpl newsService;
    @Autowired
    private PostNewsRepository repository;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    @WithMockUser(authorities = "read")
    void news() throws Exception {
        var pathVariable = "World";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/news/{variable}",pathVariable))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
        String viewName = mvcResult.getModelAndView().getViewName();
        Object title = mvcResult.getModelAndView().getModel().get("title");
        Object typeNews = mvcResult.getModelAndView().getModel().get("typeNews");
        Assertions.assertThat(viewName).isEqualTo("news-header");
        Assertions.assertThat(title).isEqualTo(pathVariable);
        Assertions.assertThat(typeNews).isNotNull();
    }

    @Test
    @WithMockUser(authorities = "read")
    void newsAdd() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/news/add"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
        String viewName = mvcResult.getModelAndView().getViewName();
        Assertions.assertThat(viewName).isEqualTo("news-add");
    }

    @Test
    @WithMockUser(authorities = "read")
    @Transactional
    void testNewsAddComplete() throws Exception {
        PostNewsDto postNewsDto = instanceNewsDto();
        long count = repository.count();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/news/add")
                        .param("typeNews", "World")
                        .flashAttr("postNewsDto",postNewsDto))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andReturn();
        Assertions.assertThat(repository.count()).isEqualTo(count+1);

    }

    @Test
    @WithMockUser(authorities = "read",username = "Bob")
    @Transactional
    @Disabled
    void newsDetails() throws Exception {
        PostNewsEntity postNewsEntity = repository.findById(UUID.fromString("897546fb-cb54-4aa6-98c3-ba2afb7afb60")).orElseThrow();
        UUID id = postNewsEntity.getId();
        long views = postNewsEntity.getViews();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/news/reading/{variable}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String viewName = mvcResult.getModelAndView().getViewName();
        var news = (PostNewsDto) mvcResult.getModelAndView().getModel().get("news");
        long viewsUpdate = news.getViews();
        Assertions.assertThat(viewName).isEqualTo("news-details");
        Assertions.assertThat(viewsUpdate).isEqualTo(views+1);


    }

    @Test
    @WithMockUser(authorities = "write")
    void testGetPageEditNews() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get
                        ("/news/edit/{id}", UUID.fromString("897546fb-cb54-4aa6-98c3-ba2afb7afb60")))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String viewName = mvcResult.getModelAndView().getViewName();
        PostNewsDto news = (PostNewsDto) mvcResult.getModelAndView().getModel().get("news");
        Assertions.assertThat(viewName).isEqualTo("news-edit");
        Assertions.assertThat(news.getId()).isEqualTo(UUID.fromString("897546fb-cb54-4aa6-98c3-ba2afb7afb60"));
    }

    @Test
    @WithMockUser(authorities = "write")
    @Transactional
    void testEditNews() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/news/edit/{id}",
                        UUID.fromString("897546fb-cb54-4aa6-98c3-ba2afb7afb60"))
                        .flashAttr("postNewsDto",PostNewsDto.builder().anons("testingEditNewsAnons").build()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String viewName = mvcResult.getModelAndView().getViewName();
        PostNewsEntity postNewsEntity = repository.findById(UUID.fromString("897546fb-cb54-4aa6-98c3-ba2afb7afb60")).orElseThrow();
        Assertions.assertThat(viewName).isEqualTo("redirect:/");
        Assertions.assertThat(postNewsEntity.getAnons()).isEqualTo("testingEditNewsAnons");

    }

    @Test
    @WithMockUser(authorities = "write")
    void deleteNews() throws Exception {
        PostNewsDto postNewsDto = instanceNewsDto();
        postNewsDto.setAnons("deleteTest");
        newsService.saveToDataBase(postNewsDto,"Design");
        PostNewsEntity deleteTest = repository.findByAnons("deleteTest");
        UUID id = deleteTest.getId();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/news/delete/{id}", id))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String viewName = mvcResult.getModelAndView().getViewName();
        Assertions.assertThat(viewName).isEqualTo("redirect:/");
        Assertions.assertThat(repository.findByAnons("deleteTest")).isNull();
        System.out.println();
    }

    @Test
    @WithMockUser(authorities = "read",username = "FuturamaAdmin")
    @Disabled
    void addComment() throws Exception {
        PostNewsDto postNewsDto = instanceNewsDto();
        postNewsDto.setAnons("test comment for news");
        newsService.saveToDataBase(postNewsDto,"Design");
        PostNewsEntity postNews = repository.findByAnons("test comment for news");
        long count = commentRepository.count();
        UUID id = postNews.getId();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/news/comment/{id}", id)
                        .param("comment","testComment"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String viewName = mvcResult.getModelAndView().getViewName();
        Assertions.assertThat(viewName).isEqualTo("redirect:/");
        Assertions.assertThat(count).isEqualTo(count+1);

    }

    @AfterEach
    void rollback(){
        repository.deleteByAnons("test of saving news to the database");
        repository.findById(UUID.fromString("897546fb-cb54-4aa6-98c3-ba2afb7afb60")).orElseThrow()
                .setAnons("Она будет использовать только внутренние датчики и не потребует привязки к внешним системам");
        commentRepository.deleteAllByCommentContaining("testComment");
    }

    private static PostNewsDto instanceNewsDto (){
        return PostNewsDto.builder()
                .anons("test of saving news to the database")
                .fullText("test of saving news to the database TEXT")
                .title("test of saving news to the database TITLE")
                .build();
    }
}