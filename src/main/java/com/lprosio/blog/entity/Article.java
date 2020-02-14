package com.lprosio.blog.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

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

    /**
     * Un article peu contenir plusieurs commentaires
     */
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<Comment> comments;

    public Article(String author, String title, String content) {
        super();
        this.author = author;
        this.title = title;
        this.content = content;
    }

}
