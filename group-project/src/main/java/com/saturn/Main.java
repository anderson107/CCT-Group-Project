package com.saturn;

import java.io.IOException;

import com.controller.MainMenuController;
import com.controller.Views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

	private Stage primaryStage;

	private BorderPane mainlayout;

	public static Stage stage;

	MainMenuController mainMenu;

	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("HEALTH AND SAFETY CONTROL APPLICATION");
		showMainView();
	}

	private void showMainView() throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource(Views.MAIN_MENU));
		mainlayout = loader.load();
		Scene scene = new Scene(mainlayout);
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		stage = primaryStage;
		mainMenu = new MainMenuController();

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Views.LOGIN));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(primaryStage);
		stage.setTitle("LOGIN");
		stage.setResizable(false);
		stage.setOnCloseRequest((WindowEvent event1) -> {
			primaryStage.close();
		       stage.close();
		    });

		primaryStage.show();
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}