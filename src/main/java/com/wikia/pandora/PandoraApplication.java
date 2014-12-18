package com.wikia.pandora;


import com.wikia.pandora.api.filters.ResponseInspectionFilter;
import com.wikia.pandora.resources.ArticlesResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.wikia.helloworld.resources.HelloWorldResource;
import com.wikia.helloworld.health.TemplateHealthCheck;

public class PandoraApplication extends Application<PandoraConfiguration> {
    public static void main(String[] args) throws Exception {
        new PandoraApplication().run(args);
    }

    @Override
    public String getName() {
        return "Pandora";
    }

    @Override
    public void initialize(Bootstrap<PandoraConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(PandoraConfiguration configuration,
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
        environment.jersey().register(HalMessageBodyWriter.class);
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().getResourceConfig().getContainerResponseFilters().add(new ResponseInspectionFilter());
        //environment.jersey().getResourceConfig().getProviderClasses().add(HalMessageBodyWriter.class);
        System.out.println(environment.jersey().getResourceConfig().getProviderClasses());
    }

}

