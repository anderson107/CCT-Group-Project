package com.saturn.model.factory.training_factory;

import java.time.LocalDate;

import com.saturn.model.training.HSETraining;
import com.saturn.model.training.TrainingSuperClass;

public class HSETrainingFactory extends AbstractTrainingFactory {

	public HSETrainingFactory(String training) {
		this.setTraining(training);
		this.setClassName("HSE");
		this.setCheckbox(null);
		this.setCreationDate(LocalDate.now());
	}
	
	@Override
	public TrainingSuperClass createTraining() {
		// TODO Auto-generated method stub
		return new HSETraining(this.getTraining());
	}

}
