package com.bravo.demo.ssm.security.captcha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bravo.demo.ssm.security.SecurityProperties;

@Configuration
public class CaptchaBeanConfig {
	@Autowired
	private SecurityProperties securityProperties;
	
	// 让验证码生成的逻辑也可配置
	@Bean
	@ConditionalOnMissingBean(name = "imageCaptchaGenerator")
	public CaptchaGenerator imageCaptchaGenerator() {
		ImageCaptchaGenerator imageCaptchaGenerator = new ImageCaptchaGenerator();
		imageCaptchaGenerator.setSecurityProperties(securityProperties);
		return imageCaptchaGenerator;
	}
}
