package com.example.coursework.controllers;

import com.example.coursework.models.PostNews;
import com.example.coursework.repositories.PostNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HeaderController {

    @Autowired
    private PostNewsRepository postNewsRepository;

    @GetMapping("/news/{title}")
    public String technology(@PathVariable(name = "title") String typeOfNews, Model model){
        model.addAttribute("title",typeOfNews);
        Iterable<PostNews> postNews = postNewsRepository.findByTitleContainingOrAnonsContaining("#"+typeOfNews,"#"+typeOfNews);
        model.addAttribute("worldNews",postNews);
        return "newsHeader";
    }
    @GetMapping("/newAdd")
    public String newsAdd(Model model){

        return "newsAdd";
    }
//    @GetMapping("/news/${id}")
//    public String newsDetails(@PathVariable(name = "id") long id , Model model){
//        PostNews postNews = postNewsRepository.findById(id).orElse(null);
//        model.addAttribute("news",postNews);
//        return "newsDetails";
//    }

    @GetMapping("/aboutme")
    public String aboutMe(Model model){
        model.addAttribute("title","About Me");
        return "aboutMe";
    }

    @PostMapping("/newsAdd")
    public String newsPostAdd(@RequestParam String title,@RequestParam String anons,@RequestParam String fullText, Model model){
        PostNews postNews = PostNews.builder().title(title).anons(anons).fullText(fullText).build();
        postNewsRepository.save(postNews);
        return "redirect:/";
    }
}
