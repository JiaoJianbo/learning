package com.bravo.demo.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bravo.demo.ssm.entity.User;

//@Mapper 
public interface UserDao {

	List<User> getAllUser();
	
	User getUserById(@Param ("userId") String userId);

	User getUserByName(@Param ("username") String username);
	
	int insertUser(User user); //返回值为插入的记录数,但是id属性会回填到user中
	
	int delUserById(String userId); //返回删除的行数
}
