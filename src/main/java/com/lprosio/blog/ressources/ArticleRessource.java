package com.lprosio.blog.ressources;

import com.lprosio.blog.entity.Article;
import com.lprosio.blog.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("article")
public class ArticleRessource {
    @Autowired
    private ArticleRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        Iterable<Article> articles = repository.findAll();

        if (repository.count() <= 0) {
            for (int i = 1; i <= 10; i++) {
                Article article = new Article("Auteur " + i, "Titre " + i, "Contenu de l'article " + i);
                repository.save(article);
            }
        }

        return Response
                .status(Response.Status.CREATED)
                .entity(articles)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Article article) {
        Article register = repository.save(article);
        return Response.status(200).entity(register).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response show(@PathParam("id") long id) {
        Optional<Article> article = repository.findById(id);
        if (!article.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response
                .status(Response.Status.ACCEPTED)
                .entity(article)
                .build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id, Article article) {
        if (!repository.findById(id).isPresent()) {
            return Response.status(404).build();
        }

        article.setId(id);
        Article resArticle = repository.save(article);

        return Response.status(200).entity(resArticle).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id) {
        Optional<Article> article = repository.findById(id);
        if (!article.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        repository.deleteById(id);

        return Response
                .status(Response.Status.ACCEPTED)
                .build();
    }
}
