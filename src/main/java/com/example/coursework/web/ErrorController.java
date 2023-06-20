package com.example.coursework.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
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
        model.addAttribute("ex", ex);
        return "news-error";
    }

    @ExceptionHandler(ConstraintViolationException.class)
    private String userNotValid(ConstraintViolationException ex, Model model) {
        List<String> error = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage).toList();
        model.addAttribute("error", error);
        return "user-error";
    }
}