package com.security.dao;

import org.springframework.data.repository.CrudRepository;

import com.security.entity.AccessRights;

/**
 * Interface for AccessRights Repository
 * @author Pranav
 *
 */


public interface AccessRightsDao extends CrudRepository<AccessRights, Long>{

	
}
