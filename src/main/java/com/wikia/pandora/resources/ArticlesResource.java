package com.wikia.pandora.resources;

import com.codahale.metrics.annotation.Timed;
import com.wikia.pandora.api.Article;

import javax.ws.rs.*;

@Path("/articles/{title}")
@Produces("application/hal+json")
public class ArticlesResource {


    @GET
    @Timed
    public Article get(@PathParam("title") String title) throws java.io.IOException {
        return new Article.Builder()
                .id(10)
                .title(title)
                .content(String.format("%s content", title))
                .build();
    }
}

