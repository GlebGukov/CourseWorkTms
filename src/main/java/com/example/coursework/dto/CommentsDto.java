package com.example.coursework.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CommentsDto {

    private String comment;
    private LocalDateTime date;
    @JsonBackReference
    private PostNewsDto postNews;
    @JsonBackReference
    private UserDto user;
}
