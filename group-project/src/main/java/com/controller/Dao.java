package com.controller;

import java.time.LocalDate;
import java.util.List;

import com.saturn.dao.DataSource;
import com.saturn.model.maintenance.Maintenance;

public class Dao {

	private DataSource data;
	
	protected Dao() {
		data = DataSource.getInstance();
	}
	
	// it adds a object to the database
	protected void add(Object obj) {
	
		data.openSession();
		try {
			data.beginTransaction();
			data.add(obj);
			data.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			data.closeSession();
		}
	}

	// it retrieves all objects from maintenance
	protected List<Maintenance> loadAllMaintenance(){
		List<Maintenance>list = null;
		data.openSession();
		try {
			data.beginTransaction();
			list = data.loadAll("from Maintenance");
			data.commit();
		}catch(Exception e) {
			data.closeSession();
		}
		return list;
	}

	// it deletes from the database
	protected void delete(Object obj) {
		data.openSession();
		try {
			data.beginTransaction();
			data.delete(obj);
			data.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			data.closeSession();
		}
	}

	// it gets a maintenance object
	protected Maintenance getMaintenance(int id) {
		data.openSession();
		try {
			data.beginTransaction();
			Maintenance maintenance = (Maintenance) data.get(Maintenance.class, id);
			data.commit();
			return maintenance;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			data.closeSession();
		}
		return null;
	}

	// it updates a maintenance object
	protected void updateMaintenance(int id, String contractor, String service, LocalDate lastDate, LocalDate nextDate) {
		data.openSession();
		try {
			data.beginTransaction();
			Maintenance maintenance = data.getSession().get(Maintenance.class, id);
			maintenance.setCompany(contractor);
			maintenance.setDescription(service);
			maintenance.setLastDate(lastDate);
			maintenance.setNextDate(nextDate);
			data.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			data.closeSession();
		}
	}
}
