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

import hust.ngocvu.education.entity.Video;
import hust.ngocvu.education.repository.CourseRepository;
import hust.ngocvu.education.repository.VideoRepository;


@Controller
public class AdminVideoController {

	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	VideoRepository videoRepository;
	
	@GetMapping("/video/video-list")
	public String video(Model model) {
		model.addAttribute("videos", videoRepository.findAll());
		return "/video/video-list";
	}

	@GetMapping("/video/video-add")
	public String videoAddGet(Model model) {
		model.addAttribute("video", new Video());
		model.addAttribute("courses", courseRepository.findAll());
		return "/video/video-add";
	}

	@PostMapping("/video/video-add")
	public String videoAddPost(Model model, @Valid @ModelAttribute("video") Video video, BindingResult errors) {
		// check input
		if (errors.hasErrors()) {
			model.addAttribute("courses", courseRepository.findAll());
			return "/video/video-add";
		}
		
		videoRepository.save(video);
		return "redirect:/video/video-list";
	}

	@GetMapping("/video/video-edit")
	public String videoEditGet(Model model, @RequestParam int id) {
		Optional<Video> video = videoRepository.findById(id);
		model.addAttribute("courses", courseRepository.findAll());
		model.addAttribute("video", video);
		return "/video/video-edit";
	}

	@PostMapping("/video/video-edit")
	public String videoEditPost(Model model, @Valid @ModelAttribute("video") Video video, BindingResult errors) {
		if (errors.hasErrors()) {
			model.addAttribute("courses", courseRepository.findAll());
			return "/video/video-edit";
		}
		videoRepository.save(video);
		return "redirect:/video/video-list";
	}

	@GetMapping("video/video-delete")
	public String videoDelete(@RequestParam("id") int id) {
		videoRepository.deleteById(id);
		return "redirect:/video/video-list";
	}
}
