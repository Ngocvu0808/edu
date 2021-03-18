package hust.ngocvu.education.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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

import hust.ngocvu.education.entity.Target;
import hust.ngocvu.education.repository.TargetRepository;

@RestController
@RequestMapping("api/target")
@EnableAutoConfiguration
public class ApiTargetController {
	
	@Autowired
	TargetRepository targetRepository;
	
	@GetMapping("")
	public Object get() {
		List<Target> targets = targetRepository.findAll();
		return new ResponseEntity<List<Target>>(targets, HttpStatus.OK);
	}
	
	@PostMapping("")
	public Object post(@RequestBody Target target) {
		Target target2 = targetRepository.save(target);
		return new ResponseEntity<Target>(target2, HttpStatus.CREATED);
	}
	
	@DeleteMapping("delete/{id}")
	public Object delete(@PathVariable int id) {
		targetRepository.deleteById(id);
		return new ResponseEntity<String>("Deleted!", HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public Object findById(@PathVariable int id) {
		Optional<Target> optional = targetRepository.findById(id);
		Target target = optional.get();
		return new ResponseEntity<Target>(target, HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("{course_id}/{order_index}")
	public Object findByCourseIdAndOrderIndex(@PathVariable int course_id,
			@PathVariable int order_index) {
		List<Target> targets = targetRepository.
				findByCourseIdAndOrderIndex(course_id, order_index);
		return new ResponseEntity<List<Target>>(targets, HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public Object put(@PathVariable int id, @RequestBody Target target) { 
		try {
			if(targetRepository.findById(id) == null || id != target.getId()) {
				return new ResponseEntity<String>("Id is invalid!", HttpStatus.BAD_REQUEST);
			}
			targetRepository.saveAndFlush(target);
			return new ResponseEntity<String>("Updated! ", HttpStatus.OK);
		}
		catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Update failed!", HttpStatus.BAD_REQUEST );
		}
	}

}
