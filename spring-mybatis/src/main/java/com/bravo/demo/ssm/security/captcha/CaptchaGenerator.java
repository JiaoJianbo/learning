/**
 * 
 */
package com.bravo.demo.ssm.security.captcha;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author Bobby
 *
 */
public interface CaptchaGenerator {
	Captcha generate(ServletWebRequest request);
}
