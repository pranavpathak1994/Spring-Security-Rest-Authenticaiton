package com.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.dao.AccessRightsDao;
import com.security.dao.UserDao;
import com.security.entity.AccessRights;
import com.security.entity.Users;

/**
 * User Service 
 * @author pranav
 *
 */

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AccessRightsDao accessRightsDao;
	/**
	 * Save user
	 */
	
	public void saveUser(Users user) {

		userDao.save(user);
		
		AccessRights accessRights= new AccessRights();
		accessRights.setAuthority("Admin");
		accessRights.setUser(user);
		
		accessRightsDao.save(accessRights);
	
	}
	
	/**
	 * Get User By Email
	 */

	public Users getByEmail(String email) {
		
		
		List<Users> users= userDao.findByEmail(email);
		
		Users user= new Users();
		
		if(users.size()!=0){
			user=users.get(0);
		}
		
		return user;
	}

	
}
