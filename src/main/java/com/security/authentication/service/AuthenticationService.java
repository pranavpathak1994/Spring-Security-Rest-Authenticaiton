package com.security.authentication.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

/**
 * 
 * @author Pranav
 *
 */

public interface AuthenticationService {

	/**
	 * Set Unauthentication response
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void setUnAuthenticationResponse(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException;

	/**
	 * set authenticated response
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	public void setAuthenticatedResponse(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException;
}
