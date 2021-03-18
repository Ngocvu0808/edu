package hust.ngocvu.education.admin;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hust.ngocvu.education.entity.Course;
import hust.ngocvu.education.repository.CategoryRepository;
import hust.ngocvu.education.repository.CourseRepository;

@Controller
public class AdminCourseController {
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@GetMapping("/course/course-list")
	public String course(Model model) {
		model.addAttribute("courses", courseRepository.findAll());
		return "/course/course-list";
	}
	
	
	@GetMapping("/course/course-add")
	public String courseAddGet(Model model) {
		model.addAttribute("course", new Course());
		model.addAttribute("categories", categoryRepository.findAll());
		return "/course/course-add";
	}

	@PostMapping("/course/course-add")
	public String courseAddPost(Model model, @Valid @ModelAttribute("course") Course course, BindingResult errors) {
		// check input
		if (errors.hasErrors()) {
			return "/course/course-add";
		}
		courseRepository.save(course);
		model.addAttribute("message", "Thêm mới thành công!");
		return "redirect:/course/course-list";
	}
	
	@GetMapping("/course/course-edit")
	public String courseEditGet(Model model, @RequestParam int id) {
		Optional<Course> course = courseRepository.findById(id);
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("course", course);
		return "/course/course-edit";
	}

	@PostMapping("/course/course-edit")
	public String courseEditPost(Model model, @Valid @ModelAttribute("course") Course course, BindingResult errors) {
		if (errors.hasErrors()) {
			model.addAttribute("categories", categoryRepository.findAll());
			return "/course/course-edit";
		}
		courseRepository.save(course);
		return "redirect:/course/course-list";
	}

	@GetMapping("course/course-delete")
	public String courseDelete(@RequestParam("id") int id) {
		courseRepository.deleteById(id);
		return "redirect:/course/course-list";
	}

}
