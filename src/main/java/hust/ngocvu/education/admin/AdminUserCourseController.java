package hust.ngocvu.education.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hust.ngocvu.education.entity.UserCourse;
import hust.ngocvu.education.repository.CourseRepository;
import hust.ngocvu.education.repository.RoleRepository;
import hust.ngocvu.education.repository.UserCourseRepository;
import hust.ngocvu.education.repository.UserRepository;


@Controller
public class AdminUserCourseController {

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	UserCourseRepository userCourseRepository;
	
	@GetMapping("/usercourse/usercourse-list")
	public String usercourse(Model model) {
		model.addAttribute("usercourses", userCourseRepository.findAll());
		return "userCourse/usercourse-list";
	}
	
	@GetMapping("/usercourse/usercourse-add")
	   public String usercourseAddGet(Model model) {
		model.addAttribute("roles", roleRepository.findAll());
		model.addAttribute("users", userRepository.findAll());
		model.addAttribute("courses", courseRepository.findAll());
		model.addAttribute("usercourses", new UserCourse());
		return "userCourse/usercourse-add";
	}
	
	
	@PostMapping("/usercourse/usercourse-add")
	   public String usercourseAddPost(Model model,@Valid @ModelAttribute("usercourse") UserCourse usercourse, 
			   BindingResult errors) {
		
		
		if(errors.hasErrors()) {
			model.addAttribute("roles", roleRepository.findAll());
			model.addAttribute("users", userRepository.findAll());
			model.addAttribute("courses", courseRepository.findAll());
			return "/userCourse/usercourse-add";
		}
			userCourseRepository.save(usercourse);
			return "redirect:/userCourse/usercourse-list";
		}
	
	
	@GetMapping("/usercourse/usercourse-edit")	
	   public String usercourseEditGet(Model model,  @RequestParam("course_id") int course_id,@RequestParam("user_id") int user_id) {
		model.addAttribute("roles", roleRepository.findAll());
		model.addAttribute("users", userRepository.findAll());
		model.addAttribute("courses", courseRepository.findAll());
		model.addAttribute("usercourse", userCourseRepository.findByCourseIdAndUserID(course_id, user_id));
		return "userCourse/usercourse-edit";
	}
	
	@PostMapping("/usercourse/usercourse-edit")
	   public String usercourseEditPost(Model model,@Valid @ModelAttribute("usercourse") UserCourse usercourse,BindingResult errors) {
		if(errors.hasErrors()) {
			model.addAttribute("roles", roleRepository.findAll());
			model.addAttribute("users", userRepository.findAll());
			model.addAttribute("courses", courseRepository.findAll());
			return "userCourse/usercourse-edit";
		}
		userCourseRepository.save(usercourse);
		return "redirect:userCourse/usercourse-list";
	}
	
	@GetMapping("/usercourse/usercourse-delete")
	  public String deleteusercourse(@RequestParam("course_id") int course_id,@RequestParam("user_id") int user_id) {  
		userCourseRepository.deleteByCourseIdAndUserID(course_id, user_id);  
	    return "redirect:userCourse/usercourse-list";
	  } 
}
