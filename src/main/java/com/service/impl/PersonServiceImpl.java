package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.PersonDao;
import com.model.Person;
import com.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDao personDao;

	@Transactional
	public void savePerson(Person person) {
		personDao.savePerson(person);
	}

	@Transactional(readOnly = true)
	public List<Person> listPersons() {
		return personDao.listPersons();
	}

	@Transactional(readOnly = true)
	public Person getPerson(int id) {
		return personDao.getPerson(id);
	}

	@Transactional
	public void deletePerson(int id) {
		personDao.deletePerson(id);
	}

}
