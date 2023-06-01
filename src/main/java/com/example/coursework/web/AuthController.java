package com.example.coursework.web;

import com.example.coursework.dto.UserDto;
import com.example.coursework.service.UserService;
import com.example.coursework.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.UUID;

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
    public String registration(@ModelAttribute @Valid UserDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/registration";
        }
        userService.saveToDataBase(user);
        return "news-login";
    }
}
