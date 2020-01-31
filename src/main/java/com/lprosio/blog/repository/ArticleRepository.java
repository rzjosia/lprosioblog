package com.lprosio.blog.repository;

import com.lprosio.blog.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}
