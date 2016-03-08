package com.security.authentication.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.security.entity.AccessRights;
import com.security.entity.Users;
import com.security.service.UserService;

/**
 * Authentication service for generate JSON response on failure and success
 * 
 * @author Pranav
 *
 */

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private UserService userService;

	/**
	 * give failure response
	 */

	public void setUnAuthenticationResponse(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		ObjectMapper jsonMapper = new ObjectMapper();
		JSONResponse<HashMap<String, Object>> jsonResponse = new JSONResponse<HashMap<String, Object>>();
		jsonResponse.setStatus(Constants.ERROR);
		jsonResponse.setMessage(Constants.INVALID_CREDENTIAL);

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.print(jsonMapper.writeValueAsString(jsonResponse));
	}

	/**
	 * give success response
	 */

	public void setAuthenticatedResponse(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		JSONResponse<HashMap<String, Object>> jsonResponse = new JSONResponse<HashMap<String, Object>>();

		ObjectMapper jsonMapper = new ObjectMapper();
		Users users = userService.getByEmail(authentication.getName());

		if (users != null) {
			jsonResponse.setStatus(Constants.SUCCESS);
			jsonResponse.setMessage(Constants.SUCCESS_CREDENTIAL);
			HashMap<String, Object> data = new HashMap<String, Object>();
			List<String> roles = new ArrayList<String>();

			for (AccessRights access : users.getRoleList()) {
				roles.add(access.getAuthority());
			}
			data.put("id", users.getUserId());
			data.put("roles", roles);
			jsonResponse.setData(data);
		}

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.print(jsonMapper.writeValueAsString(jsonResponse));
	}

}
