package com.lprosio.blog.ressources;

import com.lprosio.blog.entity.Article;
import com.lprosio.blog.entity.Comment;
import com.lprosio.blog.repository.ArticleRepository;
import com.lprosio.blog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.Optional;

@Path("article/{articleId}/comment")
public class CommentRessource {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private ArticleRepository articleRepository;

    @PathParam("articleId")
    private long articleId;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response showAll() {
        if (!articleRepository.findById(articleId).isPresent()) {
            return Response.status(404).build();
        }

        Article article = articleRepository.findById(articleId).get();
        Collection<Comment> comments = article.getComments();

        if (comments.isEmpty()) {

            for (int i = 1; i <= 10; i++) {
                String author = "Comm Auteur " + i;
                String content = "Voici un commentaire " + i + " de l'article " + article.getId();
                Comment comment = new Comment(author, content);
                comment.setArticle(article);
                repository.save(comment);
            }
        }

        return Response
                .status(Response.Status.CREATED)
                .entity(comments)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Comment comment) {
        if (!articleRepository.findById(articleId).isPresent()) {
            return Response.status(404).build();
        }

        Article article = articleRepository.findById(articleId).get();
        comment.setArticle(article);
        repository.save(comment);


        return Response.status(200).entity(comment).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id) {
        Optional<Comment> comment = repository.findById(id);

        if (!comment.isPresent() || !articleRepository.findById(articleId).isPresent()) {
            return Response.status(404).build();
        }

        repository.deleteById(id);

        return Response.status(200).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response show(@PathParam("id") long id) {
        Optional<Comment> article = repository.findById(id);

        if (!article.isPresent() || !articleRepository.findById(articleId).isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response
                .status(Response.Status.ACCEPTED)
                .entity(article)
                .build();
    }

}
