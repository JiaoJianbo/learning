package com.bravo.spring.webflux.repo;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.bravo.spring.webflux.entity.Person;

@Repository
//public interface PersonRepository extends ReactiveCassandraRepository <Person, UUID> {
public interface PersonRepository extends ReactiveCrudRepository <Person, UUID> {

}
