package com.example.coursework.web;

import com.example.coursework.dto.UserDto;
import com.example.coursework.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "news-login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute UserDto user) {
        userService.saveToDataBase(user);
        return "news-login";
    }
}
