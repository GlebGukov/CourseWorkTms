package com.example.coursework.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity
public class PostNews {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title,anons;
    @Column (length = 1000)
    private String fullText;
    private int views;

}
