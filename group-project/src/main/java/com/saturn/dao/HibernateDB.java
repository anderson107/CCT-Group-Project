package com.saturn.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface HibernateDB {

	Session getSession();
	SessionFactory getFactory();
	Session openSession();
	void beginTransaction();
	void commit();
	void closeSession();
	void closeFactory();
}
