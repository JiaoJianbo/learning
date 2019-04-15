package com.bravo.demo.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bravo.demo.ssm.entity.User;

//@Mapper 
public interface UserDao {

	List<User> getAllUser();
	
	User getUserById(@Param ("userId") String userId);

	User getUserByName(@Param ("username") String username);
}
