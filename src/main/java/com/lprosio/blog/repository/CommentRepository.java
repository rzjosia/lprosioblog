package com.lprosio.blog.repository;

import com.lprosio.blog.entity.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}
