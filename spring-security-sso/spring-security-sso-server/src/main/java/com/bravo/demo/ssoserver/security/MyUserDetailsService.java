package com.bravo.demo.ssoserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author: Bobby
 * @since 2019/5/7 17:43
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //登录时输入任何用户名皆可，密码为1

        return new User("user", passwordEncoder.encode("1"), //如果是从数据库查出的那就是密文了
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
    }

}
