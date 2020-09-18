package br.com.alex.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alex.data.model.Person;

/**
 * @author Alex Sandro
 * @version 1.0
 *
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	@Modifying
	@Query("UPDATE Person p SET p.enabled = false WHERE p.id =: id")
	void disablePerson(@Param(value = "id") Long id);
	
	@Query("SELECT p FROM Person p WHERE p.firstName LIKE LOWER(CONCAT('%', :firstName, '%'))")
	Page<Person> findPersonByName(@Param(value = "firstName") String firstName, Pageable pageable);
}
