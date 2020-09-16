package br.com.alex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alex.data.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
