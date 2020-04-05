package com.saturn.model.checklists;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.NoArgsConstructor;

@Entity
@Table(name="deli_haccp")
@NoArgsConstructor
public class DeliHACCP extends ChecklistSuperClass{

	//***** private fields ************
		@Transient
		private List<DeliHACCP>delichecklist;
		
		//******* constructors **************
		
		public DeliHACCP(String itemDescription, String status, String frequency) {
			super(itemDescription, status, frequency);
		}

}
