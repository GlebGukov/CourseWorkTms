package com.example.coursework.controllers;

import com.example.coursework.TypeOfNews;
import com.example.coursework.models.PostNewsEntity;
import com.example.coursework.repositories.PostNewsRepository;
import com.example.coursework.converter.ConverterStringToType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller()
public class NewsController {

    @Autowired
    private PostNewsRepository postNewsRepository;
    @Autowired
    private ConverterStringToType converterStringToType;

    @GetMapping("/news/{title}")
    public String news(@PathVariable(name = "title") String type, Model model) {
        model.addAttribute("title", type);
        TypeOfNews typeOfNews = converterStringToType.convertStringToTypeNews(type);
        Iterable<PostNewsEntity> byTypeOfNews = postNewsRepository.findByTypeOfNewsAndArchivedIsFalse(typeOfNews);
        model.addAttribute("typeNews", byTypeOfNews);
        return "news-header";
    }

    @GetMapping("/news/add")
    public String newsAdd() {

        return "news-add";
    }

    @PostMapping("/news/add")
    public String newsAdd(@RequestParam String title,
                          @RequestParam String anons,
                          @RequestParam String fullText,
                          @RequestParam String typeNews) {
        TypeOfNews typeOfNews = converterStringToType.convertStringToTypeNews(typeNews);
        PostNewsEntity postNewsEntity = PostNewsEntity.builder()
                .title(title)
                .anons(anons)
                .fullText(fullText)
                .typeOfNews(typeOfNews)
                .archived(false)
                .build();
        postNewsRepository.save(postNewsEntity);
        return "redirect:/";
    }

    @Transactional
    @GetMapping("/reading/{id}")
    public String newsDetails(@PathVariable(name = "id") long id, Model model) {
        if (postNewsRepository.existsById(id)) {
            PostNewsEntity postNewsEntity = postNewsRepository.findById(id).orElseThrow();
            postNewsRepository.updateViews(id);
            model.addAttribute("news", postNewsEntity);
            return "news-details";
        } else return "news-error";
    }

    @GetMapping("/news/edit/{id}")
    public String editNews(@PathVariable(name = "id") long id, Model model) {
        if (!postNewsRepository.existsById(id)) {
            return "news-error";
        }
        PostNewsEntity postNewsEntity = postNewsRepository.findById(id).orElse(null);
        model.addAttribute("news", postNewsEntity);
        return "news-edit";
    }

    @PostMapping("/news/edit/{id}")
    public String editNews(@PathVariable(name = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String fullText, Model model) {
        PostNewsEntity postNewsEntity = postNewsRepository.findById(id).orElseThrow();
        postNewsEntity.setTitle(title);
        postNewsEntity.setAnons(anons);
        postNewsEntity.setFullText(fullText);
        postNewsRepository.save(postNewsEntity);
        return "redirect:/";
    }

    @PostMapping("/news/delete/{id}")
    public String deleteNews(@PathVariable(name = "id") long id) {
        if (!postNewsRepository.existsById(id)) {
            return "news-error";
        }
        postNewsRepository.deleteById(id);
        return "redirect:/";
    }
}

