package com.saturn.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import javafx.scene.control.CheckBox;
import lombok.NoArgsConstructor;

@Entity
@Table(name="firewarden")
@NoArgsConstructor
public class FireWarden extends ChecklistSuperClass implements Checklist {
	
	// ******fields********
	@Transient
	List<FireWarden>firewardenTasks;
	// *******constructors*******
	
	public FireWarden(String itemDescription, String status, String frequency) {
		this.setFrequency(frequency);
		this.setStatus(status);
		this.setItemDescription(itemDescription);
		//this.setCheckbox(new CheckBox());
		this.setCreationDate(LocalDate.now());
		this.setDueDate(LocalDate.now());
	}
	
	// *******public methods*********
	
	// method to add fire warden task to the fire warden list
	@Override
	public void addItem(Checklist fireWarden) {
		if(firewardenTasks==null) {
			firewardenTasks = new ArrayList<>();
			firewardenTasks.add((FireWarden) fireWarden);
		}else {
			firewardenTasks.add((FireWarden) fireWarden);
		}
	}
	
	// method to remove the task from the list
	@Override
	public void removeItem(int id) {
		ListIterator<FireWarden>iterator = firewardenTasks.listIterator();
		while(iterator.hasNext()) {
			FireWarden fireWarden = iterator.next();
			if(fireWarden.getId()==id) {
				iterator.remove();
				break;
			}
		}
		
	}
	
	// method to get fire warden task
	@Override
	public FireWarden getItem(int id) {
		for(FireWarden f: firewardenTasks) {
			if(f.getId()==id) {
				return f;
			}
		}
		return null;
	}

	
}
