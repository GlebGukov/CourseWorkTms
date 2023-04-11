package com.example.coursework.web;

import com.example.coursework.dto.PostNewsDto;
import com.example.coursework.service.impl.NewsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class MainController {

    private final NewsServiceImpl service;

    @GetMapping()
    public String home(Model model) {
        model.addAttribute("title", "Home page");
        PostNewsDto randomNews = service.getRandomNews();
        PostNewsDto randomWorldNews = service.randomWorldNews();
        PostNewsDto randomDesignNews = service.randomDesignNews();
        model.addAttribute("design", randomDesignNews);
        model.addAttribute("world", randomWorldNews);
        model.addAttribute("news", randomNews);
        return "home";
    }

    @PostMapping()
    public String getHome(Model model) {
        model.addAttribute("title", "Home page");
        return "home";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        return null;
    }
}
