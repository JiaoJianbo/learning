package com.bravo.demo.ssm.security.captcha;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.util.WebUtils;

@Controller
@RequestMapping("/captcha")
public class CaptchaController {
	
	@Autowired
	private CaptchaGenerator imageCaptchaGenerator;
	
	protected static final String SESSION_CAPTCHA_KEY = "CAPTCHA_KEY";
	protected static final String SESSION_CAPTCHA_EXPIRED_TIME = "CAPTCHA_EXPIRED_TIME";

	@GetMapping("/image")
	public void image(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Captcha captcha = imageCaptchaGenerator.generate(new ServletWebRequest(request));
		//不用将验证码连图片都放到 session 里，只需要 code 和过期时间
		//WebUtils.setSessionAttribute(request, SESSION_CAPTCHA_KEY, captcha);
		WebUtils.setSessionAttribute(request, SESSION_CAPTCHA_KEY, captcha.getCode());
		WebUtils.setSessionAttribute(request, SESSION_CAPTCHA_EXPIRED_TIME, captcha.getExpireTime());
		ImageIO.write(captcha.getImage(), "JPEG", response.getOutputStream());
	}
}
