package com.saturn.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javafx.scene.control.CheckBox;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Task extends ChecklistSuperClass implements Checklist {

	
	private List<Task>taskList;
	
	public Task(String task, String status, String frequency) {
		this.setItemDescription(task);
		this.setStatus(status);
		this.setFrequency(frequency);
		//this.setCheckbox(new CheckBox());
		this.setDueDate(LocalDate.now());
	}
		
	@Override
	public void addItem(Checklist task) {
		if(taskList==null) {
			taskList = new ArrayList<>();
			taskList.add((Task) task);
		}else {
			taskList.add((Task) task);
		}
		
	}

	@Override
	public void removeItem(int id) {
    ListIterator<Task>iterator = taskList.listIterator();
		
		while(iterator.hasNext()) {
			Task task = iterator.next();
			
			if(task.getId()==id) {
				iterator.remove();
				break;
			}
		}
		
	}

	@Override
	public Task getItem(int id) {
		for(Task t: taskList) {
			if(t.getId()==id) {
				return t;
			}
		}
		return null;
	}
}
