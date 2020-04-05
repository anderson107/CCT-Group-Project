package com.saturn.model.checklists;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.NoArgsConstructor;

@Entity
@Table(name="floor_haccp")
@NoArgsConstructor
public class FloorHACCP extends ChecklistSuperClass {

	// ***** private fields ************
	@Transient
	private List<FloorHACCP> delichecklist;

	// ******* constructors **************
	public FloorHACCP(String itemDescription, String status, String frequency) {
		super(itemDescription, status, frequency);
	}
}
