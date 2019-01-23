package com.bravo.demo.ssm.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.bravo.demo.ssm.entity.User;
import com.bravo.demo.ssm.service.UserService;

@RestController
@RequestMapping ("/users")
public class UserController {
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public List<User> listUsers() {
		return userService.listAllUser();
	}
	
	@GetMapping("/{userId}")
	public User getUser(@PathVariable("userId") String userId) {
		log.debug("Call getUser version 1.");
		return userService.getUserById(userId);
	}

	//在request header中设置 Content-Type="application/json;version=2"
	@GetMapping(value = "/{userId}", consumes = "application/json;version=2")
	public User getUserV2(@PathVariable("userId") String userId) {
		log.debug("Call getUser version 2.");
		return userService.getUserById(userId);
	}
	
	//在request header中设置 X-API-Version=3
	@GetMapping(value = "/{userId}", headers= {"X-API-Version=3"})
	public User getUserV3(@PathVariable("userId") String userId, WebRequest request) {
		log.debug("Call getUser version 3.");
		
//		String etag = request.getHeader("ETag");
//		log.debug("ETag === {}", etag);
//		if(request.checkNotModified(etag)) {
//			return null;
//		}
		
		User user = userService.getUserById(userId);
		
//		Timestamp ts = user.getUpdateDate();
//		if(request.checkNotModified(ts.getTime())) {
//			return null;
//		}
		
		return user;
	}
	
	// 要设置请求header X-XSRF-TOKEN 和 Content-Type的值
	@PostMapping()
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		log.debug("Create user {}", user.toString());
		
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void addUser(@RequestParam("username") String username, @RequestParam("password") String password) {
		log.debug("create user [username={}, password={}]", username, password);
	}
	
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") String userId) {
		log.debug("Delete user, id is {}", userId);
	}
	
	@PutMapping("/{userId}")
	// 使用Postman 测试时，要设置请求header X-XSRF-TOKEN={{xsrf-token}} 和 Content-Type=application/json。xsrf-token是设置的变量名
	public void updateUser(@PathVariable("userId") String userId, @Valid @RequestBody User newUser) {
		log.debug("Update user, id is {}, new User is {}", userId, newUser.toString());
	}
	
	//Overloaded POST
	//或者使用名称为_method的隐藏域 <input type="hidden" name="_method" value="PUT"> 和 HiddenHttpMethodFilter
	@PostMapping(value = "/{userId}", headers = {"X-HTTP-Method-Override=PUT"})
	public void updateUserViaPost(@PathVariable("userId") String userId, @RequestBody User newUser) {
		log.debug("Update user, id is {}, new User is {}", userId, newUser.toString());
	}
	
}
