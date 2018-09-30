package com.bravo.spring.webflux;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.springframework.web.reactive.function.client.WebClient;

import com.bravo.spring.webflux.entity.Person;

public class Client {
	private static WebClient client = WebClient.create("http://localhost:8080");

	public static void main(String[] args) {
		// all
		client.get().uri("/people")
			.accept(APPLICATION_JSON)
			.exchange()
			.flatMapMany(response -> response.bodyToFlux(Person.class))
			.subscribe(person -> System.out.println("ALL: " + person));
		
		// get
		client.get().uri("/person/{id}", "id-001")
			.accept(APPLICATION_JSON)
			.exchange()
			.flatMap(response -> response.bodyToMono(Person.class))
			.subscribe(person -> System.out.println("GET: " + person));
	}

}
