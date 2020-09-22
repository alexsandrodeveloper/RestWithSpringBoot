package br.com.alex.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alex.data.vo.v1.BookVO;
import br.com.alex.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Book Endpoint", description = "Description para Book Endpoint", tags = "BookEndpoint")
@RestController
@RequestMapping(value = "/api/book/v1")
public class BookController {

	@Autowired
	private BookService services;
	
	@Autowired
	private PagedResourcesAssembler<BookVO> assembler;

	@ApiOperation(value = "Find book record by id")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public BookVO findById(@PathVariable("author") Long id) {
		BookVO vo = this.services.findById(id);
		vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	@ApiOperation(value = "Find book record by name")
	@GetMapping(value = "/findBookByName/{author}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findBookByName(@PathVariable(value="author") String author,
								 @RequestParam(value = "page", defaultValue = "0") int page,
								 @RequestParam(value = "limit", defaultValue = "5") int limit,
								 @RequestParam(value = "direction", defaultValue = "asc") String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));

		Page<BookVO> books = this.services.findBookByName(author, pageable);
		
		books.stream()
				.forEach(p -> p.add(linkTo(
									methodOn(PersonController.class).
									findPersonByName(p.getAuthor(),page,limit,direction)).
									withSelfRel()));
		
		PagedResources<?> resource = assembler.toResource(books);
		
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}

	@ApiOperation(value = "Find book record")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<BookVO> findAll() {
		List<BookVO> books = this.services.findAll();
		books.stream()
				.forEach(p -> p.add(linkTo(
										methodOn(BookController.class).
										findAll()).
										withSelfRel()));
		return books;
	}

	@ApiOperation(value = "Create book record")
	@PostMapping(consumes = { "application/json", "application/xml", "application/x-yaml" }, produces = {
			"application/json", "application/xml", "application/x-yaml" })
	public BookVO create(@RequestBody BookVO bookVO) {
		BookVO vo = this.services.createBook(bookVO);
		vo.add(linkTo(methodOn(BookController.class).create(vo)).withSelfRel());
		return vo;
	}

	@ApiOperation(value = "Update book record")
	@PutMapping(consumes = { "application/json", "application/xml", "application/x-yaml" }, produces = {
			"application/json", "application/xml", "application/x-yaml" })
	public BookVO update(@RequestBody BookVO bookVO) {
		BookVO vo = this.services.update(bookVO);
		vo.add(linkTo(methodOn(BookController.class).update(vo)).withSelfRel());
		return vo;
	}

	@ApiOperation(value = "Delete book record by id")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		this.services.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
