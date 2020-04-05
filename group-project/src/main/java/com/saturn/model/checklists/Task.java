package com.saturn.model.checklists;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.NoArgsConstructor;

@Entity
@Table(name="task")
@NoArgsConstructor
public class Task extends ChecklistSuperClass{

	@Transient
	private List<Task>taskList;
	
	public Task(String task, String status, String frequency) {
		this.setItemDescription(task);
		this.setStatus(status);
		this.setFrequency(frequency);
		this.setCheckbox(null);
		this.setDueDate(LocalDate.now());
		this.setCreationDate(LocalDate.now());
	}
		
}
