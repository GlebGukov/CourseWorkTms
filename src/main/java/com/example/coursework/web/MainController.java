package com.example.coursework.web;

import com.example.coursework.dto.PostNewsDto;
import com.example.coursework.service.impl.NewsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class MainController {

    private final NewsServiceImpl service;

    @GetMapping()
    public ModelAndView home() {
        PostNewsDto randomNews = service.getRandomNews();
        PostNewsDto randomWorldNews = service.randomWorldNews();
        PostNewsDto randomDesignNews = service.randomDesignNews();
        return modelAndView("news-homePage")
                .addObject("title", "Home page")
                        .addObject("design", randomDesignNews)
                                .addObject("world", randomWorldNews)
                                        .addObject("news", randomNews);
    }

    private static ModelAndView modelAndView(String view) {
        return new ModelAndView(view);
    }
}
