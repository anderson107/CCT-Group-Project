package com.saturn.model.checklists;


import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "coffee_haccp")
@NoArgsConstructor
public class CoffeeHACCP extends ChecklistSuperClass {


	// ******* constructors **************

	public CoffeeHACCP(String itemDescription, String status, String frequency) {
		super(itemDescription, status, frequency);
	}
}
