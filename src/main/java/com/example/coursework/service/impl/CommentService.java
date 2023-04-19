package com.example.coursework.service.impl;

import com.example.coursework.mapper.CommentsMapper;
import com.example.coursework.models.CommentsEntity;
import com.example.coursework.models.PostNewsEntity;
import com.example.coursework.models.UserEntity;
import com.example.coursework.repositories.CommentRepository;
import com.example.coursework.repositories.PostNewsRepository;
import com.example.coursework.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PostNewsRepository newsRepository;
    private final CommentsMapper commentsMapper;

    public void setComment(UUID idNews, String comment) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity userEntity = userRepository.findByLogin(login);
        PostNewsEntity newsEntity = newsRepository.findById(idNews).orElseThrow();

        CommentsEntity commentsEntity = CommentsEntity.builder()
                .comment(comment)
                .user(userEntity)
                .postNews(newsEntity)
                .date(LocalDateTime.now())
                .build();
        newsEntity.getComments().add(commentsEntity);
        newsRepository.save(newsEntity);
    }
}
