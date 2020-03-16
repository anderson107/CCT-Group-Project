package com.saturn.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.saturn.model.Checklist;
import com.saturn.model.ChecklistSuperClass;
import com.saturn.model.FireWarden;
import com.saturn.model.HealthSafetyChecklist;
import com.saturn.model.Task;

public final class DatabaseConnection {

	private static final SessionFactory factory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(FireWarden.class)
			.addAnnotatedClass(HealthSafetyChecklist.class)
			.addAnnotatedClass(Task.class)
			.buildSessionFactory();
	
	
	// add checklist items to the database
	public static void addChecklistItem(Checklist obj) {
		
		Session session = factory.getCurrentSession();
		try {
		session.beginTransaction();
		session.save(obj);
		session.getTransaction().commit();
		
		}catch(Exception es) {
			es.printStackTrace();
		}
		finally {
			session.close();
		}
		
	}

	// delete item from checklist item from the database
	public static void deleteItemFromChecklist(Checklist obj) {
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			session.delete(obj);
			session.getTransaction().commit();
			
			}catch(Exception es) {
				es.printStackTrace();
			}
			finally {
				session.close();
			}
	}
	
	// get item from database
	public static <T> ChecklistSuperClass getChecklistItem(Class<T> type, int id) {
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			ChecklistSuperClass obj = (ChecklistSuperClass) session.get(type, id);
			session.getTransaction().commit();
			return obj;
			}catch(Exception es) {
				es.printStackTrace();
			}
			finally {
				session.close();
			}
		return null;
	}
	
	// get all items from a table
	public static <T> List<T> loadAllData(Class<T>type){
		Session session = factory.getCurrentSession();
		List<T> data=null;
		try {
		session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
	    CriteriaQuery<T> criteria = builder.createQuery(type);
	    criteria.from(type);
	     data= session.createQuery(criteria).getResultList();
	    
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	    return data;
	}
	
	public static <T> void updateItemChecklistItem(Class<T>type, int id, String frequency, String status, LocalDate localdate) {
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			ChecklistSuperClass obj = (ChecklistSuperClass) session.get(type, id);
			obj.setFrequency(frequency);
			session.getTransaction().commit();
			
			}catch(Exception es) {
				es.printStackTrace();
			}
			finally {
				session.close();
			}
	}
	
}
