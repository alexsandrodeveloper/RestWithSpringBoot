package br.com.alex.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alex.data.vo.v1.PersonVO;
import br.com.alex.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//Habilitar Cross Origin (Compartilhamento de recursos de origem cruzada) por controller
//@CrossOrigin
@Api(value = "Person Endpoint", description = "Description para Person Endpoint", tags = "PersonEndpoint")
@RestController
@RequestMapping(value = "/api/person/v1")
public class PersonController {

	@Autowired
	private PersonService services;

	// @CrossOrigin(origins = "http://localhost:8080")
	@ApiOperation(value = "Find people record by id")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO findById(@PathVariable("id") Long id) {
		PersonVO vo = this.services.findById(id);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}

	// @CrossOrigin(origins = "http://localhost:8080")
	@ApiOperation(value = "Disable specific people by Id")
	@PatchMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO disablePerson(@PathVariable("id") Long id) {
		PersonVO vo = this.services.disablePerson(id);
		vo.add(linkTo(methodOn(PersonController.class).disablePerson(vo.getKey())).withSelfRel());
		return vo;
	}

	// Habilitar Cross Origin (Compartilhamento de recursos de origem cruzada) por
	// recurso
	// @CrossOrigin(origins = {"http://localhost:8080" ,
	// "http://alexsandro.com.br"})
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "Find all people records with pageable")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<PagedResources<PersonVO>> findAllPageable(@RequestParam(value = "page", defaultValue = "0") int page,
										  			@RequestParam(value = "limit", defaultValue = "12") int limit,
										  			@RequestParam(value = "direction", defaultValue = "asc") String direction,
										  			PagedResourcesAssembler assembler) {

		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));

		Page<PersonVO> persons = this.services.findAllPageable(pageable);
		
		persons.stream().forEach(p -> p.add(linkTo(
								                   methodOn(PersonController.class).
								                   findAllPageable(page,limit,direction,assembler)).
												   withSelfRel()));
		
		return new ResponseEntity<>(assembler.toResource(persons), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "Find all people records with pageable")
	@GetMapping(value = "/findPersonByName/{firstName}",
				produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<PagedResources<PersonVO>> findPersonByName(
									@PathVariable(value="firstName") String firstName,
									@RequestParam(value = "page", defaultValue = "0") int page,
									@RequestParam(value = "limit", defaultValue = "12") int limit,
									@RequestParam(value = "direction", defaultValue = "asc") String direction,
										  		  PagedResourcesAssembler assembler) {

		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));

		Page<PersonVO> persons = this.services.findPersonByName(firstName, pageable);
		
		persons.stream()
				.forEach(p -> p.add(linkTo(
									methodOn(PersonController.class).
									findPersonByName(p.getFirstName(),page,limit,direction,assembler)).
									withSelfRel()));
		
		return new ResponseEntity<>(assembler.toResource(persons), HttpStatus.OK);
	}

	@ApiOperation(value = "Create people")
	@PostMapping(consumes = { "application/json", "application/xml", "application/x-yaml" }, produces = {
			"application/json", "application/xml", "application/x-yaml" })
	public PersonVO create(@RequestBody PersonVO personVO) {
		PersonVO vo = this.services.createPerson(personVO);
		vo.add(linkTo(methodOn(PersonController.class).create(vo)).withSelfRel());
		return vo;
	}

	@ApiOperation(value = "Update people records")
	@PutMapping(consumes = { "application/json", "application/xml", "application/x-yaml" }, produces = {
			"application/json", "application/xml", "application/x-yaml" })
	public PersonVO update(@RequestBody PersonVO personVO) {
		PersonVO vo = this.services.update(personVO);
		vo.add(linkTo(methodOn(PersonController.class).update(vo)).withSelfRel());
		return vo;
	}

	@ApiOperation(value = "Delete people record by id")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		this.services.delete(id);
		return ResponseEntity.ok().build();
	}
}
