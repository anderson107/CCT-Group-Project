package com.saturn.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.NoArgsConstructor;

@Entity
@Table(name="hs")
@NoArgsConstructor
public class HealthSafetyChecklist extends ChecklistSuperClass implements Checklist {

	//***** private fields ************
	@Transient
	private List<HealthSafetyChecklist>hsChecklist;
	
	//******* constructors **************
	
	public HealthSafetyChecklist(String itemDescription, String status, String frequency) {
		this.setItemDescription(itemDescription);
		this.setFrequency(frequency);
		this.setStatus(status);
		this.setCheckbox(null);
		this.setDueDate(LocalDate.now());
		this.setCreationDate(LocalDate.now());
	}
	
	//********* public methods ***************
	
	// method to add a health and safety task
	@Override
	public void addItem(Checklist obj) {
		
		if(hsChecklist == null) {
			hsChecklist = new ArrayList<>();
			hsChecklist.add((HealthSafetyChecklist) obj);
		}else {
			hsChecklist.add((HealthSafetyChecklist) obj);
		}
	}

	// method to remove a H&S task
	@Override
	public void removeItem(int id) {
		
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
	public HealthSafetyChecklist getItem(int id) {
		
		for(HealthSafetyChecklist hs: hsChecklist) {
			if(hs.getId()==id) {
				return hs;
			}
		}
		return null;
	}

}
