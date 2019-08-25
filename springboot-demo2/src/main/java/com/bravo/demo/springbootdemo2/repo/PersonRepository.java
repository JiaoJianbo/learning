package com.bravo.demo.springbootdemo2.repo;

import com.bravo.demo.springbootdemo2.entity.Person;
import com.bravo.demo.springbootdemo2.entity.PersonKey;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bobby
 * @since 2019/8/25 23:04
 */
public interface PersonRepository extends JpaRepository<Person, PersonKey> {
}
