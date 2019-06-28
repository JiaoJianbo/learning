package com.bravo.demo.ssm.entity;

import java.io.Serializable;
import java.util.List;

public class SysRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7048293713005548715L;

	private Integer id;
	
	private String name;
	
	private String status;
	
	private List<User> users;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "SysRole [id=" + id + ", name=" + name + ", status=" + status + "]";
	}
	
}
