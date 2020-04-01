package com.saturn.model.checklists;

public interface Checklist {
	
	void addItem(Checklist obj);
	void removeItem(int id);
	Checklist getItem(int id);
}
