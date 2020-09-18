package br.com.alex.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alex.converter.DozerConverter;
import br.com.alex.data.model.Person;
import br.com.alex.data.vo.v1.PersonVO;
import br.com.alex.exception.ResourceNotFoundException;
import br.com.alex.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;

	public PersonVO createPerson(PersonVO vo) {

		var entity = DozerConverter.parseObject(vo, Person.class);

		var person = repository.save(entity);

		var personVO = DozerConverter.parseObject(person, PersonVO.class);

		return personVO;
	}

	public List<PersonVO> findAll() {

		List<PersonVO> entities = DozerConverter.parseListObjects(repository.findAll(), PersonVO.class);

		return entities;
	}
	
	public Page<PersonVO> findAllPageable(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToPersonVO);		
	}
	
	public PersonVO convertToPersonVO(Person entity) {
		return DozerConverter.parseObject(entity, PersonVO.class);
	}

	public PersonVO findById(Long id) {
		Person person = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID"));

		PersonVO vo = DozerConverter.parseObject(person, PersonVO.class);
		return vo;
	}

	@Transactional
	public PersonVO disablePerson(Long id) {

		repository.disablePerson(id);

		Optional<Person> op = repository.findById(id);
		
		Person person = op.get();

		PersonVO vo = DozerConverter.parseObject(person, PersonVO.class);
		return vo;
	}

	public PersonVO update(PersonVO person) {
		var entity = repository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());

		entity = repository.save(entity);

		var vo = DozerConverter.parseObject(entity, PersonVO.class);

		return vo;
	}

	public void delete(Long id) {
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID"));
		this.repository.delete(entity);
		;
	}
}
