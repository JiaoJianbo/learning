package com.bravo.demo.ssm.dao;

import java.util.List;

import com.bravo.demo.ssm.entity.User;

//@Mapper 
public interface UserDao {

	List<User> getAllUser();

}
