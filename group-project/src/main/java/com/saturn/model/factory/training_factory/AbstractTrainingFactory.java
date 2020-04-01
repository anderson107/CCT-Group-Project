package com.saturn.model.factory.training_factory;

import java.time.LocalDate;

import com.saturn.model.training.TrainingSuperClass;

import javafx.scene.control.CheckBox;
import lombok.Data;

@Data
public abstract class AbstractTrainingFactory {

	private String training;
	private String className;
	private LocalDate creationDate;
	private CheckBox checkbox;
	
	public abstract TrainingSuperClass createTraining();
}
