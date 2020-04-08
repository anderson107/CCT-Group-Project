package com.saturn.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DataSource {

	
	private static DataSource instance = new DataSource();
	
	private SessionFactory factory;

	private Session session;
	
	private DataSource() {
		
	}
	
	public static DataSource getInstance() {
		return instance;
	}

	public SessionFactory getFactory() {
		factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		return factory;
	}
	
	public Session openSession() {
		session = getFactory().getCurrentSession();
		session.beginTransaction();
		return session;
	}
	
	public <T> void add(T t) {
		session.save(t);
	}
	
	public <T> void delete(T t) {
		session.delete(t);
	}
	
	public <T>Class<T> get(Class<T>type, int id){
		T t = session.get(type, id);
		return (Class<T>) t;
	}
	
	public void commit() {
		session.getTransaction().commit();
	}
	
	public void closeSession() {
		session.close();
	}
	
	public void closeFactory() {
		factory.close();
	}
	
	public <T> List<T>loadAll(String query){
		List<T>results = session.createQuery(query).getResultList();
		return results;
	}
	
}
