package com.saturn.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.saturn.model.ChecklistCategory;
import com.saturn.model.ChecklistSuperClass;
import com.saturn.model.Employee;
import com.saturn.model.FireWarden;
import com.saturn.model.HealthSafetyChecklist;
import com.saturn.model.Task;

import javafx.scene.control.DatePicker;

public final class DatabaseConnection {

	private static final SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(FireWarden.class).addAnnotatedClass(HealthSafetyChecklist.class)
			.addAnnotatedClass(Task.class).addAnnotatedClass(Employee.class).buildSessionFactory();

	// add checklist items to the database
	public static void add(ChecklistSuperClass obj) {

		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(obj);
			session.getTransaction().commit();

		} catch (Exception es) {
			es.printStackTrace();
		} finally {
			session.close();
		}

	}
	
	public static void add(Employee emp) {
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(emp);
			session.getTransaction().commit();

		} catch (Exception es) {
			es.printStackTrace();
		} finally {
			session.close();
		}
		
	}

	// delete item from checklist item from the database
	public static void delete(ChecklistSuperClass obj) {
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			session.delete(obj);
			session.getTransaction().commit();

		} catch (Exception es) {
			es.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	// delete employee from the database
		public static void delete(Employee obj) {
			Session session = factory.getCurrentSession();
			try {
				session.beginTransaction();
				session.delete(obj);
				session.getTransaction().commit();

			} catch (Exception es) {
				es.printStackTrace();
			} finally {
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
		} catch (Exception es) {
			es.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	// get all items from a table
	public static <T> List<T> loadAllData(Class<T> type) {
		Session session = factory.getCurrentSession();
		List<T> data = null;
		try {
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<T> criteria = builder.createQuery(type);
			criteria.from(type);
			data = session.createQuery(criteria).getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return data;
	}

	// this method updates the tasks in the checklist administrator window
	public static <T> void updateItemChecklistItem(Class<T> type, int id, String text, String category,
			String frequency, String status, DatePicker localdate) {

		// it sets the string the simple name of the class
		String classType = null;
		if (category.matches(ChecklistCategory.FIRE_WARDEN.getCategory())) {
			classType = "FireWarden";
		} else if (category.matches(ChecklistCategory.HEALTH_SAFETY.getCategory())) {
			classType = "HealthSafetyChecklist";
		} else if (category.matches(ChecklistCategory.TASK.getCategory())) {
			classType = "Task";
		}

		Session session = factory.getCurrentSession();
		
		ChecklistSuperClass update=null;
		try {
			session.beginTransaction();
			update = (ChecklistSuperClass) session.get(type, id);

			if (!update.getClass().getSimpleName().matches(classType)) {
				if (classType.matches("FireWarden")) {
					FireWarden firewarden = new FireWarden(text, status, frequency);
					firewarden.setDueDate(localdate.getValue());
					session.delete(update);
					session.save(firewarden);
					session.getTransaction().commit();
					return;

				} else if (classType.matches("HealthSafetyChecklist")) {
					HealthSafetyChecklist hs = new HealthSafetyChecklist(text, status, frequency);
					hs.setDueDate(localdate.getValue());
					session.delete(update);
					session.save(hs);
					session.getTransaction().commit();
					return;
					
				} else if (classType.matches("Task")) {
					Task task = new Task(text, status, frequency);
					task.setDueDate(localdate.getValue());
					session.delete(update);
					session.save(task);
					session.getTransaction().commit();
					return;
							
					
				} 

			}else {
				update.setItemDescription(text);
				update.setFrequency(frequency);
				update.setStatus(status);
				update.setDueDate(localdate.getValue());
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

}
