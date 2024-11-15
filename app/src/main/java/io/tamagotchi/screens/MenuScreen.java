package io.tamagotchi.screens;

import io.tamagotchi.TamagotchiException;
import io.tamagotchi.pet.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MenuScreen extends Application {
    private Pet selectedPet = null;
    private String selectedLanguage = null;

    @Override
    public void start(Stage primaryStage) {
        // Main layout
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        // Title
        Text title = new Text("Welcome!\nPlease choose your tamagotchi pet and language that you need to prepare for interview:");
        title.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        // Pet selection buttons
        ToggleGroup petGroup = new ToggleGroup();
        HBox petSelection = createPetButtons(petGroup);

        // Language selection buttons
        ToggleGroup languageGroup = new ToggleGroup();
        HBox languageSelection = createLanguageButtons(languageGroup);

        // Start button
        Button startButton = new Button("Start");
        startButton.setStyle("-fx-font-size: 14;");
        startButton.setDisable(true); // Disable until selections are made

        // Enable the start button only if both selections are made
        petGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            selectedPet = (Pet) ((ToggleButton) newValue).getUserData();
            checkSelections(startButton, petGroup, languageGroup);
        });

        languageGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            selectedLanguage = (String) ((ToggleButton) newValue).getUserData();
            checkSelections(startButton, petGroup, languageGroup);
        });

//        startButton.setOnAction(event -> {
//            // Pass selectedPet and selectedLanguage to MainScreen
//            System.out.println("Pet: " + selectedPet.getName() + ", Language: " + selectedLanguage);
//            MainScreen mainScreen = new MainScreen(selectedPet, selectedLanguage);
//            try {
//                mainScreen.start(primaryStage); // Open MainScreen
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });

        // Add everything to the root layout
        root.getChildren().addAll(title, petSelection, languageSelection, startButton);

        // Show the stage
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Main Menu");
        primaryStage.show();
    }

    private HBox createPetButtons(ToggleGroup petGroup) {
        HBox petSelection = new HBox(20);
        petSelection.setAlignment(Pos.CENTER);

        List<Pet> pets = new ArrayList<>();
        try {
            pets.add(PetFactory.create("gradle"));
            pets.add(PetFactory.create("docker"));
            pets.add(PetFactory.create("linux"));
        } catch (TamagotchiException e) {
            throw new RuntimeException(e);
        }

        for (Pet pet : pets) {
            ToggleButton petButton = createPetButton(pet, petGroup);
            petSelection.getChildren().add(petButton);
        }

        return petSelection;
    }

    private ToggleButton createPetButton(Pet pet, ToggleGroup petGroup) {
        ImageView imageView = new ImageView(new Image(pet.getImageUrl()));
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);

        ToggleButton petButton = new ToggleButton(pet.getName() + "\n" + pet.getDescription());
        petButton.setGraphic(imageView);
        petButton.setToggleGroup(petGroup);
        petButton.setUserData(pet);
        petButton.setStyle("-fx-font-size: 12;");

        return petButton;
    }

    private HBox createLanguageButtons(ToggleGroup languageGroup) {
        HBox languageSelection = new HBox(20);
        languageSelection.setAlignment(Pos.CENTER);

        String[] languages = {"Python", "Java"};
        for (String language : languages) {
            ToggleButton langButton = new ToggleButton(language);
            langButton.setToggleGroup(languageGroup);
            langButton.setUserData(language);

            ImageView imageView = new ImageView(new Image(language.toLowerCase() + ".png"));
            imageView.setFitWidth(40);
            imageView.setFitHeight(40);
            langButton.setGraphic(imageView);

            langButton.setStyle("-fx-font-size: 14;");
            languageSelection.getChildren().add(langButton);
        }

        return languageSelection;
    }

    private void checkSelections(Button startButton, ToggleGroup petGroup, ToggleGroup languageGroup) {
        startButton.setDisable(petGroup.getSelectedToggle() == null || languageGroup.getSelectedToggle() == null);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
