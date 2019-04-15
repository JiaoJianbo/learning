package com.bravo.demo.ssm.security;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.bravo.demo.ssm.dao.UserDao;

@Component
public class MyUserDetailsService implements UserDetailsService {
	private static Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);
	@Autowired
	private UserDao userDao;

	// 注意下面方法中两个 User 类不一样。一个是我们定义的，一个是SpringSecurity的。
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("Login as: {}", username);
		com.bravo.demo.ssm.entity.User user = userDao.getUserByName(username);
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		Collection<GrantedAuthority> grantedAuths = AuthorityUtils.commaSeparatedStringToAuthorityList("001,002"); 
		
		return new User(user.getUsername(), user.getPassword(), grantedAuths);
	}

}
