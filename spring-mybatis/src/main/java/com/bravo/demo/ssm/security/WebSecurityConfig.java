package com.bravo.demo.ssm.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.bravo.demo.ssm.security.captcha.CaptchaFilter;
import com.bravo.demo.ssm.security.session.MyInvalidSessionStrategy;
import com.bravo.demo.ssm.security.session.MySessionInformationExpiredStrategy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;
	@Autowired
	private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
	@Autowired
	private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
	
	@Autowired
	private DataSource dataSource; //Remember-Me JdbcTokenRepositoryImpl 使用
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	@Autowired
	private InvalidSessionStrategy invalidSessionStrategy;
	@Autowired
	private SessionInformationExpiredStrategy expiredSessionStrategy;
	
	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;
	
//	@Autowired
//	private MessageSource messageSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		// 这里不用显示的配置，Spring 可以为我们自动配置。
		//auth.userDetailsService(new MyUserDetailsService())
		//	.passwordEncoder(passwordEncoder());
	}

	// 在这里配置哪些页面不需要认证
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**", "/js/**", "/css/**", "/images/**", "/**/favicon.ico"); //, "/swagger-ui.html"
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http);
		CaptchaFilter captchaFilter = new CaptchaFilter();
		captchaFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
		captchaFilter.setSecurityProperties(securityProperties);
		//改用 Spring Security 自己的国际化 
		//captchaFilter.setMessageSource(messageSource);
		//captchaFilter.setMyLocaleResolver(new MyLocaleResolver());
		captchaFilter.afterPropertiesSet();
		
		http.addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class)
			.formLogin()
				.loginPage("/tologin")
				//.loginPage("/login.html") //自定义login页面。如果写html页面，会直接跳转，不会走controller
				.usernameParameter("username")
				.passwordParameter("password")
				//.failureUrl("/tologin?error") //默认就是这个
				.loginProcessingUrl("/authentication/form") //login form 的 action 地址
				.successHandler(myAuthenticationSuccessHandler) //如果配置了defaultSuccessUrl，那么自定义的successHandler 不起作用
				//.defaultSuccessUrl("/")
				.failureHandler(myAuthenticationFailureHandler)
			.and().rememberMe() // Remember-Me 功能在内存数据库模式下不起作用，因为服务重启存储在库里的token信息将丢失
				.tokenRepository(this.persistentTokenRepository())
				.tokenValiditySeconds(securityProperties.getRememberMeSeconds())
				.userDetailsService(myUserDetailsService)
			.and().sessionManagement()
				.invalidSessionUrl(securityProperties.getInvalidSessionUrl()) // session 失效跳转到URL
				//.invalidSessionStrategy(invalidSessionStrategy) // Session 超时。每次登录时和 logout之后都会执行invalidSessionStrategy??
				.maximumSessions(securityProperties.getMaxSessions()) // 最大session并发数
				.maxSessionsPreventsLogin(securityProperties.isMaxSessionsPreventsLogin()) // 超过最大并发session数后,是否阻止最新登录
				.expiredSessionStrategy(expiredSessionStrategy) // Session 失效
				.and()
			.and().authorizeRequests()
				.antMatchers(
					"/h2-console/**",
					"/tologin", 
					"/login.html",
					securityProperties.getLoginUrl(),
					"/captcha/image", //图片验证码
					"/druid/**", //DruidDataSource监控
					securityProperties.getInvalidSessionUrl(),
					securityProperties.getExpiredSessionUrl(),
					"/tologin?logout",
					securityProperties.getLogoutUrl()
				)
				.permitAll()
				.anyRequest().authenticated()
			.and().logout()
				//.logoutUrl("/logout") //If CSRF protection is enabled (default), then the request must also be a POST. 所以页面上logout最好用POST请求
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) //get方式 logout
				.invalidateHttpSession(true)
				//.logoutSuccessUrl("/tologin?logout") //logout成功后，返回login页面. The default is /login?logout. This will setup the SimpleUrlLogoutSuccessHandler under the covers.
				.logoutSuccessHandler(logoutSuccessHandler) //If this is specified, logoutSuccessUrl(String) is ignored.
				//.addLogoutHandler(logoutHandler) //Adds a LogoutHandler. The SecurityContextLogoutHandler is added as the last LogoutHandler by default.
				//.deleteCookies(cookieNamesToClear) //This is a shortcut for adding a CookieClearingLogoutHandler explicitly.
				.permitAll();
		
		/* 默认情况下CookieCsrfTokenRepository将编写一个名为 XSRF-TOKEN的cookie和从头部命名 X-XSRF-TOKEN中读取或HTTP参数 _csrf。
		 * 示例显式地设置cookieHttpOnly=false. 这是必要的,允许JavaScript(例如AngularJS)读取它。
		 * 如果你不需要使用JavaScript直接读取cookie的能力，建议省略 cookieHttpOnly=false (通过使用new CookieCsrfTokenRepository()代替) 提高安全性。
		 * 
		 * 将CSRF token写入cookie之后，就可以采用下面的方式使用postman测试POST, PUT, DELETE 类型的HTTP请求了。默认GET, HEAD, TRACE, OPTIONS的请求不需要CSRF token.
		 * https://stackoverflow.com/questions/27182701/how-do-i-send-spring-csrf-token-from-postman-rest-client
		 */
//		http.csrf().
//			csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()); // CSRF token将被写入cookie，且允许客户端JavaScript从cookie中读取该值
		http.csrf()
			.csrfTokenRepository(new CookieCsrfTokenRepository()) // CSRF token将被写入cookie. 客户端JavaScript不能从cookie中读取该值
			.ignoringAntMatchers("/h2-console/**", "/druid/**");
		// 暂时禁用 CSRF
//		http.csrf().disable();
		
		http.headers()
			.frameOptions().sameOrigin(); // 解决 in a frame because it set 'X-Frame-Options' to 'deny'问题。对同一domain的frame不检查
	}

	// 声明一个 passwordEncoder，Security 在校验密码时，就会使用该encoder对用户输入的密码加密，然后同数据库中密文进行比对
	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); //BCryptPasswordEncoder 对某个字符串每次加密的结果都是不同的，但是不影响验证
	}

	// Remember-Me 功能在内存数据库模式下不起作用，因为服务重启存储在库里的token信息将丢失
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		// tokenRepository.setCreateTableOnStartup(true); 这个表我们提前创建好
		return tokenRepository;
	}
	
	// 跟上面直接 new 的不同，采用这种方式就让 Spring 容器管理bean
//	@Bean
//	public CaptchaFilter captchaFilter() throws ServletException {
//		CaptchaFilter captchaFilter = new CaptchaFilter();
//		captchaFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
//		captchaFilter.setSecurityProperties(securityProperties);
//		captchaFilter.afterPropertiesSet();
//		
//		return captchaFilter;
//	}
	
	
	@Bean
	@ConditionalOnMissingBean(InvalidSessionStrategy.class)
	public InvalidSessionStrategy invalidSessionStrategy() {
		return new MyInvalidSessionStrategy(securityProperties.getInvalidSessionUrl(), securityProperties.getLoginType());
	}
	
	@Bean
	@ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
	public SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
		return new MySessionInformationExpiredStrategy(securityProperties.getExpiredSessionUrl(), securityProperties.getLoginType());
	}
	
	@Bean
	@ConditionalOnMissingBean(LogoutSuccessHandler.class)
	public LogoutSuccessHandler logoutSuccessHandler() {
		return new MyLogoutSuccessHandler(securityProperties.getLogoutUrl(), securityProperties.getLoginType());
	}
}
