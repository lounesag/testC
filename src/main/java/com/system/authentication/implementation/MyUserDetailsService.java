package com.system.authentication.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.dao.PersonDao;
import com.dao.impl.UserDao;
import com.exception.CustomException;
import com.model.Person;
import com.model.User;

/**
 * Implements Spring Security {@link UserDetailsService} that is injected into authentication provider in configuration XML.
 * It interacts with domain, loads user details and wraps it into {@link UserContext} which implements Spring Security {@link UserDetails}.
 */
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private PersonDao userDao;

	/**
	 * This will be called from
	 * {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider#retrieveUser(java.lang.String, org.springframework.security.authentication.UsernamePasswordAuthenticationToken)}
	 * when {@link AuthenticationService#authenticate(java.lang.String, java.lang.String)} calls
	 * {@link AuthenticationManager#authenticate(org.springframework.security.core.Authentication)}. Easy.
	 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(" *** MyUseDetailService.loadUserByUsername");
		Person user;
		try {
			user = userDao.getByLogin(username);
		} catch (CustomException e) {
			throw new UsernameNotFoundException("User not found",e);
		}
		return new UserContext(user);
	}
}
