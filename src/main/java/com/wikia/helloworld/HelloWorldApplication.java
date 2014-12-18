package com.wikia.helloworld;


import com.wikia.pandora.api.filters.ResponseInspectionFilter;
import com.wikia.pandora.resources.ArticlesResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.wikia.helloworld.resources.HelloWorldResource;
import com.wikia.helloworld.health.TemplateHealthCheck;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );

        final ArticlesResource articles = new ArticlesResource();

        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());


        environment.jersey().register(resource);
        environment.jersey().register(articles);
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().getResourceConfig().getContainerResponseFilters().add(new ResponseInspectionFilter());
    }

}
