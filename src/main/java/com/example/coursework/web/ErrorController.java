package com.example.coursework.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(NoSuchElementException.class)
    private ModelAndView processError(NoSuchElementException ex) {
        return modelAndView("news-error.html").addObject("exs", ex);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ModelAndView processError(IllegalArgumentException ex) {
        return modelAndView("news-error.html").addObject("exs", ex);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    private ModelAndView userNotValid(ConstraintViolationException ex) {
        List<String> error = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage).toList();
        return modelAndView("user-error").addObject("error", error);
    }
    private static ModelAndView modelAndView(String view){
        return new ModelAndView(view);
    }
}