package hust.ngocvu.education.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


public class CustomUserDetails extends User implements UserDetails{
	
	
	private static final long serialVersionUID = 1L;
	
	public CustomUserDetails(String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

}
