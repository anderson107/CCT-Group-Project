package com.saturn.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import lombok.Data;

@Data
public class HealthSafetyChecklist implements Tasks {

	//***** private fields ************
	
	private int id;
	private String frequency;
	private String status;
	private List<HealthSafetyChecklist>hsChecklist;
	
	
	//******* constructors **************
	
	public HealthSafetyChecklist() {
		
	}
	
	public HealthSafetyChecklist(String status, String frequency) {
		this.frequency = frequency;
		this.status = status;
	}
	
	//********* public methods ***************
	
	// method to add a health and safety task
	@Override
	public void addTask(Object obj) {
		
		if(hsChecklist == null) {
			hsChecklist = new ArrayList<>();
			hsChecklist.add((HealthSafetyChecklist) obj);
		}else {
			hsChecklist.add((HealthSafetyChecklist) obj);
		}
	}

	// method to remove a H&S task
	@Override
	public void removeTask(int id) {
		
		ListIterator<HealthSafetyChecklist> iterator = hsChecklist.listIterator();
		
		while(iterator.hasNext()) {
			HealthSafetyChecklist hs = iterator.next();
			
			if(hs.getId()==id) {
				iterator.remove();
				break;
			}
		}
	}

	// method to get a H&S task
	@Override
	public HealthSafetyChecklist getTask(int id) {
		
		for(HealthSafetyChecklist hs: hsChecklist) {
			if(hs.getId()==id) {
				return hs;
			}
		}
		return null;
	}

}
