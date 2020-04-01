package com.saturn.model.factory.checklist_factory;

import com.saturn.model.checklists.ChecklistSuperClass;

public class ChecklistFactory {

	public static ChecklistSuperClass getItem(AbstractChecklistFactory factory) {
		return factory.createItem();
	}
}
