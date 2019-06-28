package com.bravo.demo.ssm.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bravo.demo.ssm.entity.SysRole;
import com.bravo.demo.ssm.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysRoleTest {
	@Autowired
	private SysRoleDao sysRoleDao;

	@Test
	public void testGetRoleById() {
		SysRole role = sysRoleDao.getRoleById(1);
		System.out.println(role);
		System.out.println(role.getUsers());
	}
	
	@Test
	public void testGetAllUserByRoldId() {
		List<User> users = sysRoleDao.getAllUserByRoldId(1);
		users.forEach(System.out::println);
	}
	
	@Test
	public void testGetRoleByIdWithUsers() {
		SysRole role = sysRoleDao.getRoleByIdWithUsers(1);
		System.out.println(role.getName());
		//System.out.println(role.getUsers());
	}
	
}
