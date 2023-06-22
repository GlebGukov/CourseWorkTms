package com.example.coursework.web;

import com.example.coursework.dto.PostNewsDto;
import com.example.coursework.models.TypeOfNews;
import com.example.coursework.security.Role;
import com.example.coursework.service.impl.NewsServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(value = MainController.class)
class MainControllerTest {

    @MockBean
    private NewsServiceImpl service;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void home() throws Exception {
        var postNews = PostNewsDto.builder()
                .id(UUID.randomUUID())
                .fullText("testFullText")
                .title("testTitle")
                .anons("testAnons")
                .typeOfNews(TypeOfNews.World)
                .build();
        Mockito.when(service.getRandomNews()).thenReturn(postNews);
        Mockito.when(service.randomDesignNews()).thenReturn(postNews);
        Mockito.when(service.randomWorldNews()).thenReturn(postNews);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();
        String viewName = modelAndView.getViewName();
        Map<String, Object> model = modelAndView.getModel();
        Object design = model.get("design");
        Assertions.assertThat(viewName).isEqualTo("news-homePage");
        Assertions.assertThat(design).isNotNull();
        Assertions.assertThatObject(model).isNotNull();
    }
    @Test
    void homePageThrowExcIfNewsNull () throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();
    }
}