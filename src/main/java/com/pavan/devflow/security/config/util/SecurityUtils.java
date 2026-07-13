package com.pavan.devflow.security.config.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.pavan.devflow.user.entity.User;

@Component
public class SecurityUtils {

	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication == null || !authentication.isAuthenticated()) {
			throw new IllegalStateException("No authenticated user found.");
		}
		
		return (User) authentication.getPrincipal();
	}
	
	public Long getCurrentUserId() {
		return getCurrentUser().getId();
	}
	
	public String getCurrentUserName() {
		return getCurrentUser().getUsername();
	}
}
