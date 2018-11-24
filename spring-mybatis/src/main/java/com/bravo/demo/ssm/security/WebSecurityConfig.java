package com.bravo.demo.ssm.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		super.configure(auth);
	}

	// 在这里配置哪些页面不需要认证
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**", "/js/**", "/css/**", "/images/**", "/**/favicon.ico");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http);
		
		http.authorizeRequests()
				.anyRequest().authenticated()
			.and().httpBasic()
			.and()
				.formLogin()
				.loginPage("/tologin")
				//.loginPage("/login.html") //自定义login页面。如果写html页面，会直接跳转，不会走controller
				.usernameParameter("username")
				.passwordParameter("password")
				//.failureUrl("/tologin?error") //默认就是这个
				.loginProcessingUrl("/authentication/form") //login form 的 action 地址
				.defaultSuccessUrl("/")
				.permitAll()
			.and()
				.logout()
				//.logoutUrl("/logout") //If CSRF protection is enabled (default), then the request must also be a POST. 所以页面上logout最好用POST请求
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) //get方式 logout
				.invalidateHttpSession(true)
				.logoutSuccessUrl("/tologin?logout") //logout成功后，返回login页面. The default is /login?logout. This will setup the SimpleUrlLogoutSuccessHandler under the covers.
				//.addLogoutHandler(logoutHandler) //If this is specified, logoutSuccessUrl(String) is ignored.
				//.deleteCookies(cookieNamesToClear) //This is a shortcut for adding a CookieClearingLogoutHandler explicitly.
				.permitAll();

		// 暂时禁用 CSRF
//		http.csrf().disable();
		
//		http.csrf().
//			csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}

}
