package hust.ngocvu.education.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "categories")
public class Category {
	@Id
	@GeneratedValue
	private int id;
	
	@NotBlank(message = "Please enter a title")
	private String title;
	private String icon;
	
	@JsonIgnore
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private Set<Course> courses;

	public Category() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public Category(int id, @NotBlank(message = "Please enter a title") String title, String icon) {
		super();
		this.id = id;
		this.title = title;
		this.icon = icon;
	}
}
