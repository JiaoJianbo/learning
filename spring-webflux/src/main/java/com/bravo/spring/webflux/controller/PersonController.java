package com.bravo.spring.webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bravo.spring.webflux.entity.Person;
import com.bravo.spring.webflux.repo.PersonRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class PersonController {
	@Autowired
	private PersonRepository personRepository;
	
//	@GetMapping("/people")
//	public Flux<Person> getAll(){
//		return personRepository.findAll();
//	}
//	
//	@GetMapping("/person/{id}")
//	public Mono<Person> findById(@PathVariable String id){
//		return personRepository.findOne(id);
//	}
}
