package io.tamagotchi.screens;

import io.tamagotchi.TamagotchiException;
import io.tamagotchi.pet.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MenuScreen extends Application {
    private Pet selectedPet = null;
    private String selectedLanguage = null;
    private Font customFont;

    @Override
    public void start(Stage primaryStage) {
        // Main layout
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        customFont = Font.loadFont(Objects.requireNonNull(getClass().getResource("/fonts/upheavtt.ttf")).toExternalForm(), 20);
        root.setStyle("-fx-background-color: #D6EEF2;");

        // Title
        Text title = new Text("WELCOME !\nPlease choose your tamagotchi pet and language that you need to prepare for interview:");
        title.setStyle("-fx-font-size: 30;");
        title.setFont(customFont);
        title.setWrappingWidth(600); // Limit the text width to 300px
        title.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        // Pet selection buttons
        ToggleGroup petGroup = new ToggleGroup();
        HBox petSelection = createPetButtons(petGroup);

        // Language selection buttons
        ToggleGroup languageGroup = new ToggleGroup();
        HBox languageSelection = createLanguageButtons(languageGroup);

        // Start button
        Button startButton = new Button("Start".toUpperCase());
        startButton.setStyle("-fx-font-family: '" + customFont.getFamily() + "';-fx-font-size: 14;");
        startButton.setFont(customFont);
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
        Scene scene = new Scene(root, 800, 600);
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
            e.printStackTrace();
        }

        for (Pet pet : pets) {
            ToggleButton petButton = createPetButton(pet, petGroup);
            petSelection.getChildren().add(petButton);
        }

        return petSelection;
    }

    private ToggleButton createPetButton(Pet pet, ToggleGroup petGroup) {
        ImageView imageView = new ImageView(new Image(pet.getImageUrl()));
        imageView.setFitWidth(180);
        imageView.setFitHeight(180);

        Text name = new Text(pet.getName().toUpperCase() + "\n");
        name.setFont(customFont);
        name.setStyle("-fx-font-family: '"+ customFont.getFamily() +"'-fx-font-size: 20;");

        Text description = new Text(pet.getDescription());
        description.setWrappingWidth(180);
        description.setFont(customFont);
        description.setStyle("-fx-font-family: '"+ customFont.getFamily() +"'-fx-font-size: 14; -fx-text-alignment: center;");

        VBox combinedText = new VBox(name, description);
        combinedText.setAlignment(Pos.CENTER);

        ToggleButton petButton = new ToggleButton();
        petButton.setGraphic(new VBox(imageView, combinedText));
        petButton.setContentDisplay(ContentDisplay.TOP);

        petButton.setToggleGroup(petGroup);
        petButton.setUserData(pet);


        petButton.setStyle("-fx-font-family: '" + customFont.getFamily() + "'; -fx-font-size: 12;");
        petButton.setFont(customFont);

        petButton.setStyle(
                "-fx-background-color: transparent; " +
                        "-fx-border-color: black; " +
                        "-fx-border-width: 2px;"
        );


        petButton.hoverProperty().addListener((obs, wasHovered, isHovered) -> {
            if (isHovered && !petButton.isSelected()) {
                petButton.setStyle("-fx-background-color: #C2ECF4; " +
                        "-fx-border-color: black; " +
                        "-fx-border-width: 2px;");
            } else {
                petButton.setStyle(petButton.isSelected() ? "-fx-background-color: #94CED8; " +
                        "-fx-border-color: black; " +
                        "-fx-border-width: 2px;"
                        :
                        "-fx-background-color: transparent; " +
                        "-fx-border-color: black; " +
                        "-fx-border-width: 2px;");
            }
        });

        petButton.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            if (isSelected) {
                petButton.setStyle(
                        "-fx-background-color: #94CED8; " +
                                "-fx-border-color: black; " +
                                "-fx-border-width: 2px;"
                );
            } else {
                petButton.setStyle(
                        "-fx-background-color: transparent; " +
                                "-fx-border-color: black; " +
                                "-fx-border-width: 2px;"
                );
            }
        });

        return petButton;
    }

    private HBox createLanguageButtons(ToggleGroup languageGroup) {
        HBox languageSelection = new HBox(20);
        languageSelection.setAlignment(Pos.CENTER);

        String[] languages = {"Python", "Java"};
        for (String language : languages) {
            ToggleButton langButton = new ToggleButton(language.toUpperCase());
            langButton.setToggleGroup(languageGroup);
            langButton.setUserData(language);

            ImageView imageView = new ImageView(new Image(language.toLowerCase() + ".png"));
            imageView.setFitWidth(40);
            imageView.setFitHeight(40);
            langButton.setGraphic(imageView);

            langButton.setStyle("-fx-font-family: '" + customFont.getFamily() + "';-fx-font-size: 14;");
            langButton.setFont(customFont);
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
