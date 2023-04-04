package com.example.coursework.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(NoSuchElementException.class)
    private String processError(NoSuchElementException ex, Model model) {
        model.addAttribute("ex", ex);
        return "news-error";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private String processError(IllegalArgumentException ex, Model model) {
        System.out.println(ex.getMessage());
        model.addAttribute("ex", ex);
        return "news-error";
    }
}