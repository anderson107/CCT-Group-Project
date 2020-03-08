package com.saturn.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SeaChangeTraining extends TrainingSuperClass implements Training {

	private List<SeaChangeTraining> seaChangeList;
	
	public SeaChangeTraining(String trainingDescription, String status) {
		this.setTraining(trainingDescription);
		this.setStatus(status);
	}
	
	@Override
	public void addTraining(Training training) {

		if(seaChangeList==null) {
			seaChangeList = new ArrayList<>();
			seaChangeList.add((SeaChangeTraining) training);
		}else {
			seaChangeList.add((SeaChangeTraining) training);
		}
	}

	@Override
	public void removeTraining(int id) {
		
		ListIterator<SeaChangeTraining> iterator = seaChangeList.listIterator();
		
		while(iterator.hasNext()) {
			SeaChangeTraining sct = iterator.next();
			
			if(sct.getId()==id) {
				iterator.remove();
				break;
			}
			
		}
				
	}

	@Override
	public SeaChangeTraining getTraining(int id) {
		for(SeaChangeTraining s: seaChangeList) {
			if(s.getId()==id) {
				return s;
			}
		}
		return null;
	}

}
