package com.example.coursework.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity(name = "news")
public class PostNewsEntity {

    @Id
    private UUID id;
    private String title;
    private String anons;
    private String fullText;
    private long views = 1;
    @Enumerated(EnumType.STRING)
    private TypeOfNews typeOfNews;
    private boolean archived;
    private boolean approved = false;
    @OneToMany(mappedBy = "postNews", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<CommentsEntity> comments;


}
