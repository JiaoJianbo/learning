package com.bravo.demo.ssm.security.session;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import com.bravo.demo.ssm.security.LoginType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @see {@link org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer}
 * {@link org.springframework.security.web.session.SimpleRedirectInvalidSessionStrategy}
 * {@link org.springframework.security.web.session.SimpleRedirectSessionInformationExpiredStrategy}
 */
public class AbstractSessionStrategy {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	private final String destinationUrl;
	
	private LoginType loginType;
	
	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	private boolean createNewSession = true;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public AbstractSessionStrategy(String invalidSessionUrl, LoginType loginType) {
		Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl),
				"url must start with '/' or with 'http(s)'");
		this.destinationUrl = invalidSessionUrl;
		this.loginType = loginType;
	}
	
	public void onSessionInvalid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.debug("Starting new session (if required) and redirecting to '" + destinationUrl + "'");
		if (createNewSession) {
			request.getSession();
		}

		if(loginType == LoginType.REDIRECT) {
			redirectStrategy.sendRedirect(request, response, destinationUrl);
		}else {
			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().write(objectMapper.writeValueAsString(this.buildResponseContent(request)));
		}
	}

	/**
	 * Determines whether a new session should be created before redirecting (to avoid
	 * possible looping issues where the same session ID is sent with the redirected
	 * request). Alternatively, ensure that the configured URL does not pass through the
	 * {@code SessionManagementFilter}.
	 *
	 * @param createNewSession defaults to {@code true}.
	 */
	public void setCreateNewSession(boolean createNewSession) {
		this.createNewSession = createNewSession;
	}
	
	/**
	 * session失效是否是并发导致的
	 * @return
	 */
	protected boolean isConcurrency() {
		return false;
	}
	
	protected Map<String, String> buildResponseContent(HttpServletRequest request) {
		Map<String, String> result = new HashMap<>(4);
		String message = "session已失效";
		
		if(isConcurrency()){
			message = message + "，有可能是并发登录导致的";
		}
		result.put("msg", message);
		return result;
	}
}
