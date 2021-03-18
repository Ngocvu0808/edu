package hust.ngocvu.education.api;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hust.ngocvu.education.dto.LoginUserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("api")
@CrossOrigin("*")
public class ApiLoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("login")
	public ResponseEntity<String> login(@RequestBody LoginUserDto userLogin, HttpServletResponse response){
		
		final String JWT_SECRET= "chuoi_bi_mat";
		//step1: login
		System.out.println("Client email: " + userLogin.getEmail());
		System.out.println("Client password: " + userLogin.getPassword());
		//Create object contain email, password
		Authentication authenticationToken = new UsernamePasswordAuthenticationToken(userLogin.getEmail(),
				userLogin.getPassword());
		
		//Login
		try {
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			//Step 2: if login success create token
			//if failed return error to client 
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			
			Date now = new Date();
			
			//create token
			
			String token = Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(now) //time publish
					.setExpiration(new Date(now.getTime() + 1500))//time alive
					.signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
			
			response.setHeader("Authorization", "Bearer " + token);
			System.out.println("Token: " + token);
			response.addHeader("Authorization", "Bearer " + token);
			System.out.println(response.getHeader("Authorization"));
			
			return new ResponseEntity<String>(token, HttpStatus.OK);
		}catch (AuthenticationException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Email or password is not correct! ", HttpStatus.BAD_REQUEST);
	}

}
