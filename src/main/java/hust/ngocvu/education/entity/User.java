package hust.ngocvu.education.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Table(name ="users")
@Entity
public class User {
	@Id
	@GeneratedValue
	private int id;
	
	@NotBlank(message = "Please enter a email")
	@Email(message = "Please enter a email!!")
	@Column(unique =  true)
	private String email;
	
	@NotBlank(message = "Enter your fullname")
	@Length(min = 2, max = 50, message = "Please enter your fullname!!!")
	private String fullname;
	
	private String password;
	
	private String confirmpassword;
	
	private String avatar;
	
	private String phone;
	
	@Column(name = "role_id", unique = true, nullable = false)
	private int roleId;
	
	@ManyToOne
	@JoinColumn(name = "role_id", insertable = false, updatable = false)
	private Role role;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<UserCourse> userCourses;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	

}
