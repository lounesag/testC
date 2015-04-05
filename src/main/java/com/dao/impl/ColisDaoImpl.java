package com.dao.impl;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.dao.ColisDao;
import com.exception.CustomException;
import com.model.Colis;
import com.model.Person;
@Repository
public class ColisDaoImpl implements ColisDao{
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void saveColis(Colis colis) {
		getSession().merge(colis);
	}
	@Override
	public List<Colis> listColis() {
		Query query = sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM Colis");
		if (query.list().isEmpty()) {
			return null;
		}
		List<Colis> listColis = query.list();
		return listColis ;
	}
	@Override
	public Colis getColis(int id) {
		Query query = sessionFactory.getCurrentSession().createSQLQuery("select * from Colis c where c.id = :id")
				.addEntity(Colis.class)
				.setParameter("id", id);
		Colis result = (Colis) query.list().get(0);
		return result;
	}
	@Override
	public void deleteColis(int id) {
		Colis colis = getColis(id);
		if (null != colis) {
			getSession().delete(colis);
		}
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
}