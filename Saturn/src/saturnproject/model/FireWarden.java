package saturnproject.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class FireWarden {
	
	private int id;
	private String task;
	
	List<FireWarden>firewardenTasks;
	
	public FireWarden() {
		
	}
	
	public FireWarden(String task) {
		this.task = task;
	}
	
	// method to add fire warden task to the fire warden list
	public void addTask(FireWarden fireWarden) {
		if(firewardenTasks==null) {
			firewardenTasks = new ArrayList<>();
		}else {
			firewardenTasks.add(fireWarden);
		}
	}
	
	// method to remove the task from the list
	public void removeTask(int id) {
		ListIterator<FireWarden>iterator = firewardenTasks.listIterator();
		
		while(iterator.hasNext()) {
			FireWarden fireWarden = iterator.next();
			if(fireWarden.getId()==id) {
				iterator.remove();
				break;
			}
		}
		
	}
	
	// method to get fire warden task
	public FireWarden getTask(int id) {
		for(FireWarden f: firewardenTasks) {
			if(f.getId()==id) {
				return f;
			}
		}
		return null;
	}

	public int getId() {
		return id;
	}
}
