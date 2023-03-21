package com.example.coursework.web;

import com.example.coursework.models.PostNewsEntity;
import com.example.coursework.repositories.PostNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/archive")
public class ArchiveController {
    @Autowired
    private PostNewsRepository postNewsRepository;

    @GetMapping("/{month}")
    public String getArchiveNews(@PathVariable(name = "month") String month,
                                 Model model) {
        Iterable<PostNewsEntity> newsArchive = postNewsRepository.findByArchivedIsTrue();
        model.addAttribute("newsArchive", newsArchive);
        model.addAttribute("month", month);
        return "news-archives";
    }


    @Transactional
    @PostMapping("/{id}")
    public String addNewsToArchive(@PathVariable(name = "id") long id) {
        postNewsRepository.archived(true, id);
        return "redirect:/";
    }

    @Transactional
    @PostMapping("/{id}/toActual")
    public String addNewsToActual(@PathVariable(name = "id") long id) {
        postNewsRepository.archived(false, id);
        return "redirect:/";
    }
}
