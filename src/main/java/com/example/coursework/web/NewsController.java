package com.example.coursework.web;

import com.example.coursework.dto.PostNewsDto;
import com.example.coursework.service.impl.CommentService;
import com.example.coursework.service.impl.NewsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor

@Controller
@RequestMapping("/news")
public class NewsController {

    private final NewsServiceImpl newsService;
    private final CommentService commentService;

    @GetMapping("/{title}")
    @PreAuthorize("hasAuthority('read')")
    public String news(@PathVariable(name = "title") String type, Model model) {
        model.addAttribute("title", type);
        List<PostNewsDto> news = newsService.getListNews(type);
        model.addAttribute("typeNews", news);
        return "news-header";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('read')")
    public String newsAdd() {
        return "news-add";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('read')")
    public String newsAdd(PostNewsDto postNewsDto, @RequestParam String typeNews) {
        newsService.saveToDataBase(postNewsDto, typeNews);
        return "redirect:/";
    }

    @GetMapping("/reading/{id}")
    @PreAuthorize("hasAuthority('read')")
    public String newsDetails(@PathVariable(name = "id") UUID id, Model model) {
        newsService.upView(id);
        PostNewsDto news = newsService.getNews(id);
        model.addAttribute("news", news);
        return "news-details";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('write')")
    public String editNews(@PathVariable(name = "id") UUID id, Model model) {
        PostNewsDto postNewsDto = newsService.getNews(id);
        model.addAttribute("news", postNewsDto);
        return "news-edit";
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('write')")
    public String editNews(@PathVariable(name = "id") UUID id, PostNewsDto postNewsDto) {
        newsService.makeChanges(id, postNewsDto);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('write')")
    public String deleteNews(@PathVariable(name = "id") UUID id) {
        newsService.deleteFromDataBase(id);
        return "redirect:/";
    }
    @PostMapping("/comment/{id}")
    @PreAuthorize("hasAuthority('read')")
    public String addComment(@PathVariable(name = "id") UUID id,String comments) {

        commentService.setComment(id,comments);
        return "redirect:/";

    }
}

