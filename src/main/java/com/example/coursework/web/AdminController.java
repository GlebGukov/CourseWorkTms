package com.example.coursework.web;

import com.example.coursework.dto.PostNewsDto;
import com.example.coursework.dto.UserDto;
import com.example.coursework.repositories.UserRepository;
import com.example.coursework.service.NewsService;
import com.example.coursework.service.impl.UserServiceImpl;
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
    private final UserServiceImpl userService;
    private final UserRepository userRepository;

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
        return "news-admin-suggested";
    }

    @Transactional
    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('write')")
    public String publishNews(@PathVariable(name = "id") UUID id) {
        newsService.publishNews(true, id);
        return "redirect:/admin";
    }

    @GetMapping("/browsingUsers")
    @PreAuthorize("hasAuthority('create')")
    public String browsingUsers(Model model) {
        List<UserDto> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);
        return "news-admin-browsingUsers";
    }

    @PostMapping("/{id}/role")
    @PreAuthorize("hasAuthority('create')")
    public String changeRoleUsers(@PathVariable UUID id, String role) {
        userService.changeRole(id, role);
        return "redirect:/";
    }
}
