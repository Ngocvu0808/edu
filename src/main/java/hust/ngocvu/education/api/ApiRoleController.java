package hust.ngocvu.education.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hust.ngocvu.education.entity.Role;
import hust.ngocvu.education.repository.RoleRepository;

@RestController
@RequestMapping("api/role")
@EnableAutoConfiguration	
public class ApiRoleController {
	

	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping("")
	public Object get() {
		List<Role> roles = roleRepository.findAll();
		return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
	}
	
	@PostMapping("")
	public Object post(@RequestBody Role role) {
		Role role2 = roleRepository.save(role);
		return new ResponseEntity<Role>(role2, HttpStatus.CREATED);
	}
	
	@GetMapping("search")
	public Object search(@RequestParam String keyword) {
		List<Role> roles = roleRepository.findByDes(keyword);
		return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{id}")
	public Object delete(@PathVariable int id) {
		roleRepository.deleteById(id);
		return new ResponseEntity<String>("Deleted!", HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public Object put(@PathVariable int id, @RequestBody Role role) {
		try {
			if(roleRepository.findById(id) == null || id != role.getId()) {
				return new ResponseEntity<String>("Id is invalid! ", HttpStatus.BAD_REQUEST);
			}
			roleRepository.save(role);
			return new ResponseEntity<String>("Update successed!", HttpStatus.OK);
			
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Update failed! ", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("{id}")
	public Object findById(@PathVariable int id) {
		Optional<Role> role = roleRepository.findById(id);
		Role role2 = role.get();
		return new ResponseEntity<Role>(role2, HttpStatus.OK);
	}
}
