package hust.ngocvu.education.entity;

import java.util.List;
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
@Table(name = "role")
public class Role {
	@Id
	@GeneratedValue
	private int id;
	
	@NotBlank(message = "Please enter name!")
	private String name;
	
	@NotBlank(message = "Enter description")
	private String description;

	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	private List<User> users;
	
	@OneToMany(mappedBy = "roleId")
	Set<UserCourse> userCourse;

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(int id, @NotBlank(message = "Please enter name!") String name,
			@NotBlank(message = "Enter description") String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonIgnore()
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
}
