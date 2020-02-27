package saturnproject.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class FireWarden implements Tasks {
	
	// ******fields********
	
	private int id;
	private String task;
	private String status;
	private String frequency;
	
	List<FireWarden>firewardenTasks;
	
	// *******constructors*******
	
	public FireWarden() {
		
	}
	
	public FireWarden(String task, String status, String frequency) {
		this.task = task;
		this.status = status;
		this.frequency = frequency;
	}
	
	// *******public methods*********
	
	// method to add fire warden task to the fire warden list
	@Override
	public void addTask(Object fireWarden) {
		if(firewardenTasks==null) {
			firewardenTasks = new ArrayList<>();
		}else {
			firewardenTasks.add((FireWarden) fireWarden);
		}
	}
	
	// method to remove the task from the list
	@Override
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
	@Override
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
