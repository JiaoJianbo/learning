package com.bravo.demo.ssm.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.ResourceServlet;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/*
 * 使用 Druid 数据源时，使用该配置。
 */
@Configuration
//当存在配置spring.profiles.active，且其值中含有druid时该配置类生效
@ConditionalOnProperty(value = "spring.profiles.active", havingValue = "druid")
public class DruidConfig {

	@Bean
	//必须加这个配置，否则配置中Druid自己特有的属性无法绑定到DruidDataSource
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return new DruidDataSource();
	}
	
	//配置Druid 监控 - 1.配置一个管理后台的Servlet
	@Bean
	public ServletRegistrationBean statViewServlet() {
		ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		
		Map<String, String> initParameters = new HashMap<>();
		initParameters.put(ResourceServlet.PARAM_NAME_USERNAME, "admin");
		initParameters.put(ResourceServlet.PARAM_NAME_PASSWORD, "123456");
		initParameters.put(ResourceServlet.PARAM_NAME_ALLOW, ""); //白名单
		initParameters.put(ResourceServlet.PARAM_NAME_DENY, "192.168.1.1,192.168.1.2"); //黑名单
		bean.setInitParameters(initParameters );

		return bean;
	}
	
	//配置Druid 监控 - 2.配置web监控的Filter
	@Bean
	public FilterRegistrationBean webStatFilter() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new WebStatFilter());
		
		Map<String, String> initParameters = new HashMap<>();
		initParameters.put(WebStatFilter.PARAM_NAME_EXCLUSIONS, "/webjars/*,/images/*,*.js,*.css,/druid/*");
		bean.setInitParameters(initParameters);
		bean.setUrlPatterns(Arrays.asList("/*"));
		
		return bean;
	}
}
