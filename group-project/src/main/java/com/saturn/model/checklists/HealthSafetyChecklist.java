package com.saturn.model.checklists;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.NoArgsConstructor;

@Entity
@Table(name="hs")
@NoArgsConstructor
public class HealthSafetyChecklist extends ChecklistSuperClass{

	//***** private fields ************
	@Transient
	private List<HealthSafetyChecklist>hsChecklist;
	
	//******* constructors **************
	
	public HealthSafetyChecklist(String itemDescription, String status, String frequency) {
		super(itemDescription, status, frequency);
	}
}
