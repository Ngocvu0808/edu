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

import hust.ngocvu.education.entity.Video;
import hust.ngocvu.education.repository.VideoRepository;

@RestController
@RequestMapping("api/video")
@EnableAutoConfiguration
public class ApiVideoController {
	
	@Autowired
	private VideoRepository videoRepository;
	
	@GetMapping("")
	public Object get() {
		List<Video> videos = videoRepository.findAll();
		return new ResponseEntity<List<Video>>(videos, HttpStatus.OK);
	}
	
	@PostMapping("")
	public Object post(@RequestBody Video video) {
		Video video2 = videoRepository.save(video);
		return new ResponseEntity<Video>(video2, HttpStatus.CREATED);
	}
	
	@DeleteMapping("delete/{id}")
	public Object delete(@PathVariable int id) {
		videoRepository.deleteById(id);
		return new ResponseEntity<String>("Deleted!", HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public Object findById(@PathVariable int id) {
		Optional<Video> video = videoRepository.findById(id);
		Video video2 = video.get();
		return new ResponseEntity<Video>(video2, HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("search/{course_id}")
	public Object findByCourseId(@PathVariable int course_id) {
		List<Video> videos = videoRepository.findBycourseId(course_id);
		return new ResponseEntity<List<Video>>(videos, HttpStatus.OK);
	}

	@PutMapping("{id}")
	public Object put(@PathVariable int id, @RequestBody Video video) {
		try {
			if(videoRepository.findById(id) == null || id != video.getId()) {
				return new ResponseEntity<String>("Id is invalid! ", HttpStatus.BAD_REQUEST);
			}
			else {
				videoRepository.saveAndFlush(video);
				return new ResponseEntity<String>("Updated! ", HttpStatus.OK);
			}
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Update failed! ", HttpStatus.BAD_REQUEST);
		}
	}
	
}
