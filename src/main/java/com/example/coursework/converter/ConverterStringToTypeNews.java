package com.example.coursework.converter;

import com.example.coursework.models.TypeOfNews;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor

@Service
public class ConverterStringToTypeNews {
    public TypeOfNews convertStringToTypeNews(String type) {
        return TypeOfNews.valueOf(TypeOfNews.class, type);
    }
}
