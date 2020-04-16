package com.saturn.model.checklists;


import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@Entity
@Table(name="floor_haccp")
@NoArgsConstructor
public class FloorHACCP extends ChecklistSuperClass {


	// ******* constructors **************
	public FloorHACCP(String itemDescription, String status, String frequency) {
		super(itemDescription, status, frequency);
	}
}
