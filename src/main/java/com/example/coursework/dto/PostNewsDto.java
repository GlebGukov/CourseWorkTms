package com.example.coursework.dto;

import com.example.coursework.models.CommentsEntity;
import com.example.coursework.models.TypeOfNews;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PostNewsDto {

    private UUID id;
    private String title, anons;
    private String fullText;
    private long views = 1;
    private TypeOfNews typeOfNews;
    private boolean archived = false;
    private boolean approved = false;
    @JsonManagedReference
    private List<CommentsDto> comments;
}
