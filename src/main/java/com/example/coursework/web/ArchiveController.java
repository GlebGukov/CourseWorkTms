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
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor

@Controller
@RequestMapping("/archive")
public class ArchiveController {
    private final NewsService newsService;

    @GetMapping()
    public ModelAndView getArchiveNews() {
        List<PostNewsEntity> newsArchive = newsService.newsListArchive();
        return modelAndView("news-archives").addObject("newsArchive", newsArchive);
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('write')")
    public ModelAndView addNewsToArchive(@PathVariable(name = "id") UUID id) {
        newsService.addNewsToArchiveOrActual(true, id);
        return modelAndView("redirect:/");
    }

    @Transactional
    @PostMapping("/{id}/toActual")
    @PreAuthorize("hasAuthority('write')")
    public ModelAndView addNewsToActual(@PathVariable(name = "id") UUID id) {
        newsService.addNewsToArchiveOrActual(false, id);
        return modelAndView("redirect:/");
    }
    private static ModelAndView modelAndView(String view){
        return new ModelAndView(view);
    }
}
