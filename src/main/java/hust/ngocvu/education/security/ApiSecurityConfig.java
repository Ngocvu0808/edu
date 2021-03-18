package hust.ngocvu.education.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import hust.ngocvu.education.repository.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@ComponentScan("hust.ngocvu.education")
@Order(1)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder1() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManager();}
	
	//declare what service catch user info from DB and declare method
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder1());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.cors().disable();
		http.authorizeRequests().antMatchers("/api/**").access("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')");
		
		http.csrf().disable().antMatcher("/api/role/**") //authorize the api start urls
		
				.authorizeRequests().antMatchers("/api/login", "/api/regidter").permitAll() //allow accesss
				.anyRequest() //the other links requests login
				.authenticated(); //check loged in?
		
		
		//check token and catch info
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), userDetailsService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.logout().logoutSuccessUrl("/home").logoutUrl("/logout").permitAll();
	}

}
