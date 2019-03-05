package com.bravo.demo;

import com.bravo.demo.health.TemplateHealthCheck;
import com.bravo.demo.resources.HelloWorldResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DropwizardApplication extends Application<DropwizardConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropwizardApplication().run(args);
    }

    @Override
    public String getName() {
        return "Dropwizard";
    }

    @Override
    public void initialize(final Bootstrap<DropwizardConfiguration> bootstrap) {
        // TODO: application initialization
    }

    /*
     * In its run method we can read the template and default name from the DropwizardConfiguration instance, 
     * create a new HelloWorldResource instance, and then add it to the application’s Jersey environment.
     */
    @Override
    public void run(final DropwizardConfiguration configuration,
                    final Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(
    		configuration.getTemplate(), 
    		configuration.getDefaultName()
        );

		final TemplateHealthCheck healthCheck = 
				new TemplateHealthCheck(configuration.getTemplate());

		environment.healthChecks().register("template", healthCheck);
		environment.jersey().register(resource);
    }

}
