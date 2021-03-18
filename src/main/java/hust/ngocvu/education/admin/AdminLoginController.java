package hust.ngocvu.education.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hust.ngocvu.education.dto.LoginUserDto;
import hust.ngocvu.education.repository.UserRepository;


@Controller
public class AdminLoginController {
	
	@Autowired
	UserRepository userrepository;
	
	@GetMapping("/admin/login")
	public String loginPage(@RequestParam(required = false) String error,
			Model model) {
		System.out.println(error);
		if(error!=null && !error.isEmpty()) {
			System.out.println("Username or password is notcorrect!");
			model.addAttribute("message", "Username or password is notcorrect!");
		}
		model.addAttribute("loginUserDto", new LoginUserDto());
		return "login/index";
	}

}
