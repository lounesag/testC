package com.system.authentication.implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.model.Person;
import com.model.Role;
import com.model.User;

/** This object wraps {@link User} and makes it {@link UserDetails} so that Spring Security can use it. */
public class UserContext implements UserDetails {

	private Person user;

	public UserContext(Person user) {
		this.user = user;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList();
//		for (String role : user.getRoles()) {
//			authorities.add(new SimpleGrantedAuthority(role));
//		}
		Role role = user.getRole();
		authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		return authorities;
	}

	public String getPassword() {
		return user.getPassword();
	}

	public String getUsername() {
		return user.getLogin();
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

	public boolean equals(Object o) {
		return this == o
			|| o != null && o instanceof UserContext
			&& Objects.equals(user, ((UserContext) o).user);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(user);
	}

	@Override
	public String toString() {
		return "UserContext{" +
			"user=" + user +
			'}';
	}
}