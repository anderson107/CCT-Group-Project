package com.saturn.model.factory.training_factory;

import java.time.LocalDate;

import com.saturn.model.training.SeaChangeTraining;
import com.saturn.model.training.TrainingSuperClass;

public class SeaChangeFactory extends AbstractTrainingFactory {

	
	public SeaChangeFactory(String training) {
		this.setTraining(training);
		this.setClassName("SeaChange");
		this.setCheckbox(null);
		this.setCreationDate(LocalDate.now());
	}
	
	@Override
	public TrainingSuperClass createTraining() {
		// TODO Auto-generated method stub
		return new SeaChangeTraining(this.getTraining());
	}

}
