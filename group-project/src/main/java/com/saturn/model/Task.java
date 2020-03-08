package com.saturn.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Task {

	private int id;
	private String task;
	private String status;
	private List<Task>taskList;
	
	public Task(String task, String status) {
		this.task = task;
		this.status = status;
	}
	
	public void addTask(Task task) {
		if(taskList==null) {
			taskList = new ArrayList<>();
			taskList.add(task);
		}else {
			taskList.add(task);
		}
	}
	
	public void removeTask(int id) {
		ListIterator<Task>iterator = taskList.listIterator();
		
		while(iterator.hasNext()) {
			Task task = iterator.next();
			
			if(task.getId()==id) {
				iterator.remove();
				break;
			}
		}
	}
	
	public Task getTask(int id) {
		
		for(Task t: taskList) {
			if(t.getId()==id) {
				return t;
			}
		}
		return null;
	}
}
