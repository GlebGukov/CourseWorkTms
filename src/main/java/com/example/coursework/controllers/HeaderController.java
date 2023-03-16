package com.example.coursework.controllers;

import com.example.coursework.models.PostNews;
import com.example.coursework.repositories.PostNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class HeaderController {

    @Autowired
    private PostNewsRepository postNewsRepository;

    @GetMapping("/news/{title}")
    public String news(@PathVariable(name = "title") String typeOfNews, Model model) {
        model.addAttribute("title", typeOfNews);
        Iterable<PostNews> postNews = postNewsRepository.findByTitleContainingOrAnonsContaining("#" + typeOfNews, "#" + typeOfNews);
        model.addAttribute("typeNews", postNews);
        return "news-header";
    }

    @GetMapping("/news/add")
    public String newsAdd(Model model) {

        return "news-add";
    }

    @GetMapping("/reading/{id}")
    public String newsDetails(@PathVariable(name = "id") long id, Model model) {
        if (postNewsRepository.existsById(id)) {
            PostNews postNews = postNewsRepository.findById(id).orElse(null);
            model.addAttribute("news", postNews);
            return "news-details";
        } else return "news-error";
    }

    @GetMapping("/aboutMe")
    public String aboutMe(Model model) {
        model.addAttribute("title", "About Me");
        return "about-me";
    }

    @PostMapping("/news/add")
    public String newsPOSTAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String fullText, Model model) {
        PostNews postNews = PostNews.builder().title(title + " #").anons(anons).fullText(fullText).build();
        postNewsRepository.save(postNews);
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

    @GetMapping("/news/edit/{id}")
    public String editGETNews(@PathVariable(name = "id") long id, Model model) {
        if (!postNewsRepository.existsById(id)) {
            return "news-error";
        }
        PostNews postNews = postNewsRepository.findById(id).orElse(null);
        model.addAttribute("news",postNews);
        return "news-edit";
    }
    @PostMapping("/news/edit/{id}")
    public String editNews(@PathVariable(name = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String fullText, Model model) {
        PostNews postNews = postNewsRepository.findById(id).orElseThrow();
        postNews.setTitle(title);
        postNews.setAnons(anons);
        postNews.setFullText(fullText);
        postNewsRepository.save(postNews);
        return "redirect:/";
    }
}

