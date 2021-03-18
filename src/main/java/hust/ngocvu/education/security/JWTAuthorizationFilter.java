package hust.ngocvu.education.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{
	
	private UserDetailsService userDetailsService;
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
			UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain) throws IOException, ServletException{
		final String JWT_SECRET = "chuoi_bi_mat";
		
		//catch token from header of request
		String headerAuthorization = request.getHeader("Authorization");
		System.out.println("JWTAuthorizationFilter headerAuthorization: "+headerAuthorization);
		
		
		//check token is attached with request? 
		//check format (Start by Bearer)
		if(headerAuthorization != null && headerAuthorization.startsWith("Bearer")) {
			try {
				//Bearer is insteaded of "" to take token
				String token = headerAuthorization.replace("Bearer", "");
				System.out.println("JWTAuthorizationFilter: " + token);
				
				//take email from token
				String email = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token)
						.getBody().getSubject();
				System.out.println("Email after resolve token: " + email);
				
				//take information fron DB
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
				
				UsernamePasswordAuthenticationToken authenticationToken = new 
						UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				
			}catch (ExpiredJwtException e) {
				// TODO: handle exception
				System.out.println(" Token expired ");
			}
		}
		chain.doFilter(request, response);
	}

}
