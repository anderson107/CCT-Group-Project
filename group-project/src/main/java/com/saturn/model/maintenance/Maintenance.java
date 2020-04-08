package com.saturn.model.maintenance;

import java.time.LocalDate;

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
@Table(name="maintenance")
@NoArgsConstructor
public class Maintenance {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="company")
	private String company;
	
	@Column(name="description")
	private String description;
	
	@Column(name="creation_date")
	private LocalDate creationDate;
	
	@Column(name="last_date")
	private LocalDate lastDate;
	
	@Column(name="next_date")
	private LocalDate nextDate;
	
	@Transient
	private CheckBox checkbox;

	public Maintenance(String company, String description) {
		this.company = company;
		this.description = description;
		this.creationDate = LocalDate.now();
		this.nextDate = null;
		this.checkbox = null;
		this.lastDate = null;
	}
	
	
}
