package hust.ngocvu.education.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "targets")
public class Target {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Please enter a title.")
	private String title;
	
	@Column(name = "order_index")
	@NotNull(message = "Please enter index")
	@Min(value = 1, message = "Index must be possitive!!")
	private int orderIndex;
	
	@Column(name = "course_id")
	private int courseId;
	
	@ManyToOne
	@JsonIgnore()
	@JoinColumn(name = "course_id", insertable = false, updatable = false)
	private Course course;
	
	public Target() {
	}
	
	public Target(int id, String title, int orderIndex, int courseId) {
		super();
		this.id = id;
		this.title = title;
		this.orderIndex = orderIndex;
		this.courseId = courseId;
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

	public int getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
}
