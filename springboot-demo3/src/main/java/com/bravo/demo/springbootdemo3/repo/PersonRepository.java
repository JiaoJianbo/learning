package com.bravo.demo.springbootdemo3.repo;

import com.bravo.demo.springbootdemo3.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Bobby
 * @since 2019/10/13 14:28
 */
@RepositoryRestResource(collectionResourceRel = "person", path = "person")
public interface PersonRepository extends JpaRepository<Person, Long> {
}
