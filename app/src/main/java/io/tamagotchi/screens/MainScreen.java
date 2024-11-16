package io.tamagotchi.screens;

import io.tamagotchi.pet.Pet;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainScreen extends Application {
    private final Pet pet;
    private final String language;

    public MainScreen(Pet pet, String language) {
        this.pet = pet;
        this.language = language;
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        Text info = new Text("Welcome to Main Screen!\nPet: " + pet.getName() + "\nLanguage: " + language);
        root.getChildren().add(info);

        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("PrepPal");
        primaryStage.show();
    }
}
