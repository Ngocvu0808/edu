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

import hust.ngocvu.education.entity.Target;
import hust.ngocvu.education.repository.CourseRepository;
import hust.ngocvu.education.repository.TargetRepository;


@Controller
public class AdminTargetController {

	@Autowired
	TargetRepository targetRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	@GetMapping("/target/target-list")
	public String target(Model model) {
		model.addAttribute("targets", targetRepository.findAll());
		return "/target/target-list";
	}

	@GetMapping("/target/target-add")
	public String targetAddGet(Model model) {
		model.addAttribute("target", new Target());
		model.addAttribute("courses", courseRepository.findAll());
		return "/target/target-add";
	}

	@PostMapping("/target/target-add")
	public String targetAddPost(Model model, @Valid @ModelAttribute("target") Target target, BindingResult errors) {
		// Kiểm tra nhập liệu
		if (errors.hasErrors()) {
			model.addAttribute("courses", courseRepository.findAll());
			return "/target/target-add";
		}
		model.addAttribute("message", "Thêm mới thành công!");
		targetRepository.save(target);
		return "redirect:/target/target-list";
	}

	@GetMapping("/target/target-edit")
	public String targetEditGet(Model model, @RequestParam int id) {
		Optional<Target> target = targetRepository.findById(id);
		model.addAttribute("courses", courseRepository.findAll());
		model.addAttribute("target", target);
		return "/target/target-edit";
	}

	@PostMapping("/target/target-edit")
	public String targetEditPost(Model model, @Valid @ModelAttribute("target") Target target, BindingResult errors) {
		if (errors.hasErrors()) {
			model.addAttribute("courses", courseRepository.findAll());
			return "/target/target-edit";
		}
		targetRepository.save(target);
		return "redirect:/target/target-list";
	}

	@GetMapping("target/target-delete")
	public String targetDelete(@RequestParam("id") int id) {
		targetRepository.deleteById(id);
		return "redirect:/target/target-list";
	}
}
