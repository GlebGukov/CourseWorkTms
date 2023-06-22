package com.example.coursework.web;

import com.example.coursework.dto.UserDto;
import com.example.coursework.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor

@Controller
@RequestMapping
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public ModelAndView login() {
        return modelAndView("news-login");
    }

    @GetMapping("/registration")
    public ModelAndView registration() {
        return modelAndView("registration");

    }

    @PostMapping("/registration")
    public ModelAndView registration(@ModelAttribute UserDto user) {
        userService.saveToDataBase(user);
        return modelAndView("news-login");
    }
    private static ModelAndView modelAndView(String view){
        return new ModelAndView(view);
    }
}
