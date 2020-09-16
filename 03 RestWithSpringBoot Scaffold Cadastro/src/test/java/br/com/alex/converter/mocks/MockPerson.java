package br.com.alex.converter.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.alex.data.model.Person;
import br.com.alex.data.vo.v1.PersonVO;

public class MockPerson {

	public Person mockEntity() {
		return mockEntity(1L);
	}

	public PersonVO mockEntityVO() {
		return mockEntityVO(1L);
	}

	public List<Person> mockEntityList() {
		List<Person> entities = new ArrayList<Person>();

		for (long i = 1; i <= 14; i++) {
			entities.add(mockEntity(i));
		}
		return entities;
	}

	public List<PersonVO> mockEntityListVO() {
		List<PersonVO> entities = new ArrayList<PersonVO>();

		for (long i = 1; i <= 14; i++) {
			entities.add(mockEntityVO(i));
		}
		return entities;
	}

	private Person mockEntity(Long i) {
		Person person = new Person();
		person.setId(i);
		person.setFirstName("First Name Test" + i);
		person.setLastName("Last Name Test" + i);
		person.setGender((i % 2) == 0 ? "Male" : "Female");
		person.setAddress("Address Test" + i);
		return person;
	}

	private PersonVO mockEntityVO(Long i) {
		PersonVO personVO = new PersonVO();
		personVO.setKey(i);
		personVO.setFirstName("First Name Test" + i);
		personVO.setLastName("Last Name Test" + i);
		personVO.setGender((i % 2) == 0 ? "Male" : "Female");
		personVO.setAddress("Address Test" + i);
		return personVO;
	}
}
