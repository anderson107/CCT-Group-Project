package com.saturn.model.training;

public class TrainingFactory {

	public static TrainingSuperClass create(String training, String type) {
		
		switch(type) {
		case "SeaChange":
			return new SeaChangeTraining(training);
		case "HSE":
			return new HSETraining(training);
		case "Virtual Academy":
			return new VirtualAcademyTraining(training);
		}
		return null;
	}
}
