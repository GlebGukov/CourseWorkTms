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

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/archive")
public class ArchiveController {
    @Autowired
    private NewsService newsService;

    @GetMapping("/{month}")
    public String getArchiveNews(@PathVariable(name = "month") String month,
                                 Model model) {
        List<PostNewsEntity> newsArchive = newsService.newsListArchive();
        model.addAttribute("newsArchive", newsArchive);
        model.addAttribute("month", month);
        return "news-archives";
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('write')")
    public String addNewsToArchive(@PathVariable(name = "id") UUID id) {
        newsService.addNewsToArchiveOrActual(true, id);
        return "redirect:/";
    }

    @Transactional
    @PostMapping("/{id}/toActual")
    @PreAuthorize("hasAuthority('write')")
    public String addNewsToActual(@PathVariable(name = "id") UUID id) {
        newsService.addNewsToArchiveOrActual(false, id);
        return "redirect:/";
    }
}
