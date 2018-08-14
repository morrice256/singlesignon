package com.morrice.SingleSignOn.foundation.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.morrice.SingleSignOn.user.repository.model.User;

public class CustomUserDetails implements UserDetails {
	
	private static final long serialVersionUID = -4917622218223543573L;
	
	private User user;
 
    public CustomUserDetails(User user) {
        this.user = user;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.user.getEnabled();
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.user.getEnabled();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.user.getEnabled();
	}

	@Override
	public boolean isEnabled() {
		return this.user.getEnabled();
	}
}