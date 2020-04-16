package com.saturn.model.checklists;


import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@Entity
@Table(name="deli_haccp")
@NoArgsConstructor
public class DeliHACCP extends ChecklistSuperClass{

		//******* constructors **************
		
		public DeliHACCP(String itemDescription, String status, String frequency) {
			super(itemDescription, status, frequency);
		}

}
