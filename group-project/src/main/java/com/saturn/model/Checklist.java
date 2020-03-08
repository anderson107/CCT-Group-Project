package com.saturn.model;

public interface Checklist {
	
	void addItem(Checklist obj);
	void removeItem(int id);
	Checklist getItem(int id);
}
