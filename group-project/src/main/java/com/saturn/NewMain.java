package com.saturn;

import com.saturn.dao.DatabaseConnection;
import com.saturn.model.ChecklistSuperClass;
import com.saturn.model.FireWarden;

public class NewMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Main.main(args);
		//FireWarden firewarden = new FireWarden("blalalala", "blalala", "Bllalallaa");
		//DatabaseConnection.addChecklistItem(firewarden);
		ChecklistSuperClass obj = DatabaseConnection.getChecklistItem(1, "FireWarden");
		System.out.println(obj);
	}

}
