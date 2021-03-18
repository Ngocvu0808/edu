package hust.ngocvu.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hust.ngocvu.education.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	@Query("SELECT r from Role r WHERE description LIKE %:keyword% ")
	List<Role> findByDes(@Param("keyword")String name);

	@Transactional
	@Modifying
	@Query(value = "update Role c set c.name = :name, c.description =:description WHERE c.id =:id",
	nativeQuery = true)
	void update(@Param("name")String name, 
			@Param("description")String description, 
			@Param("id")int id);
}
