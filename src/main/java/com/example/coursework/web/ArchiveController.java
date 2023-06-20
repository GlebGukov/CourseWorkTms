package com.example.coursework.web;

import com.example.coursework.models.PostNewsEntity;
import com.example.coursework.service.NewsService;
import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor

@Controller
@RequestMapping("/archive")
public class ArchiveController {
    private final NewsService newsService;

    @GetMapping()
    public String getArchiveNews(Model model) {
        List<PostNewsEntity> newsArchive = newsService.newsListArchive();
        model.addAttribute("newsArchive", newsArchive);
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
