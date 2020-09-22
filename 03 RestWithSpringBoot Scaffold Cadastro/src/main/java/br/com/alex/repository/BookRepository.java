package br.com.alex.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alex.data.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	@Modifying
	@Query("UPDATE Book b SET b.enabled = false WHERE b.id =: id")
	void disableBook(@Param(value = "id") Long id);
	
	@Query("SELECT b FROM Book b WHERE b.author LIKE LOWER(CONCAT('%', :author, '%'))")
	Page<Book> findBookByName(@Param(value = "author") String author, Pageable pageable);
}
