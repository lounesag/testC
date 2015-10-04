package com.dao;

import java.util.List;

import org.hibernate.exception.DataException;

import com.exception.CustomException;
import com.model.Person;

public interface PersonDao {

	 /*
     * CREATE and UPDATE
     */
    public void savePerson(Person person) throws CustomException, DataException; // create and update

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

	public Person getPersonByLogin(String login) throws CustomException;

	public Person getPersonByEmail(String email) throws CustomException;

}
