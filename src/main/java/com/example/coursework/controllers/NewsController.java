package com.example.coursework.controllers;

import com.example.coursework.TypeOfNews;
import com.example.coursework.dto.PostNewsDto;
import com.example.coursework.mapper.NewsMapper;
import com.example.coursework.models.PostNewsEntity;
import com.example.coursework.repositories.PostNewsRepository;
import com.example.coursework.converter.ConverterStringToType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller()
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private PostNewsRepository postNewsRepository;
    @Autowired
    private ConverterStringToType converterStringToType;
    @Autowired
    private NewsMapper newsMapper;

    @GetMapping("/{title}")
    public String news(@PathVariable(name = "title") String type, Model model) {
        model.addAttribute("title", type);
        TypeOfNews typeOfNews = converterStringToType.convertStringToTypeNews(type);
        Iterable<PostNewsEntity> newsEntityIterable = postNewsRepository.findByTypeOfNewsAndArchivedIsFalse(typeOfNews);
        Iterable<PostNewsDto> postNewsDto = newsMapper.iterableNewsToDto(newsEntityIterable);
        model.addAttribute("typeNews", postNewsDto);
        return "news-header";
    }

    @GetMapping("/add")
    public String newsAdd() {

        return "news-add";
    }

    @PostMapping("/add")
    public String newsAdd(@RequestParam String title,
                          @RequestParam String anons,
                          @RequestParam String fullText,
                          @RequestParam String typeNews) {
        TypeOfNews typeOfNews = converterStringToType.convertStringToTypeNews(typeNews);
        PostNewsDto postNewsDto = PostNewsDto.builder()
                .title(title)
                .anons(anons)
                .fullText(fullText)
                .typeOfNews(typeOfNews)
                .build();
        PostNewsEntity postNewsEntity = newsMapper.toEntity(postNewsDto);
        postNewsRepository.save(postNewsEntity);
        return "redirect:/";
    }

    @Transactional
    @GetMapping("/reading/{id}")
    public String newsDetails(@PathVariable(name = "id") long id, Model model) {
        if (postNewsRepository.existsById(id)) {
            PostNewsEntity postNewsEntity = postNewsRepository.findById(id).orElseThrow();
            postNewsRepository.updateViews(id);
            PostNewsDto postNewsDto = newsMapper.toDto(postNewsEntity);
            model.addAttribute("news", postNewsDto);
            return "news-details";
        } else return "news-error";
    }

    @GetMapping("/edit/{id}")
    public String editNews(@PathVariable(name = "id") long id, Model model) {
        if (!postNewsRepository.existsById(id)) {
            return "news-error";
        }
        PostNewsEntity postNewsEntity = postNewsRepository.findById(id).orElse(null);
        PostNewsDto postNewsDto = newsMapper.toDto(postNewsEntity);
        model.addAttribute("news", postNewsDto);
        return "news-edit";
    }

    @PostMapping("/edit/{id}")
    public String editNews(@PathVariable(name = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String fullText, Model model) {
        PostNewsEntity postNewsEntity = postNewsRepository.findById(id).orElseThrow();
        postNewsEntity.setTitle(title);
        postNewsEntity.setAnons(anons);
        postNewsEntity.setFullText(fullText);
        postNewsRepository.save(postNewsEntity);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteNews(@PathVariable(name = "id") long id) {
        if (!postNewsRepository.existsById(id)) {
            return "news-error";
        }
        postNewsRepository.deleteById(id);
        return "redirect:/";
    }
}

