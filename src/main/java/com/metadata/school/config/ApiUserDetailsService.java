package com.metadata.school.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.metadata.school.entity.UserAuthDetails;
import com.metadata.school.repo.UserAuthDetailRepo;

@Service
public class ApiUserDetailsService implements UserDetailsService{
	@Autowired
	private UserAuthDetailRepo userAuthDetailRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAuthDetails user = userAuthDetailRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new ApiUserPrincipal(user);
	}

}
