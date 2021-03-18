
package hust.ngocvu.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import hust.ngocvu.education.entity.UserCourse;

public interface UserCourseRepository extends JpaRepository<UserCourse, Integer>{
	@Transactional
	@Modifying
	@Query("DELETE FROM UserCourse WHERE user_id = :user_id and course_id = :course_id")
	void deleteByCourseIdAndUserID(@Param("course_id")int course_id,
			@Param("user_id") int user_id);
	
	@Transactional
	@Query("FROM UserCourse WHERE user_id = :user_id and course_id =:course_id")
	UserCourse findByCourseIdAndUserID(@Param("course_id") int course_id,
			@Param("user_id")int user_id);
	
	@Query("FROM UserCourse WHERE user_id =:user_id")
	List<UserCourse> findByUserId(@Param("user_id") int user_id);

}
