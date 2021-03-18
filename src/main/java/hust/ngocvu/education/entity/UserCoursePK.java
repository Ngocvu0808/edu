package hust.ngocvu.education.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class UserCoursePK implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long user;
	private long course;
	
	public UserCoursePK() {
		// TODO Auto-generated constructor stub
	}

	public UserCoursePK(long user, long course) {
		super();
		this.user = user;
		this.course = course;
	}

	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
	}

	public long getCourse() {
		return course;
	}

	public void setCourse(long course) {
		this.course = course;
	}
	
}
