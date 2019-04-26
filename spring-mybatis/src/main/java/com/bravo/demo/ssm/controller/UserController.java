package com.bravo.demo.ssm.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
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
	
	@GetMapping()
	public List<User> listUsers() {
		return userService.listAllUser();
	}
	
	/*
	 * 获得当前用户信息的方法：
	 * 1. 直接返回 SecurityContextHolder.getContext().getAuthentication();
	 * 2.方法参数为Authentication类型，直接返回该参数
	 * 3.如只需要 Authentication 中用户信息相关内容(Principal)，将方法参数改为 @AuthenticationPrincipal UserDetails 类型，然后直接返回该参数
	 */
	@GetMapping("/me")
	/*// 方法一
	public Object getCurrentUser() {
		return SecurityContextHolder.getContext().getAuthentication(); //返回一个 Authentication对象
	}
	*/
	/*// 方法二
	public Object getCurrentUser(Authentication authentication) { // Spring 会自动注入
		return authentication;
	}
	*/
	// 方法三
	public UserDetails getCurrentUser(@AuthenticationPrincipal UserDetails user) { // Spring 会自动注入
		return user;
	}
	
	
	@GetMapping("/{userId}")
	public User getUser(@PathVariable(name="userId", required = true) String userId) {
		log.debug("Call getUser version 1.");
		User user = userService.getUserById(userId);
		//log.debug(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));
		log.debug(ReflectionToStringBuilder.toString(user, ToStringStyle.DEFAULT_STYLE));
		return user;
	}

	//在request header中设置 Content-Type="application/json;version=2"
	@GetMapping(value = "/{userId}", consumes = "application/json;version=2")
	public User getUserV2(@PathVariable("userId") String userId) {
		log.debug("Call getUser version 2.");
		User user = userService.getUserById(userId);
		log.debug(ReflectionToStringBuilder.toString(user, ToStringStyle.SHORT_PREFIX_STYLE));
		return user;
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
	
	@GetMapping("/username/{username}")
	public User getUserByName(@PathVariable(name="username", required = true) String username) {
		log.debug("Call getUserByName.");
		User user = userService.getUserByName(username);
		log.debug(ReflectionToStringBuilder.toString(user, ToStringStyle.DEFAULT_STYLE));
		return user;
	}
	
	// 要设置请求header X-XSRF-TOKEN 和 Content-Type的值
	@PostMapping()
	public ResponseEntity<User> addUser(@Valid @RequestBody User user, BindingResult errors) {
		// 如果不加 BindingResult 参数的话，只要 valid 不通过，就根本不会进入到方法体
		log.debug("Create user {}", user.toString());
		
		if(errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> log.error(error.getDefaultMessage()));
			// return ;
		}
		userService.createUser(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void addUser(@RequestParam("username") String username, @RequestParam("password") String password) {
		log.debug("create user [username={}, password={}]", username, password);
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		userService.createUser(user);
	}
	
	@DeleteMapping("/{userId}")
	public int deleteUser(@PathVariable("userId") String userId) {
		log.debug("Delete user, id is {}", userId);
		return userService.delByUserId(userId);
	}
	
	@PutMapping("/{userId}")
	// 使用Postman 测试时，要设置请求header X-XSRF-TOKEN={{xsrf-token}} 和 Content-Type=application/json。xsrf-token是设置的变量名
	public void updateUser(@Valid @RequestBody User user, BindingResult errors) {
		log.debug("Update user, id is {}, new User is {}", user.getId(), user.toString());
		if(errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> log.error(error.getDefaultMessage()));
		}
	}
	
	//Overloaded POST
	//或者使用名称为_method的隐藏域 <input type="hidden" name="_method" value="PUT"> 和 HiddenHttpMethodFilter
	@PostMapping(value = "/{userId}", headers = {"X-HTTP-Method-Override=PUT"})
	public void updateUserViaPost(@RequestBody User user) {
		log.debug("Update user, id is {}, new User is {}", user.getId(), user.toString());
	}
	
}
