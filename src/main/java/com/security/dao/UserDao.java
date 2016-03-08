package com.security.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.security.entity.Users;

/**
 * Interface for User Repository
 * @author Pranav
 *
 */


public interface UserDao extends CrudRepository<Users, Long>{

	public List<Users> findByEmail(String email);
	
}
