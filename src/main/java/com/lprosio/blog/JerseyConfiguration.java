package com.lprosio.blog;

import com.lprosio.blog.ressources.ArticleRessource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("rest")
@Configuration
public class JerseyConfiguration extends ResourceConfig {
    public JerseyConfiguration() {
        register(CorsFilter.class);
        register(ArticleRessource.class);

        property(ServletProperties.FILTER_FORWARD_ON_404, true);
    }
}
