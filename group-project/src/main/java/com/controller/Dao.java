package com.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.swing.JOptionPane;

import com.saturn.dao.DataSource;
import com.saturn.model.Administrator;
import com.saturn.model.checklists.ChecklistCategory;
import com.saturn.model.checklists.ChecklistSuperClass;
import com.saturn.model.checklists.CoffeeHACCP;
import com.saturn.model.checklists.DeliHACCP;
import com.saturn.model.checklists.FireWarden;
import com.saturn.model.checklists.FloorHACCP;
import com.saturn.model.checklists.HealthSafetyChecklist;
import com.saturn.model.employee.Employee;
import com.saturn.model.maintenance.Maintenance;
import com.saturn.model.task.Task;
import com.saturn.model.training.EmployeeTraining;
import com.saturn.model.training.HSETraining;
import com.saturn.model.training.SeaChangeTraining;
import com.saturn.model.training.TrainingSuperClass;
import com.saturn.model.training.VirtualAcademyTraining;

import javafx.scene.control.DatePicker;

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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			data.closeSession();
		}
	}

	// it loads all the checklist items
	protected List<ChecklistSuperClass> loadChecklistItems() {

		List<ChecklistSuperClass> list = new ArrayList<>();
		list.addAll(loadAllData(FireWarden.class));
		list.addAll(loadAllData(HealthSafetyChecklist.class));
		list.addAll(loadAllData(DeliHACCP.class));
		list.addAll(loadAllData(FloorHACCP.class));
		list.addAll(loadAllData(CoffeeHACCP.class));

		return list;
	}
	
	// it loads all the training items
	protected List<TrainingSuperClass> loadTrainingItems(){
		
		List<TrainingSuperClass> list = new ArrayList<>();
		list.addAll(loadAllData(HSETraining.class));
		list.addAll(loadAllData(SeaChangeTraining.class));
		list.addAll(loadAllData(VirtualAcademyTraining.class));
		
		return list;
	}

	// it returns a item
	protected Administrator getAdministrator(int id) {
		data.openSession();
		try {
			data.beginTransaction();
			Administrator adm = (Administrator) data.get(Administrator.class, id);
			data.commit();
			return adm;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			data.closeSession();
		}
		return null;
	}

	// it deletes from the database
	protected void delete(Object obj) {
		data.openSession();
		try {
			data.beginTransaction();
			data.delete(obj);
			data.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			data.closeSession();
		}
		return null;
	}

	// it updates a maintenance object
	protected void updateMaintenance(int id, String contractor, String service, LocalDate lastDate,
			LocalDate nextDate) {
		data.openSession();
		try {
			data.beginTransaction();
			Maintenance maintenance = data.getSession().get(Maintenance.class, id);
			maintenance.setCompany(contractor);
			maintenance.setDescription(service);
			maintenance.setLastDate(lastDate);
			maintenance.setNextDate(nextDate);
			data.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			data.closeSession();
		}
	}

	// it queries the database and returns a list
	protected <T> List<T> queryDB(String query) {
		data.openSession();
		List<T> queryResults = new ArrayList<>();

		try {
			data.beginTransaction();
			@SuppressWarnings("unchecked")
			List<T> results = data.getSession().createQuery(query).getResultList();
			queryResults.addAll(results);

			data.commit();

			return queryResults;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			data.closeSession();
			;
		}
		return null;
	}

	// it loads all the data from a given table
	protected <T> List<T> loadAllData(Class<T> type) {
		data.openSession();
		List<T> list = null;
		try {
			data.beginTransaction();
			CriteriaBuilder builder = data.getSession().getCriteriaBuilder();
			CriteriaQuery<T> criteria = builder.createQuery(type);
			criteria.from(type);
			list = data.getSession().createQuery(criteria).getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			data.closeSession();
		}
		return list;
	}

	// it updates the table status column
	protected void updateChecklist(List<ChecklistSuperClass> list) {

		List<ChecklistSuperClass> update = list;

		data.openSession();

		try {
			data.beginTransaction();

			for (ChecklistSuperClass item : update) {

				ChecklistSuperClass obj = (ChecklistSuperClass) data.getSession().get(item.getClass().getName(),
						item.getId());
				obj.setStatus("Done");
				data.getSession().save(obj);
			}
			data.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			data.closeSession();
		}

	}

	// it updates the employee information in the database
	public void updateEmployee(int id, String choice, String update) {

		data.openSession();

		try {
			data.beginTransaction();
			Employee emp = data.getSession().get(Employee.class, id);

			switch (choice) {
			case "First Name":
				emp.setFirstName(update);
				break;
			case "Last Name":
				emp.setLastName(update);
				break;
			case "Address":
				emp.setAddress(update);
				break;
			case "Email":
				emp.setEmail(update);
				break;
			case "Mobile":
				emp.setMobile(update);
				break;
			case "Telephone":
				emp.setTelephone(update);
				break;
			case "City/County":
				emp.setCity(update);
				break;
			}

			data.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			data.closeSession();
			;
		}

	}

	// it updates the employee training
	@SuppressWarnings("unchecked")
	public void updateEmployeeTraining(List<EmployeeTraining> training) {

		data.openSession();

		try {
			data.beginTransaction();

			List<EmployeeTraining> trainingList = new ArrayList<>();
			trainingList.addAll(data.getSession().createQuery("from EmployeeVirtualAcademy").getResultList());
			trainingList.addAll(data.getSession().createQuery("from EmployeeSeaChange").getResultList());
			trainingList.addAll(data.getSession().createQuery("from EmployeeHSE").getResultList());

			for (EmployeeTraining et : trainingList) {
				for (EmployeeTraining update : training) {

					if (et.getEmployee().getId() == update.getEmployee().getId()
							&& et.getTraining().getId() == update.getTraining().getId()) {
						et.setStatus(update.getStatus());
						et.setDate(update.getDate());
						data.getSession().update(et);
					}

				}
			}

			data.commit();

		} catch (Exception es) {
			es.printStackTrace();
		} finally {
			data.closeSession();
		}

	}

	// this method updates the tasks in the checklist administrator window
	public <T> void updateItemChecklistItem(Class<T> type, int id, String text, String category, String frequency,
			String status, DatePicker localdate) {

		// it sets the string the simple name of the class
		String classType = null;
		if (category.matches(ChecklistCategory.FIRE_WARDEN.getCategory())) {
			classType = "FireWarden";
		} else if (category.matches(ChecklistCategory.HEALTH_SAFETY.getCategory())) {
			classType = "HealthSafetyChecklist";
		} else if (category.matches(ChecklistCategory.COFFEE_HACCP.getCategory())) {
			classType = "CoffeeHACCP";
		} else if (category.matches(ChecklistCategory.DELI_HACCP.getCategory())) {
			classType = "DeliHACCP";
		} else if (category.matches(ChecklistCategory.FLOOR_HACCP.getCategory())) {
			classType = "FloorHACCP";
		}
		data.openSession();

		ChecklistSuperClass update = null;
		try {
			data.beginTransaction();
			update = (ChecklistSuperClass) data.getSession().get(type, id);

			if (!update.getClass().getSimpleName().matches(classType)) {
				if (classType.matches("FireWarden")) {
					FireWarden firewarden = new FireWarden(text, status, frequency);
					firewarden.setDueDate(update.getDueDate());
					data.delete(update);
					data.add(firewarden);
					data.commit();
					return;

				} else if (classType.matches("HealthSafetyChecklist")) {
					HealthSafetyChecklist hs = new HealthSafetyChecklist(text, status, frequency);
					hs.setDueDate(update.getDueDate());
					data.delete(update);
					data.add(hs);
					data.commit();
					return;

				} else if (classType.matches("DeliHACCP")) {
					DeliHACCP deli = new DeliHACCP(text, status, frequency);
					deli.setDueDate(update.getDueDate());
					data.delete(update);
					data.add(deli);
					data.commit();
					return;

				} else if (classType.matches("CoffeeHACCP")) {
					CoffeeHACCP coffee = new CoffeeHACCP(text, status, frequency);
					coffee.setDueDate(update.getDueDate());
					data.delete(update);
					data.add(coffee);
					data.commit();
					return;

				} else if (classType.matches("FloorHACCP")) {
					FloorHACCP floor = new FloorHACCP(text, status, frequency);
					floor.setDueDate(update.getDueDate());
					data.delete(update);
					data.add(floor);
					data.commit();
					return;
				}

			} else {
				update.setItemDescription(text);
				update.setFrequency(frequency);
				update.setStatus(status);
				update.setDueDate(localdate.getValue());
				data.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			data.closeSession();
		}

	}

	// it updates a task object
	public void updateTask(int id, String textArea, String action, LocalDate date, String status) {

		data.openSession();

		try {
			data.beginTransaction();
			Task task = data.getSession().get(Task.class, id);
			task.setAction(action);
			task.setItemDescription(textArea);
			task.setDate(date);
			task.setStatus(status);

			data.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			data.closeSession();
		}

	}

	// it updates a training object
	public <T> void updateTraining(Class<T> type, int id, String training, String category) {

		// it sets the string the simple name of the class
		String classType = null;
		if (category.matches("Virtual Academy")) {
			classType = "VirtualAcademyTraining";
		} else if (category.matches("HSE")) {
			classType = "HSETraining";
		} else if (category.matches("SeaChange")) {
			classType = "SeaChangeTraining";
		}

		data.openSession();

		TrainingSuperClass update = null;
		try {
			data.beginTransaction();
			update = (TrainingSuperClass) data.getSession().get(type, id);

			if (!update.getClass().getSimpleName().matches(classType)) {
				if (classType.matches("SeaChangeTraining")) {
					SeaChangeTraining seaChange = new SeaChangeTraining(training);
					seaChange.setCreationDate(update.getCreationDate());
					data.delete(update);
					seaChange.setClassName("SeaChange");
					data.add(seaChange);
					data.commit();
					return;

				} else if (classType.matches("VirtualAcademyTraining")) {
					VirtualAcademyTraining virtualAcademy = new VirtualAcademyTraining(training);
					virtualAcademy.setCreationDate(update.getCreationDate());
					data.delete(update);
					virtualAcademy.setClassName("Virtual Academy");
					data.add(virtualAcademy);
					data.commit();
					return;

				} else if (classType.matches("HSETraining")) {
					HSETraining hse = new HSETraining(training);
					hse.setCreationDate(update.getCreationDate());
					data.delete(update);
					hse.setClassName("HSE");
					data.add(hse);
					data.commit();
					return;

				}

			} else {
				update.setTraining(training);
				data.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			data.closeSession();
			;
		}

	}

	// it updates the checklist item due date if the item is marked as Done
	protected void updateDBChecklistDates(List<ChecklistSuperClass> list) {

		data.openSession();

		try {

			data.beginTransaction();

			for (ChecklistSuperClass item : list) {

				if (item.getDueDate().isBefore(LocalDate.now()) && item.getStatus().matches("Done")) {

					ChecklistSuperClass listItem = (ChecklistSuperClass) data.getSession()
							.get(item.getClass().getName(), item.getId());

					switch (listItem.getFrequency()) {

					case "Daily":
						listItem.setDueDate(LocalDate.now());
						listItem.setStatus("Pending");
						data.getSession().update(listItem);
						break;
					case "Weekly":
						listItem.setDueDate(LocalDate.now().plusDays(7));
						listItem.setStatus("Pending");
						data.getSession().update(listItem);
						break;
					case "Biweekly":
						listItem.setDueDate(LocalDate.now().plusDays(15));
						listItem.setStatus("Pending");
						data.getSession().update(listItem);
						break;
					case "Monthly":
						listItem.setDueDate(LocalDate.now().plusDays(30));
						listItem.setStatus("Pending");
						data.getSession().update(listItem);
						break;
					case "Semiannual":
						listItem.setDueDate(LocalDate.now().plusMonths(6));
						listItem.setStatus("Pending");
						data.getSession().update(listItem);
						break;
					case "Yearly":
						listItem.setDueDate(LocalDate.now().plusMonths(12));
						listItem.setStatus("Pending");
						data.getSession().update(listItem);
						break;
					}

				}
			}

			data.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			data.closeSession();
		}

	}
	
	protected void changePassword(String newPassword) {
		
		data.openSession();
		
		try {
			
			data.beginTransaction();
			Administrator adm = (Administrator) data.get(Administrator.class, 1);
			adm.setPassword(newPassword);
			data.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			data.closeSession();
		}
	}

	// it checks if it is time to call the contractor to execute the service
	protected void checkMaintenance() {

		List<Maintenance> list = new ArrayList<>();

		list.addAll(loadAllData(Maintenance.class));

		for (Maintenance m : list) {

			if (m.getNextDate().isBefore(LocalDate.now())) {

				JOptionPane
						.showMessageDialog(null,
								"Company:" + m.getCompany() + "\nService:" + m.getDescription()
										+ "\nLast Service: " + m.getLastDate() + "\nNext Service: " + m.getNextDate(),
										"Service Expired", JOptionPane.WARNING_MESSAGE);
			}
		}

	}
}
