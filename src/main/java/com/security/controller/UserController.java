package com.security.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.security.entity.AccessRights;
import com.security.entity.Users;
import com.security.service.UserService;

/**
 * UserController
 * 
 * @author Pranav
 *
 */

@Controller
public class UserController {
	
	
	@Autowired 
	private UserService userService;
	
    public static final String LOGGED_MESSAGE = "logged in";

	/**
	 * Load welcome page
	 * @return
	 */
	
	@RequestMapping(value="/")
	public String getLoginPage(){
		
		return "index";
	}
	
	/**
	 * Get login user details
	 * @return
	 */
	
	@RequestMapping(value="/home/getUser.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getUser(){
		Map<String , Object> map = new HashMap<String, Object>();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user=(User) auth.getPrincipal();
		
		Users users =userService.getByEmail(user.getUsername()); 
		
		Set<AccessRights> accessRights= users.getRoleList();
		List<String> rights= new ArrayList<String>();
 		for (AccessRights accessRight : accessRights) {
			rights.add(accessRight.getAuthority());
		}
		
		map.put("authority", rights);
		map.put("email", user.getUsername());
		map.put("firstName", users.getFirstName());
		map.put("lastName", users.getLastName());
		
		return map;
	}
	
	/**
	 * Save User
	 * @param user
	 * 
	 */
	
	@RequestMapping(value="/register.do",method=RequestMethod.POST)
	@ResponseBody
	public void register(@RequestBody Users user){
		userService.saveUser(user);
	}

}

