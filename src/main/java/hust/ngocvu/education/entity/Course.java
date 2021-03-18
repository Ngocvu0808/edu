package hust.ngocvu.education.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "courses")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Please enter a title")
	private String title;
	
	private String image;
	
	@NotNull(message = "Please enter the number of lesson")
	@Min(value = 0, message = "Number of lesson is not negative")
	@Column(name = "letures_count")
	private int leturesCount;
	
	@NotNull(message = "Please enter the hours of study")
	@Min(value = 0, message = "Hours of study is not negative")
	@Column(name ="hour_count")
	private int hourcount;
	
	@Column(name = "view_count")
	private int viewCount;
	
	@NotNull(message = "Please enter price")
	@Min(value = 0, message = "Price is not negative")
	private long price;
	
	private int discount;
	
	@Column(name = "promotion_price")
	private long promotionPrice;
	
	@NotBlank(message = "please enter description")
	private String description;
	
	private String content;
	
	@Column(name = "category_id")
	private int categoryId;
	
	@Column(name = "last_update")
	@Temporal(TemporalType.DATE)
	private Date lastUpdate;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "category_id", insertable = false, updatable = false)
	private Category category;
	
	@JsonIgnore
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	private Set<Target> targets;
	
	@JsonIgnore
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	private Set<Video> videos;
	
	@JsonIgnore
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	private Set<UserCourse> userCourses;

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Course(int id, @NotBlank(message = "Please enter a title") String title,
			@NotNull(message = "Please enter the number of lesson") @Min(value = 0, message = "Number of lesson is not negative") int leturesCount,
			@NotNull(message = "Please enter the hours of study") @Min(value = 0, message = "Hours of study is not negative") int hourcount,
			int viewCount,
			@NotNull(message = "Please enter price") @Min(value = 0, message = "Price is not negative") long price,
			int discount, long promotionPrice, @NotBlank(message = "please enter description") String description,
			String content, int categoryId, Date lastUpdate) {
		super();
		this.id = id;
		this.title = title;
		this.leturesCount = leturesCount;
		this.hourcount = hourcount;
		this.viewCount = viewCount;
		this.price = price;
		this.discount = discount;
		this.promotionPrice = promotionPrice;
		this.description = description;
		this.content = content;
		this.categoryId = categoryId;
		this.lastUpdate = lastUpdate;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getLeturesCount() {
		return leturesCount;
	}

	public void setLeturesCount(int leturesCount) {
		this.leturesCount = leturesCount;
	}

	public int getHourcount() {
		return hourcount;
	}

	public void setHourcount(int hourcount) {
		this.hourcount = hourcount;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public long getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(long promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Target> getTargets() {
		return targets;
	}

	public void setTargets(Set<Target> targets) {
		this.targets = targets;
	}

	public Set<Video> getVideos() {
		return videos;
	}

	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}

	public Set<UserCourse> getUserCourses() {
		return userCourses;
	}

	public void setUserCourses(Set<UserCourse> userCourses) {
		this.userCourses = userCourses;
	}
}
