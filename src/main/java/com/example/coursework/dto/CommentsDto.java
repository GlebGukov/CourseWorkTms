package com.example.coursework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CommentsDto {

    @Size(min = 5, max = 500, message = "Min 5 characters, max - 500 ")
    private String comment;
    private LocalDateTime date;
    private PostNewsDto postNews;
    private UserDto user;
}
