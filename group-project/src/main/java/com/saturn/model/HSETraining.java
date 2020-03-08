package com.saturn.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HSETraining extends TrainingSuperClass implements Training {

	private List<HSETraining>hseList;
	
	public HSETraining(String training, String status) {
		this.setTraining(training);
		this.setStatus(status);
	}
	
	@Override
	public void addTraining(Training training) {

		if(hseList==null) {
			hseList = new ArrayList<>();
			hseList.add((HSETraining) training);
		}else {
			hseList.add((HSETraining) training);
		}
	}

	@Override
	public void removeTraining(int id) {
		
		ListIterator<HSETraining>iterator = hseList.listIterator();
		while(iterator.hasNext()) {
			HSETraining hse = iterator.next();
			
			if(hse.getId()==id) {
				iterator.remove();
				break;
			}
		}

	}

	@Override
	public HSETraining getTraining(int id) {
		
		for(HSETraining hse: hseList) {
			if(hse.getId()==id) {
				return hse;
			}
		}
		return null;
	}

}
