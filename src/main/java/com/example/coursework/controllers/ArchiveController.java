package com.example.coursework.controllers;

import com.example.coursework.models.PostNewsEntity;
import com.example.coursework.repositories.PostNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArchiveController {
    @Autowired
    private PostNewsRepository postNewsRepository;

    @GetMapping("/archive/{month}")
    public String getArchiveNews(@PathVariable(name = "month") String month,
                                 Model model) {
        Iterable<PostNewsEntity> newsArchive = postNewsRepository.findByArchivedIsTrue();
        model.addAttribute("newsArchive",newsArchive);
        model.addAttribute("month",month);
        return "news-archives";
    }


    @Transactional
    @PostMapping("/archive/{id}")
    public String addNewsToArchive(@PathVariable(name = "id") long id) {
        postNewsRepository.archived(true,id);
        return "redirect:/";
    }
}
