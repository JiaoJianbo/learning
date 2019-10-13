package com.bravo.demo.springbootdemo3.repo;

import com.bravo.demo.springbootdemo3.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * @author Bobby
 * @since 2019/10/13 14:28
 */
@RepositoryRestResource//(collectionResourceRel = "person", path = "person")
public interface PersonRepository extends JpaRepository<Person, Long> {

    // http://localhost:8080/api/persons/search/names?firstName=Jim
    @RestResource(path = "names")
    List<Person> findByFirstName(String firstName);
}
