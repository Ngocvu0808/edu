package hust.ngocvu.education.api;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import hust.ngocvu.education.entity.UserCourse;
import hust.ngocvu.education.repository.UserCourseRepository;

@RestController
@RequestMapping("api/userCourse")
@CrossOrigin
public class ApiUserCourseController {
	
	@Autowired
	UserCourseRepository userCourseRepository;
	
	@GetMapping("")
	public Object get() {
		List<UserCourse> userCourses = userCourseRepository.findAll();
		return new ResponseEntity<List<UserCourse>>(userCourses, HttpStatus.OK);
	}
	
	@PostMapping("")
	public Object post(@RequestBody UserCourse userCourse) {
		UserCourse _UserCourse = userCourseRepository.save(userCourse);
		return new ResponseEntity<UserCourse>(_UserCourse, HttpStatus.CREATED);
	}
	
	@DeleteMapping("delete/{course_id}/{user_id}")
	public Object delete(@PathVariable int course_id, @PathVariable int user_id) {
		userCourseRepository.deleteByCourseIdAndUserID(course_id, user_id);
		return  new ResponseEntity<String>("Deleted! ", HttpStatus.OK);
	}
	
	@PutMapping("{course_id}/{user_id}")
	public Object put(@PathVariable int course_id, @PathVariable int user_id, 
			@RequestBody UserCourse userCourse) {
		try {
			if(userCourseRepository.findByCourseIdAndUserID(course_id, user_id) == null) {
				return new ResponseEntity<String>("User_id or course_id is invalid",
						HttpStatus.BAD_REQUEST);
			}
			userCourseRepository.saveAndFlush(userCourse);
			return new ResponseEntity<String>("Updated! ", HttpStatus.OK);
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Update failed!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("{course_id}/{user_id}")
	public Object fingById(@PathVariable int course_id, @PathVariable int user_id) {
		UserCourse userCourse = userCourseRepository.findByCourseIdAndUserID(course_id, user_id);
		return new ResponseEntity<UserCourse>(userCourse, HttpStatus.OK);
	}
	
	@GetMapping("{user_id}")
	public Object findByUserId(@PathVariable int user_id) {
		List<UserCourse> courses = userCourseRepository.findByUserId(user_id);
		return new ResponseEntity<List<UserCourse>>(courses, HttpStatus.OK);
	}

}
