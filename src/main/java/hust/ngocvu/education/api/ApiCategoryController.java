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

import hust.ngocvu.education.entity.Category;
import hust.ngocvu.education.repository.CategoryRepository;

@RestController
@RequestMapping("api/category")
@CrossOrigin
public class ApiCategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("")
	public Object get() {
		List<Category> category = categoryRepository.findAll();
		return new ResponseEntity<List<Category>>(category, HttpStatus.OK);
	}
	
	@PostMapping("")
	public Object post(@RequestBody Category category) {
		Category category2= categoryRepository.save(category);
		return new ResponseEntity<Category>(category2, HttpStatus.OK);
	}
	
	@GetMapping("search")
	public Object search(@RequestParam String keyword) {
		List<Category> categories = categoryRepository.findByDes(keyword);
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{id}")
	public Object delete(@PathVariable int id) {
		categoryRepository.deleteById(id);
		return new ResponseEntity<String>("Delete successfuly!", HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public Object put(@PathVariable int id, @RequestBody Category category) {
		try {
			if(categoryRepository.findById(id) == null || id != category.getId()) {
				return new ResponseEntity<String>("Id is not valid!", HttpStatus.BAD_REQUEST);
			}
			categoryRepository.saveAndFlush(category);
			return new ResponseEntity<String>("Update successfully! ", HttpStatus.OK);
			
		}
		catch (Exception e) {
			return new ResponseEntity<String>("Update failed!", HttpStatus.BAD_REQUEST);
			// TODO: handle exception
		}
	}
	
	
	@GetMapping("{id}")
	public Object findByid(@PathVariable int id) {
		Optional<Category> categories =categoryRepository.findById(id);
		Category category = categories.get();
		
		return new ResponseEntity<Category>(category, HttpStatus.OK);
		
	}
}
