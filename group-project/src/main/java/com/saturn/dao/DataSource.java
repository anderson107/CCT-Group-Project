package com.saturn.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DataSource implements HibernateDB{

	// only instance of this class
	private static DataSource instance = new DataSource();
	
	private SessionFactory factory;

	private Session session;
	
	// private constructor to guarantee that no other instance will be created
	private DataSource() {
		
	}
	
	@Override
	public Session getSession() {
		return session;
	}
	
	public static DataSource getInstance() {
		return instance;
	}

	// it fetches the database configuration and mapped classes from the hibernate file
	@Override
	public SessionFactory getFactory() {
		factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		return factory;
	}
	
	@Override
	public Session openSession() {
		session = getFactory().getCurrentSession();
		return session;
	}
	
	@Override
	public void beginTransaction() {
		session.beginTransaction();
	}
	
	public <T> void add(T t) {
		session.save(t);
	}
	
	public <T> void delete(T t) {
		session.delete(t);
	}
	
	public <T> Object get(Class<T>type, int id){
		Object t =session.get(type, id);
		return t;
	}
	
	@Override
	public void commit() {
		session.getTransaction().commit();
	}
	
	@Override
	public void closeSession() {
		session.close();
	}
	
	@Override
	public void closeFactory() {
		factory.close();
	}
	
	// it loads all items of a given table
	@SuppressWarnings("unchecked")
	public <T> List<T>loadAll(String query){
		List<T> results = session.createQuery(query).getResultList();
		return results;
	}
	
}
