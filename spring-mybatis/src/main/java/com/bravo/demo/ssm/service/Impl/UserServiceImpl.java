package com.bravo.demo.ssm.service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bravo.demo.ssm.dao.UserDao;
import com.bravo.demo.ssm.entity.User;
import com.bravo.demo.ssm.service.UserService;

@Service ("userService")
public class UserServiceImpl implements UserService {
	private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;

	@Override
	public List<User> listAllUser() {
		return userDao.getAllUser();
	}

	@Override
	public User getUserById(String userId) {
		return userDao.getUserById(userId);
	}
	
	

}
