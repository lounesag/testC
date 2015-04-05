package com.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.exception.CustomException;
import com.model.Person;

public interface PersonService {
	/*
	 * CREATE and UPDATE
	 */
	public void savePerson(Person book);

	/*
	 * READ
	 */
	public List<Person> listPersons();
	public Person getPerson(int id) throws CustomException;

	/*
	 * DELETE
	 */
	public void deletePerson(int id);

	public Person getPersonByNameAndFirstName(String name, String firstname) throws CustomException;

	@Transactional
	public Person getByLogin(String login) throws CustomException;
}
