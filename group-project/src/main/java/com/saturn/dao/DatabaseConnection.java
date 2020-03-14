package com.saturn.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.saturn.model.Checklist;
import com.saturn.model.ChecklistSuperClass;
import com.saturn.model.FireWarden;

public final class DatabaseConnection {

	private static final SessionFactory factory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(FireWarden.class)
			.buildSessionFactory();
	
	// create session
	private static final Session session = factory.getCurrentSession();
	
	// add checklist items to the database
	public static void addChecklistItem(Checklist obj) {
		
		try {
		session.beginTransaction();
		session.save(obj);
		session.getTransaction().commit();
		
		}catch(Exception es) {
			es.printStackTrace();
		}
		
		finally {
			session.close();
			factory.close();
		}
		
	}

	// delete item from checklist item from the database
	public static void deleteItemFromChecklist(Checklist obj) {

		try {
			session.beginTransaction();
			session.delete(obj);
			session.getTransaction().commit();
			
			}catch(Exception es) {
				es.printStackTrace();
			}
			finally {
				session.close();
				factory.close();
			}
	}
	
	public static ChecklistSuperClass getChecklistItem(int id) {
		
		try {
			session.beginTransaction();
			ChecklistSuperClass obj = session.get(ChecklistSuperClass.class, id);
			session.getTransaction().commit();
			return obj;
			}catch(Exception es) {
				es.printStackTrace();
			}
			finally {
				session.close();
				factory.close();
			}
		return null;
	}
}
