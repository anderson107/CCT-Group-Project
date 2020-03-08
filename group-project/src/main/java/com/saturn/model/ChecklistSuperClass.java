package com.saturn.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChecklistSuperClass {

	private int id;
	private String itemDescription;
	private String frequency;
	private String status;
}
