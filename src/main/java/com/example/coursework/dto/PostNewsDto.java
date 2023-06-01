package com.example.coursework.dto;

import com.example.coursework.models.TypeOfNews;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Component
public class PostNewsDto {

    private UUID id;

    private String title, anons;

    private String fullText;

    private long views = 1;

    private TypeOfNews typeOfNews;

    private boolean archived = false;
}
