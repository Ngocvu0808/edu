package hust.ngocvu.education.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "videos")
public class Video {
	
	@Id
	@GeneratedValue
	private int id;
	
	@NotBlank(message = "Please enter a title")
	private String title;
	
	@Column(name ="order_index")
	private int orderIndex;
	
	@NotBlank(message = "Enter a url!")
	@URL(message = "Enter a URL!!")
	private String url;
	
	@Min(value = 0, message = "Enter time count")
	@Column(name = "time_count")
	private int timeCount;
	
	@Min(value = 0, message = "Enter a courseid")
	@Column(name = "course_id")
	private int courseId;
	
	private String image;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "course_id", insertable = false, updatable = false)
	private Course course;
	
	public Video() {}

	public Video(int id, String title, String url, int timeCount, int courseId,int orderIndex, String image) {
	
		this.id = id;
		this.title = title;
		this.url = url;
		this.timeCount = timeCount;
		this.courseId = courseId;
		this.image = image;
		this.orderIndex = orderIndex;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getTimeCount() {
		return timeCount;
	}

	public void setTimeCount(int timeCount) {
		this.timeCount = timeCount;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	public int getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	

}
