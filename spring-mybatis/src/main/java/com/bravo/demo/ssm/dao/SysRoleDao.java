package com.bravo.demo.ssm.dao;

import java.util.List;

import com.bravo.demo.ssm.entity.SysRole;
import com.bravo.demo.ssm.entity.User;

public interface SysRoleDao {
	SysRole getRoleById(Integer id);
	
	SysRole getRoleByIdWithUsers(Integer id);
	
	List<User> getAllUserByRoldId(Integer id);
}
