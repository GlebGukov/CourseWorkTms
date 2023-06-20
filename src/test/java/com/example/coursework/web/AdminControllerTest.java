//package com.example.coursework.web;
//
//import com.example.coursework.service.NewsService;
//import com.example.coursework.service.impl.UserServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@WebMvcTest(value = AdminController.class)
//class AdminControllerTest {
//
//    @MockBean
//    private NewsService newsService;
//    @MockBean
//    private UserServiceImpl userService;
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    void adminPage() throws Exception {
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/admin"))
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//        mvcResult.
//    }
//
//    @Test
//    void viewSuggestedNews() {
//    }
//
//    @Test
//    void publishNews() {
//    }
//
//    @Test
//    void browsingUsers() {
//    }
//
//    @Test
//    void changeRoleUsers() {
//    }
//}