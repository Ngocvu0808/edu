package hust.ngocvu.education.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hust.ngocvu.education.entity.Course;
import hust.ngocvu.education.repository.CourseRepository;

@RestController
@RequestMapping("api/course")
@CrossOrigin
public class ApiCourseController {

	@Autowired
	CourseRepository courseRepository;
	
	@GetMapping("")
	public Object get() {
		List<Course> courses = courseRepository.findAll();
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
	}
	
	@GetMapping("discount")
	public Object getDiscount() {
		List<Course> courses = courseRepository.findCourseDiscount();
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
	}
	
	@GetMapping("not_discount")
	public Object getNotDiscount() {
		List<Course> courses = courseRepository.findCourseNotDiscount();
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
	}
	
	@PostMapping("")
	public Object post(@RequestBody Course course) {
		Course course2 = courseRepository.save(course);
		return new ResponseEntity<Course>(course2, HttpStatus.OK);
	}
	
	@GetMapping("search")
	public Object search(@RequestParam String keyword) {
		List<Course> courses= courseRepository.findByDes(keyword);
		
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
	}
	
	@GetMapping("id")
	public Object findById(@PathVariable int id) {
		Optional<Course> course = courseRepository.findById(id);
		Course course2 = course.get();
		return new ResponseEntity<Course>(course2, HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public Object put(@PathVariable int id, @RequestBody Course course) {
		try {
			if(courseRepository.findById(id) == null || id!= course .getId()) {
				return new ResponseEntity<String>("Id is not valid!", HttpStatus.BAD_REQUEST);
			}
			courseRepository.saveAndFlush(course);
			return new ResponseEntity<String>("Update successed! ", HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>("Update failed!", HttpStatus.BAD_REQUEST);
			// TODO: handle exception
		}
	}
	
	@DeleteMapping("delete/{id}")
	public Object delete(@PathVariable int id) {
		courseRepository.deleteById(id);
		return new ResponseEntity<String>("Deleted!", HttpStatus.OK);
	}
}

