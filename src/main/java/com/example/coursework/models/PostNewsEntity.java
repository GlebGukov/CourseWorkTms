package com.example.coursework.models;

import com.example.coursework.TypeOfNews;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity(name = "news")
public class PostNewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title, anons;

    @Column(length = 1000)
    private String fullText;

    private long views = 1;

    @Enumerated(EnumType.STRING)
    private TypeOfNews typeOfNews;

    private boolean archived;


}
