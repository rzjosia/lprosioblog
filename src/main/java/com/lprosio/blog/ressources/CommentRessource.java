package com.lprosio.blog.ressources;

import com.lprosio.blog.entity.Article;
import com.lprosio.blog.entity.Comment;
import com.lprosio.blog.repository.ArticleRepository;
import com.lprosio.blog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;


@Path("article/{articleId}/comment")
public class CommentRessource {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private ArticleRepository articleRepository;

    @PathParam("articleId")
    private long articleId;


    /**
     * Retourne tous les commentaires d'un article
     * qui ont articleId
     *
     * @return Response
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response showAll() {
        Optional<Article> article = articleRepository.findById(articleId);

        // Renvoie un 404 si l'article n'existe pas
        if (!article.isPresent()) {
            return Response.status(404).build();
        }

        // Ajouter quelques commentaires si la table est vide
        if (article.get().getComments().isEmpty()) {

            for (int i = 1; i <= 10; i++) {
                String author = "Com Auteur " + i;
                String content = "Voici un commentaire " + i + " de l'article " + article.get().getId();
                Comment comment = new Comment(author, content);
                comment.setArticle(article.get());
                repository.save(comment);
            }
        }

        return Response
                .status(Response.Status.CREATED)
                .entity(article.get().getComments())
                .build();
    }

    /**
     * Ajouter un commentaire
     *
     * @param comment Comment
     * @return Response
     */
    @PUT
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

    /**
     * Supprimer un commentaire
     *
     * @param id long
     * @return Response
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id) {
        Optional<Comment> comment = repository.findById(id);

        if (comment.isPresent() && comment.get().getArticle().getId() == articleId) {
            repository.deleteById(id);
            return Response.status(200).build();
        }

        return Response.status(404).build();
    }

    /**
     * Afficher un commentaire
     *
     * @param id long
     * @return Response
     */
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
   /* @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id, Comment comment) {

        Optional<Comment> comment = repository.findById(id);

        if (comment.isPresent() && comment.get().getArticle().getId() == articleId) {

            comment.get().setId(id);
            Comment resComment = CommentRepository.save(comment);
        }

        return Response.status(404).build();
    }*/
}
