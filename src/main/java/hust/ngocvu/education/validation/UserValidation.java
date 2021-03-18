package hust.ngocvu.education.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import hust.ngocvu.education.entity.User;
import hust.ngocvu.education.repository.UserRepository;

@Component
public class UserValidation {
	
	@Autowired
	UserRepository userRepository;
	
	public boolean supports(Class<?> clazz) {
		//check the object is User?
		return User.class.equals(clazz);
	}
	
	public void validate(Object target, Errors errors) {
		//Cast object to User
		User user = (User)target;
		
		if(user.getPassword() == null || user.getPassword().length() == 0) {
			errors.rejectValue("password", "user", "Please enter password!");
		}
		if(!user.getPassword().equals(user.getConfirmpassword())) {
			errors.rejectValue("confirmpassword", "user", "Password is not match!");
		}
		else if(user.getConfirmpassword() == null || user.getConfirmpassword().length() == 0) {
			errors.rejectValue("confirmpassword", "user", "Please confirm password!");
		}
	}

}
