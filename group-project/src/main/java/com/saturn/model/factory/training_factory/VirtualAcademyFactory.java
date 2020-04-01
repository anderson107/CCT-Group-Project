package com.saturn.model.factory.training_factory;

import java.time.LocalDate;

import com.saturn.model.training.TrainingSuperClass;
import com.saturn.model.training.VirtualAcademyTraining;

public class VirtualAcademyFactory extends AbstractTrainingFactory {

	public VirtualAcademyFactory(String training) {
		this.setTraining(training);
		this.setClassName("Virtual Academy");
		this.setCheckbox(null);
		this.setCreationDate(LocalDate.now());
	}
	@Override
	public TrainingSuperClass createTraining() {
		// TODO Auto-generated method stub
		return new VirtualAcademyTraining(this.getTraining());
	}

}
