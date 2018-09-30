package com.bravo.spring.webflux.entity;

import java.io.Serializable;
import java.util.UUID;

public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1405823740742569711L;
	
	private UUID id;
	
	private String firstName;
	
	private String lastName;
	
	private Integer age;
	
	
	public Person() {
	}

	public Person(Person p) {
	}

	public Person(Person p, UUID id) {
	}

	public Person(UUID id, String firstName, String lastName, Integer age) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
}
