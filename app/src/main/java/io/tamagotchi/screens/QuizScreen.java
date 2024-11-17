package io.tamagotchi.screens;

import io.tamagotchi.pet.Pet;
import javafx.application.Application;
import javafx.stage.Stage;

public class QuizScreen extends Application {
    Pet pet;
    String language;
    String mode;

    public QuizScreen(Pet pet, String language, String mode) {
        this.pet = pet;
        this.language = language;
        this.mode = mode;
    }

    @Override
    public void start(Stage primaryStage) {
    }
}
