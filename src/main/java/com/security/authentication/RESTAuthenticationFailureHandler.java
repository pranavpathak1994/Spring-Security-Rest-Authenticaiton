package com.security.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.security.authentication.service.AuthenticationService;

/**
 * Failure handler to manage authentication failure and generate failure response
 * 
 * @author Pranav
 *
 */

@Component
public class RESTAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		exception.printStackTrace();
		authenticationService.setUnAuthenticationResponse(request, response);
	}
}
