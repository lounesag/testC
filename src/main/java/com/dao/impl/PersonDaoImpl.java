package com.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.PersonDao;
import com.exception.CustomException;
import com.model.Person;

@Repository
public class PersonDaoImpl implements PersonDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void savePerson(Person person) throws CustomException, HibernateException {
		try {
			getSession().merge(person);
		} catch(Exception e) {
			//getSession()).rollback();
			System.out.println("coucou jsui la 1");
			getSession().clear();
			throw new CustomException("email, or login duplicate",e);   
		}
	}

	public List<Person> listPersons() {
		return getSession().createCriteria(Person.class).list();
	}

	public Person getPerson(int id) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Person p where p.id = :id");
		query.setParameter("id", id);

		if (query.list().isEmpty()) {
			return null;
		}

		return (Person) query.list().get(0);
	}

	public void deletePerson(int id) {
		Person person = getPerson(id);

		if (null != person) {
			getSession().delete(person);
		}

	}

	public Person getPersonByNameAndFirstName(String name, String firstname) throws CustomException {
		Query query = sessionFactory.getCurrentSession().createQuery("from Person t where t.name = :name and t.firstname = :firstname");
		query.setParameter("name", name);
		query.setParameter("firstname", firstname);

		if (query.list().isEmpty()) {
			return null;
		} 
		if (query.list().size()!=1){
			throw new CustomException("there is not an unique person for name: "+name+" and firstname: "+firstname+" please see a DB !");
		}

		return (Person) query.list().get(0);
	}

	private Session getSession() {
		Session sess = getSessionFactory().getCurrentSession();
		if (sess == null) {
			sess = getSessionFactory().openSession();
		}
		return sess;
	}

	private SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public Person getPersonByLogin(String login) throws CustomException {
		Query query = sessionFactory.getCurrentSession().createQuery("from Person t where t.login = :login");
		query.setParameter("login", login);

		if (query.list().isEmpty()) {
			return null;
		}

		if (query.list().size()!=1){
			throw new CustomException("there is not an unique person for login: "+login+"  please see a DB !");
		}

		return (Person) query.list().get(0);
	}

	@Override
	public Person getPersonByEmail(String email) throws CustomException {
		Query query = sessionFactory.getCurrentSession().createQuery("from Person t where t.email = :email");
		query.setParameter("email", email);

		if (query.list().isEmpty()) {
			return null;
		}

		if (query.list().size()!=1){
			throw new CustomException("there is not an unique person for email: "+email+"  please see a DB !");
		}

		return (Person) query.list().get(0);
	}

}
