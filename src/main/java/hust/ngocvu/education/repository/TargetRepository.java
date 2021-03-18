package hust.ngocvu.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import hust.ngocvu.education.entity.Target;

public interface TargetRepository extends JpaRepository<Target, Integer>{
	
	@Transactional
	@Query("FROM Target WHERE order_index =: order_index and course_id =: course_id")
	List<Target> findByCourseIdAndOrderIndex(@Param("course_id")int course_id, 
			@Param("order_index") int order_index);

}
