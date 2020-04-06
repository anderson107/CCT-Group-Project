package com.saturn.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.saturn.model.checklists.FireWarden;
import com.saturn.model.checklists.HealthSafetyChecklist;
import com.saturn.model.employee.Employee;
import com.saturn.model.task.Task;
import com.saturn.model.training.EmployeeHSE;
import com.saturn.model.training.EmployeeSeaChange;
import com.saturn.model.training.EmployeeVirtualAcademy;
import com.saturn.model.training.HSETraining;
import com.saturn.model.training.SeaChangeTraining;
import com.saturn.model.training.VirtualAcademyTraining;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class DataSource {

	@Getter @Setter
	private Session currentSession;
	
	@Getter @Setter
	private Transaction currentTransaction;
	
	public DataSource() {
		
	}
	
	public Session openSession() {
		
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}

	public SessionFactory getSessionFactory() {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
		.addAnnotatedClass(FireWarden.class).addAnnotatedClass(HealthSafetyChecklist.class)
		.addAnnotatedClass(Task.class).addAnnotatedClass(Employee.class)
		.addAnnotatedClass(VirtualAcademyTraining.class).addAnnotatedClass(HSETraining.class)
		.addAnnotatedClass(SeaChangeTraining.class).addAnnotatedClass(EmployeeSeaChange.class)
		.addAnnotatedClass(EmployeeHSE.class).addAnnotatedClass(EmployeeVirtualAcademy.class).buildSessionFactory();
        return sessionFactory;
	}
	
	public <T> void save(T t) {
		getCurrentSession().save(t);
	}
	
	public <T> Class<T> get(Class<T>type, int id){
		T t1 = getCurrentSession().get(type, id);
		return (Class<T>) t1;
	}
	
	public <T> List<T> getAll(String hql){
		List<T>list = getCurrentSession().createQuery(hql).getResultList();
		return list;
	}
	
	public void close() {
		currentSession.close();
	}
	
}
