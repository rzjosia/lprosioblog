package com.lprosio.blog.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table
@Data
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    private String author;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

//    @CreationTimestamp
//    private Calendar createdAt;
//
//    @UpdateTimestamp
//    private Calendar updatedAt;

    public Article(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }
}
