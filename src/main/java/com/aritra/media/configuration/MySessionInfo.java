package com.aritra.media.configuration;


import com.aritra.media.domain.User;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;



@Repository
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MySessionInfo {

	private User user;

	public User getCurrentUser() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		//System.out.println("******** "+ name);
		if(!name.equalsIgnoreCase("anonymousUser"))
		{
			//System.out.println("inside if "+name);
			user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		
		return user;
	}
}