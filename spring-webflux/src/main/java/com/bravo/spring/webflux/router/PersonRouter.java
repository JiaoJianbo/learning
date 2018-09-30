package com.bravo.spring.webflux.router;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.bravo.spring.webflux.handler.PersonHandler;

@Configuration
public class PersonRouter {
	
	@Bean
	public RouterFunction<ServerResponse> route(PersonHandler personHandler){
		return RouterFunctions.route(GET("/people").and(accept(APPLICATION_JSON)), personHandler::all)
				.andRoute(GET("/person/{id}").and(accept(APPLICATION_JSON)), personHandler::get)
				.andRoute(POST("/person").and(accept(APPLICATION_JSON)).and(contentType(APPLICATION_JSON)), personHandler::add)
				.andRoute(PUT("/person/{id}").and(accept(APPLICATION_JSON)).and(contentType(APPLICATION_JSON)), personHandler::update)
				.andRoute(DELETE("/person/{id}"), personHandler::delete);
	}
}
