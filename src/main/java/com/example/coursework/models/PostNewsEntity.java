package com.example.coursework.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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
    @Column(length = 1000)
    private String fullText;
    private long views = 1;
    @Enumerated(EnumType.STRING)
    private TypeOfNews typeOfNews;
    private boolean archived;
    private boolean approved = false;
    @OneToMany(mappedBy = "postNews",cascade = CascadeType.ALL,orphanRemoval = true)
    @ToString.Exclude
    private List<CommentsEntity> comments;


}
