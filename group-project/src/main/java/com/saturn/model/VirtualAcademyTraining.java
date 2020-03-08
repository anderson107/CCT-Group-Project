package com.saturn.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VirtualAcademyTraining extends TrainingSuperClass implements Training {

	private List<VirtualAcademyTraining> virtualAcademy;
	
	public VirtualAcademyTraining(String training, String status) {
		this.setTraining(training);
		this.setStatus(status);
	}
	
	@Override
	public void addTraining(Training training) {
		if(virtualAcademy==null) {
			virtualAcademy = new ArrayList<>();
			virtualAcademy.add((VirtualAcademyTraining) training);
		}else {
			virtualAcademy.add((VirtualAcademyTraining) training);
		}
	}

	@Override
	public void removeTraining(int id) {

		ListIterator<VirtualAcademyTraining> iterator = virtualAcademy.listIterator();
		
		while(iterator.hasNext()) {
			VirtualAcademyTraining vat = iterator.next();
			
			if(vat.getId()==id) {
				iterator.remove();
				break;
			}
		}
	}

	@Override
	public VirtualAcademyTraining getTraining(int id) {

		for(VirtualAcademyTraining v: virtualAcademy) {
			if(v.getId()==id) {
				return v;
			}
		}
		return null;
	}

}
