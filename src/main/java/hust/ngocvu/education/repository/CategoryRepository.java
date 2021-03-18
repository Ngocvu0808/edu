package hust.ngocvu.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hust.ngocvu.education.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	@Query("Select r from Category r WHERE title LIKE %:keyword% ")
	List<Category> findByDes(@Param("keyword")String name);

}
