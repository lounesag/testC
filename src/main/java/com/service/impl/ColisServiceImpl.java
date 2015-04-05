package com.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ColisDao;
import com.dao.PersonDao;
import com.exception.CustomException;
import com.model.Colis;
import com.model.Person;
import com.service.ColisService;
import com.service.PersonService;

@Service
public class ColisServiceImpl implements ColisService{

	@Autowired
	private ColisDao colisDao;

	@Autowired
	private PersonDao personDao;

	@Autowired
	private PersonService personService;
	
	@Transactional
	public void saveColis(Colis colis) {
		colisDao.saveColis(colis);
	}

	@Transactional
	public List<Colis> listColis() {
		return colisDao.listColis();
	}

	@Transactional
	public Colis getColis(int id) {
		return colisDao.getColis(id);
	}

	@Transactional
	public void deleteColis(int id) {
		colisDao.deleteColis(id);
	}

	@Transactional
	public void setColisService (UserDetails currentUser, Colis colis ) throws CustomException{
		System.out.println("current user : ------> "+currentUser);

		Person person = personDao.getByLogin(currentUser.getUsername());
		
		Set<Person> ListePersone = new HashSet<Person>();
		
		ListePersone.add(person);

		Colis colisNew = new Colis();
		colisNew.setColisNumero(colis.getColisNumero());
		colisNew.setComment(colis.getComment());
		colisNew.setCreatedBy(person.getId());
		colisNew.setDescription(colis.getDescription());
		colisNew.setHeight(colis.getHeight());
		colisNew.setLength(colis.getLength());
		colisNew.setName(colis.getName());
		colisNew.setVolume(colis.getVolume());
		colisNew.setWeight(colis.getWeight());
		colisNew.setWidth(colis.getWidth());

		colisNew.setPersons(ListePersone);

	//	saveColis(colisNew);
		person.getColisListe().add(colisNew);
		personService.savePerson(person);
	}
}