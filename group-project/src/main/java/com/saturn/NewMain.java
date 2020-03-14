package com.saturn;

import java.util.List;

import com.saturn.dao.DatabaseConnection;
import com.saturn.model.ChecklistSuperClass;
import com.saturn.model.FireWarden;
import com.saturn.model.HealthSafetyChecklist;
import com.saturn.model.Task;

public class NewMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main.main(args);
		
		Task task = new Task("Enter enter", "Enter enter", "Enter enter");
		
		DatabaseConnection.addChecklistItem(task);
		
		List<Task>list = DatabaseConnection.loadAllData(Task.class);
		
		for(Task t: list) {
			System.out.println(t);
		}
		
	}

}
