package hust.ngocvu.education.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class LoginDto extends User{
	
	private static final long serialVersionUID = 1L;
	
	public LoginDto(String fullname, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(fullname, password, authorities);
	}

}
