package hust.ngocvu.education.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;

@Entity
@Table(name ="user_courses")
public class UserCourse {

	@Id
	private int id;
	
	@Valid
	@Column(name = "role_id", unique = true, nullable = false)
	private int roleId;
	
	@Column(name = "user_id", unique = true, nullable = false)
	private int userId;
	
	@Column(name = "course_id", unique = true, nullable = false)
	private int courseId;
	
	@ManyToOne
	@JoinColumn(name = "role_id", insertable = false, updatable = false)
	private Role role;
	
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "course_id", insertable = false, updatable = false)
	private Course course;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	
	public UserCourse() {
		
	}

	public UserCourse(int roleId, User user, Course course) {
		super();
		this.roleId = roleId;
		this.user = user;
		this.course = course;
	}
	
}
