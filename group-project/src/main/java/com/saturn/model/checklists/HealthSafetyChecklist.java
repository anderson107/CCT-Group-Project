package com.saturn.model.checklists;


import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@Entity
@Table(name="hs")
@NoArgsConstructor
public class HealthSafetyChecklist extends ChecklistSuperClass{

	
	//******* constructors **************
	
	public HealthSafetyChecklist(String itemDescription, String status, String frequency) {
		super(itemDescription, status, frequency);
	}
}
