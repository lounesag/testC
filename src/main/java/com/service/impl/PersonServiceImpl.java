package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.PersonDao;
import com.exception.CustomException;
import com.model.Person;
import com.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDao personDao;

	@Transactional
	public void savePerson(Person person) throws CustomException {
			try {
				personDao.savePerson(person);
				System.out.println("coucou jsui la 2");
			} catch(Exception e) {
				throw new CustomException("email, or login duplicate",e);
			}
		
	}

	@Transactional(readOnly = true)
	public List<Person> listPersons() {
		return personDao.listPersons();
	}

	@Transactional(readOnly = true)
	public Person getPerson(int id) throws CustomException {
		return personDao.getPerson(id);
	}

	@Transactional
	public void deletePerson(int id) {
		personDao.deletePerson(id);
	}
	
	@Transactional(readOnly=true)
	public Person getPersonByNameAndFirstName(String name, String firstname) throws CustomException{
		return personDao.getPersonByNameAndFirstName(name, firstname);	
	}

	@Transactional
	public Person getByLogin(String login) throws CustomException {
		return personDao.getPersonByLogin(login);
	}

	@Override
	public Person getPersonByEmail(String email) throws CustomException {
		return personDao.getPersonByEmail(email);
	}


}
