package com.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.PersonRegLogDao;
import com.model.LogsForPerson;

@Repository
public class PersonRegLogDaoImpl implements PersonRegLogDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void savePersonRegLog(LogsForPerson personRegLog) {
		getSession().merge(personRegLog);
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

	public List<LogsForPerson> listLogsForPersons() {
		// TODO Auto-generated method stub
		return null;
	}

	public LogsForPerson getLogsForPerson(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteLogsForPerson(int id) {
		// TODO Auto-generated method stub
		
	}

}
