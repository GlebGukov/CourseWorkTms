package com.example.coursework.web;

import com.example.coursework.dto.PostNewsDto;
import com.example.coursework.service.impl.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsServiceImpl newsService;

    @GetMapping("/{title}")
    @PreAuthorize("hasAuthority('read')")
    public String news(@PathVariable(name = "title") String type, Model model) {
        model.addAttribute("title", type);
        Iterable<PostNewsDto> news = newsService.getNews(type);
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

    //    @Transactional
    @GetMapping("/reading/{id}")
    @PreAuthorize("hasAuthority('read')")
    public String newsDetails(@PathVariable(name = "id") UUID id, Model model) {
        PostNewsDto postNewsDto = newsService.toDetails(id);
        model.addAttribute("news", postNewsDto);
        return "news-details";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('write')")
    public String editNews(@PathVariable(name = "id") UUID id, Model model) {
        PostNewsDto postNewsDto = newsService.makeChanges(null, id);
        model.addAttribute("news", postNewsDto);
        return "news-edit";
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('write')")
    public String editNews(@PathVariable(name = "id") UUID id,
                           PostNewsDto postNewsDto) {
        newsService.makeChanges(postNewsDto, id);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('write')")
    public String deleteNews(@PathVariable(name = "id") UUID id) {
        newsService.deleteFromDataBase(id);
        return "redirect:/";
    }
}

