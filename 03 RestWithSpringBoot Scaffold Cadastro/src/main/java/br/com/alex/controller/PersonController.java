package br.com.alex.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

	//@CrossOrigin(origins = "http://localhost:8080")
	@ApiOperation(value = "Find people record by id")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO findById(@PathVariable("id") Long id) {
		PersonVO vo = this.services.findById(id);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}

	//Habilitar Cross Origin (Compartilhamento de recursos de origem cruzada) por recurso
	//@CrossOrigin(origins = {"http://localhost:8080" , "http://alexsandro.com.br"})
	@ApiOperation(value = "Find all people records")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<PersonVO> findAll() {
		List<PersonVO> persons = this.services.findAll();
		persons.stream()
				.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		return persons;
	}

	@ApiOperation(value = "Create people")
	@PostMapping(consumes = { "application/json", "application/xml", "application/x-yaml" }, produces = {
			"application/json", "application/xml", "application/x-yaml" })
	public PersonVO create(@RequestBody PersonVO personVO) {
		PersonVO vo = this.services.createPerson(personVO);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	@ApiOperation(value = "Update people records")
	@PutMapping(consumes = { "application/json", "application/xml", "application/x-yaml" }, produces = {
			"application/json", "application/xml", "application/x-yaml" })
	public PersonVO update(@RequestBody PersonVO personVO) {
		PersonVO vo = this.services.update(personVO);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	@ApiOperation(value = "Delete people record by id")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		this.services.delete(id);
		return ResponseEntity.ok().build();
	}
}
