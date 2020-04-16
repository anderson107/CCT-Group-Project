package com.saturn.model.checklists;


import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@Entity
@Table(name="firewarden")
@NoArgsConstructor
public class FireWarden extends ChecklistSuperClass {
	
	// *******constructors*******
	
	public FireWarden(String itemDescription, String status, String frequency) {
		super(itemDescription, status, frequency);
	}
	
}
