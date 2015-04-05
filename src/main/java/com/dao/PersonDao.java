package com.dao;

import java.util.List;

import com.exception.CustomException;
import com.model.Person;

public interface PersonDao {

	 /*
     * CREATE and UPDATE
     */
    public void savePerson(Person person); // create and update

    /*
     * READ
     */
    public List<Person> listPersons();
    public Person getPerson(int id);

    /*
     * DELETE
     */
    public void deletePerson(int id);
    
	public Person getPersonByNameAndFirstName(String name, String firstname) throws CustomException;

	public Person getByLogin(String login) throws CustomException;
}
