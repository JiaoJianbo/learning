package com.bravo.demo.ssm.service;

import java.util.List;

import com.bravo.demo.ssm.entity.User;


public interface UserService {
	
	List<User> listAllUser();
	
	User getUserById(String userId);

	User getUserByName(String username);
}
