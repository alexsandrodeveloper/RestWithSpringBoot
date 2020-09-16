package br.com.alex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alex.converter.DozerConverter;
import br.com.alex.data.model.Book;
import br.com.alex.data.vo.v1.BookVO;
import br.com.alex.exception.ResourceNotFoundException;
import br.com.alex.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository repository;

	public BookVO createBook(BookVO vo) {

		var entity = DozerConverter.parseObject(vo, Book.class);

		var book = repository.save(entity);

		var bookVO = DozerConverter.parseObject(book, BookVO.class);

		return bookVO;
	}

	public List<BookVO> findAll() {

		List<Book> books = repository.findAll();
		List<BookVO> entities = DozerConverter.parseListObjects(books, BookVO.class);

		return entities;
	}

	public BookVO findById(Long id) {
		Book person = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID"));

		BookVO vo = DozerConverter.parseObject(person, BookVO.class);
		return vo;
	}

	public BookVO update(BookVO book) {
		var entity = repository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID"));
		entity.setAuthor(book.getAuthor());
		entity.setTitle(book.getTitle());
		entity.setPrice(book.getPrice());
		entity.setLaunchDate(book.getLaunch_date());

		entity = repository.save(entity);

		var vo = DozerConverter.parseObject(entity, BookVO.class);

		return vo;
	}

	public void delete(Long id) {
		Book entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID"));
		this.repository.delete(entity);
		;
	}
}
