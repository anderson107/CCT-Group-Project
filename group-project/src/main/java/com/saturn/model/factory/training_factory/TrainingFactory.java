package com.saturn.model.factory.training_factory;

import com.saturn.model.training.TrainingSuperClass;

public class TrainingFactory {

	public static TrainingSuperClass getTraining(AbstractTrainingFactory factory) {
		return factory.createTraining();
	}
}
