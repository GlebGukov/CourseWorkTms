package com.example.coursework.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity(name = "news")
public class PostNewsEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    private String title, anons;

    @Column(length = 1000)
    private String fullText;

    private long views = 1;

    @Enumerated(EnumType.STRING)
    private TypeOfNews typeOfNews;

    private boolean archived;
    private boolean approved = false;


}
