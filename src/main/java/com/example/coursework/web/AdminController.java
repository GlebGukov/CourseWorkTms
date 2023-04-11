package com.example.coursework.web;

import com.example.coursework.dto.PostNewsDto;
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
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('write')")
public class AdminController {
    private final NewsService newsService;

    @GetMapping()
    @PreAuthorize("hasAuthority('write')")
    public String adminPage() {

        return "news-adminPage";
    }

    @GetMapping("/suggestion")
    @PreAuthorize("hasAuthority('write')")
    public String viewSuggestedNews(Model model) {
        List<PostNewsDto> suggestedNews = newsService.getSuggestedNews();
        model.addAttribute("news", suggestedNews);
        return "news-suggested";
    }

    @Transactional
    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('write')")
    public String publishNews(@PathVariable(name = "id") UUID id) {
        newsService.publishNews(true, id);
        return "redirect:/";
    }
}
