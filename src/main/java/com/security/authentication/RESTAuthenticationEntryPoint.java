package com.security.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.security.authentication.service.AuthenticationService;

/**
 * handle unauthorized access at entry point and generate failure response
 * 
 * @author Pranav
 *
 */

@Component
public class RESTAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Autowired
	private AuthenticationService authenticationService;

	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		authenticationService.setUnAuthenticationResponse(request, response);
	}
}