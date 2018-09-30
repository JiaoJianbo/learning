package com.bravo.spring.webflux.handler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.created;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;

import com.bravo.spring.webflux.entity.Person;
import com.bravo.spring.webflux.repo.PersonRepository;

import reactor.core.publisher.Mono;

@Component
public class PersonHandler {
	@Autowired
	private PersonRepository personRepository;

	public Mono<ServerResponse> get(ServerRequest request){
		UUID id = UUID.fromString(request.pathVariable("id"));
		Mono<Person> person = personRepository.findById(id);
		
		return person.flatMap(p -> ok().contentType(APPLICATION_JSON).body(fromPublisher(person, Person.class)))
				.switchIfEmpty(notFound().build());
	}

	public Mono<ServerResponse> all(ServerRequest request) {
		return ok().contentType(APPLICATION_JSON)
				.body(fromPublisher(personRepository.findAll().map(p -> new Person(p)), Person.class));
	}

	public Mono<ServerResponse> add(ServerRequest request) {
		Mono<Person> person = request.bodyToMono(Person.class);
		UUID id = UUID.randomUUID();
		
		return created(UriComponentsBuilder.fromPath("person/" + id).build().toUri())
				.contentType(APPLICATION_JSON)
				.body(fromPublisher(person.map(p -> new Person(p, id)).flatMap(personRepository::save), Person.class));
	}

	public Mono<ServerResponse> update(ServerRequest request) {
		return null;
	}

	public Mono<ServerResponse> delete(ServerRequest request) {
		return null;
	}
}
