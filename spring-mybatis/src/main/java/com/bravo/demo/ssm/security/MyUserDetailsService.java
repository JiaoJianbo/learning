package com.bravo.demo.ssm.security;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
		
		// 这里应该从数据库去获取该用户的权限信息
		Collection<GrantedAuthority> grantedAuths = AuthorityUtils.commaSeparatedStringToAuthorityList("001,002"); 
		
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = !StringUtils.equals("1", user.getLocked());
		// 理论上数据库里应该是加密后的密码，这一步应该在创建用户时完成，在这里直接使用user.getPassword() 即可。
		String encodedPwd = passwordEncoder.encode(user.getPassword());
		logger.debug("数据库中密文是：{}", encodedPwd);
		
		return new User(username, encodedPwd, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);
	}
	
	@Autowired
	private PasswordEncoder passwordEncoder;

}
