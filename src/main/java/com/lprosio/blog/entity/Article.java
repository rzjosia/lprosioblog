package com.lprosio.blog.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

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
    @Lob
    private String content;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<Comment> comments;

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
