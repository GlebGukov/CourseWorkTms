package com.example.coursework.web;

import com.example.coursework.dto.PostNewsDto;
import com.example.coursework.service.impl.CommentService;
import com.example.coursework.service.impl.NewsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    public ModelAndView news(@PathVariable(name = "title") String type) {
        List<PostNewsDto> news = newsService.getListNews(type);
        return modelAndView("news-header")
                .addObject("title", type)
                        .addObject("typeNews", news);
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('read')")
    public ModelAndView newsAdd() {
        return modelAndView("news-add");
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('read')")
    public ModelAndView newsAdd(PostNewsDto postNewsDto,@RequestParam String typeNews) {
        newsService.saveToDataBase(postNewsDto, typeNews);
        return modelAndView("redirect:/");
    }

    @GetMapping("/reading/{id}")
    @PreAuthorize("hasAuthority('read')")
    public ModelAndView newsDetails(@PathVariable(name = "id") UUID id) {
        newsService.upView(id);
        PostNewsDto news = newsService.getNews(id);
        return modelAndView("news-details")
                .addObject("news", news);
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('write')")
    public ModelAndView editNews(@PathVariable(name = "id") UUID id) {
        PostNewsDto postNewsDto = newsService.getNews(id);
        return modelAndView("news-edit")
                .addObject("news", postNewsDto);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('write')")
    public ModelAndView editNews(@PathVariable(name = "id") UUID id, PostNewsDto postNewsDto) {
        newsService.makeChanges(id, postNewsDto);
        return modelAndView("redirect:/");
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('write')")
    public ModelAndView deleteNews(@PathVariable(name = "id") UUID id) {
        newsService.deleteFromDataBase(id);
        return modelAndView("redirect:/");
    }

    @PostMapping("/comment/{id}")
    @PreAuthorize("hasAnyAuthority('read')")
    public ModelAndView addComment(@PathVariable(name = "id") UUID id,@RequestParam(value = "comment") String comment) {
        commentService.setComment(id, comment);
        return modelAndView("redirect:/");
    }
    private static ModelAndView modelAndView(String view) {
        return new ModelAndView(view);
    }
}

