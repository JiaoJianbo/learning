package com.bravo.demo.resources;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.bravo.demo.api.Saying;
import com.codahale.metrics.annotation.Timed;

/*
 * Warning:
 * Resource classes are used by multiple threads concurrently. 
 * In general, we recommend that resources be stateless/immutable, but it’s important to keep the context in mind.
 */

// Tells Jersey that this resource is accessible at the URI /hello-world
@Path("/hello-world")
// Lets Jersey’s content negotiation code know that this resource produces representations which are application/json
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
	private final String template;
	private final String defaultName;
	private final AtomicLong counter;
	
	public HelloWorldResource(String template, String defaultName) {
		super();
		this.template = template;
		this.defaultName = defaultName;
		this.counter = new AtomicLong();
	}
	
	@GET
	@Timed
	public Saying sayHello(@QueryParam("name") Optional<String> name) {
		final String value = String.format(template, name.orElse(defaultName));
		return new Saying(counter.incrementAndGet(), value);
	}
}
