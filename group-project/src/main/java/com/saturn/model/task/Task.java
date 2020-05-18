package com.saturn.model.task;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import javafx.scene.control.CheckBox;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="task")
@NoArgsConstructor
public class Task {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="item_description")
	private String itemDescription;
	
	@Column(name="status")
	private String status;
	
	@Column(name="creation_date")
	private LocalDate creationDate;
	
	@Column(name="date")
	private LocalDate date;
	
	@Column(name="action_description")
	private String action;
	
	@Transient
	private CheckBox checkbox;
	
	public Task(String itemDescription) {
		
		this.itemDescription = itemDescription;
		this.status = "Pending";
		this.creationDate = LocalDate.now();
		this.date = null;
		this.action = null;
		this.checkbox = null;
	}
		
}
