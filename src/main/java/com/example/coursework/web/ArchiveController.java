package com.example.coursework.web;

import com.example.coursework.models.PostNewsEntity;
import com.example.coursework.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private NewsService newsService;

    @GetMapping("/{month}")
    @PreAuthorize("hasAuthority('users:write')")
    public String getArchiveNews(@PathVariable(name = "month") String month,
                                 Model model) {
        Iterable<PostNewsEntity> newsArchive = newsService.newsArchive();
        model.addAttribute("newsArchive", newsArchive);
        model.addAttribute("month", month);
        return "news-archives";
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public String addNewsToArchive(@PathVariable(name = "id") long id) {
        newsService.addNewsToArchiveOrActual(true, id);
        return "redirect:/";
    }

    @Transactional
    @PostMapping("/{id}/toActual")
    @PreAuthorize("hasAuthority('users:write')")
    public String addNewsToActual(@PathVariable(name = "id") long id) {
        newsService.addNewsToArchiveOrActual(false, id);
        return "redirect:/";
    }
}
