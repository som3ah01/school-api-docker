package com.metadata.school.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metadata.school.entity.UserAuthDetails;

@Repository
public interface UserAuthDetailRepo extends JpaRepository<UserAuthDetails, Integer> , Serializable{
	
	UserAuthDetails findByUsername(String username);

}
