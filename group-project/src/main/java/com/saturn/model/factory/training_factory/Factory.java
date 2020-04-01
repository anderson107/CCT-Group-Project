package com.saturn.model.factory.training_factory;

import com.saturn.model.training.HSETraining;
import com.saturn.model.training.SeaChangeTraining;
import com.saturn.model.training.TrainingSuperClass;
import com.saturn.model.training.VirtualAcademyTraining;

public class Factory {

	public static TrainingSuperClass getTraining(String training, String type) {
		
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
