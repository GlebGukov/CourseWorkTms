package com.example.coursework.web;

import com.example.coursework.dto.PostNewsDto;
import com.example.coursework.dto.UserDto;
import com.example.coursework.service.NewsService;
import com.example.coursework.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.lang.Nullable;
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
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('write')")
public class AdminController {
    private final NewsService newsService;
    private final UserServiceImpl userService;

    @GetMapping()
    @PreAuthorize("hasAuthority('write')")
    public ModelAndView adminPage() {
        return modelAndView("news-adminPage");
    }

    @GetMapping("/suggestion")
    @PreAuthorize("hasAuthority('write')")
    public ModelAndView viewSuggestedNews() {
        List<PostNewsDto> suggestedNews = newsService.getSuggestedNews();
        return modelAndView("news-admin-suggested").addObject("news", suggestedNews);
    }

    @Transactional
    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('write')")
    public ModelAndView publishNews(@PathVariable(name = "id") UUID id) {
        newsService.publishNews(true, id);
        return modelAndView("redirect:/admin");
    }

    @GetMapping("/browsingUsers")
    @PreAuthorize("hasAuthority('create')")
    public ModelAndView browsingUsers() {
        List<UserDto> allUsers = userService.getAllUsers();
        return modelAndView("news-admin-browsingUsers").addObject("users", allUsers);
    }

    @PostMapping("/{id}/role")
    @PreAuthorize("hasAuthority('create')")
    public ModelAndView changeRoleUsers(@PathVariable UUID id, String role) {
        userService.changeRole(id, role);
        return modelAndView("redirect:/");
    }

    @PostMapping("/{id}/ban")
    @PreAuthorize("hasAuthority('create')")
    public ModelAndView banUser(@PathVariable UUID id) {
        userService.banUser(id);
        return modelAndView("redirect:/");
    }
    private static ModelAndView modelAndView(String view){
        return new ModelAndView(view);
    }
}
