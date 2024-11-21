package io.tamagotchi.screens;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.tamagotchi.TamagotchiException;
import io.tamagotchi.pet.Pet;
import io.tamagotchi.pet.PetFactory;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Represents the menu screen in the Tamagotchi game.
 * Allows the user to select a pet and a language for the game.
 */
public class MenuScreen extends Application {

    /**
     * The background music player.
     */
    private MediaPlayer backgroundMusic;

    /**
     * The selected pet.
     */
    private Pet selectedPet = null;

    /**
     * The selected language.
     */
    private String selectedLanguage = null;

    /**
     * The custom font used in the menu screen.
     */
    private Font customFont;

    /**
     * The style for buttons in their default state.
     */
    private String buttonStyle = "-fx-background-color: transparent; " +
                                    "-fx-border-color: black; " +
                                    "-fx-border-width: 2px;";

    /**
     * The style for buttons when hovered over.
     */
    private String buttonStyleHover = "-fx-background-color: #C2ECF4; " +
                                        "-fx-border-color: black; " +
                                        "-fx-border-width: 2px;";

    /**
     * The style for buttons when selected.
     */
    private String buttonStyleSelected = "-fx-background-color: #94CED8; " +
                                        "-fx-border-color: black; " +
                                        "-fx-border-width: 2px;";

    /**
     * The style for the start button when active.
     */
    private String startButtonStyleActive = "-fx-background-color: #A0DF95; " +
                                            "-fx-border-color: black; " +
                                            "-fx-border-width: 2px;";

    /**
     * The style for the start button when hovered over.
     */
    private String startButtonStyleHover = "-fx-background-color: #8AC380; " +
                                            "-fx-border-color: black; " +
                                            "-fx-border-width: 2px;";

    /**
     * Starts the application and sets up the menu screen.
     *
     * @param primaryStage the primary stage for this application
     */
    @Override
    public void start(Stage primaryStage) {
        playBackgroundMusic();
        // Main layout
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        customFont = Font.loadFont(Objects.requireNonNull(getClass().getResource("/fonts/upheavtt.ttf")).toExternalForm(), 20);
        root.setStyle("-fx-background-color: #D6EEF2;");

        // Title
        Text title = new Text("WELCOME to PrepPal - tamagotchi that will help you to prepare for technical interview!\n\nPlease choose your tamagotchi pet:");
        title.setStyle("-fx-font-size: 30;");
        title.setFont(customFont);
        title.setWrappingWidth(800); // Limit the text width to 300px
        title.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);


        // Pet selection buttons
        ToggleGroup petGroup = new ToggleGroup();
        HBox petSelection = createPetButtons(petGroup);

        Text petTitle = new Text("Please select the language that you need to prepare for interview:");
        petTitle.setStyle("-fx-font-size: 30;");
        petTitle.setFont(customFont);
        petTitle.setWrappingWidth(800); // Limit the text width to 300px
        petTitle.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        // Language selection buttons
        ToggleGroup languageGroup = new ToggleGroup();
        HBox languageSelection = createLanguageButtons(languageGroup);

        // Start button
        Button startButton = new Button("Start".toUpperCase());
        startButton.setStyle("-fx-font-family: '" + customFont.getFamily() + "';-fx-font-size: 30;");
        startButton.setFont(customFont);
        startButton.setDisable(true);
        startButton.setStyle(buttonStyle);

        // Enable the start button only if both selections are made
        petGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                petGroup.selectToggle(oldValue);  // Revert back if trying to unselect
            } else {
                selectedPet = (Pet) ((ToggleButton) newValue).getUserData();
            }
            checkSelections(startButton, petGroup, languageGroup);
        });

        languageGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                languageGroup.selectToggle(oldValue);  // Revert back if trying to unselect
            } else {
                selectedLanguage = (String) ((ToggleButton) newValue).getUserData();
            }
            checkSelections(startButton, petGroup, languageGroup);
        });

        startButton.hoverProperty().addListener((obs, wasHovered, isHovered) -> {
            if (isHovered && !startButton.isDisabled()) {
                startButton.setStyle(startButtonStyleHover);
            } else if (!startButton.isDisabled()) {
                startButton.setStyle(startButtonStyleActive);
            } else {
                startButton.setStyle(buttonStyle);
            }
        });

        startButton.setOnAction(event -> {
            // Pass selectedPet and selectedLanguage to MainScreen
            MainScreen mainScreen = new MainScreen(selectedPet, selectedLanguage, backgroundMusic);
            try {
                mainScreen.start(primaryStage); // Open MainScreen
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Add everything to the root layout
        root.getChildren().addAll(title, petSelection, petTitle, languageSelection, startButton);

        // Show the stage
        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("PrepPal: Choose Your Pet and Language");
        primaryStage.show();
    }

    /**
     * Creates the pet selection buttons.
     *
     * @param petGroup the toggle group for the pet buttons
     * @return an HBox containing the pet selection buttons
     */
    private HBox createPetButtons(ToggleGroup petGroup) {
        HBox petSelection = new HBox(20);
        petSelection.setAlignment(Pos.CENTER);

        List<String> pets = new ArrayList<>();
        pets.add("gradle");
        pets.add("docker");
        pets.add("linux");


        for (String pet : pets) {
            ToggleButton petButton = createPetButton(pet, petGroup);
            petSelection.getChildren().add(petButton);
        }

        return petSelection;
    }

    /**
     * Creates a pet selection button.
     *
     * @param tamagotchi the name of the tamagotchi pet
     * @param petGroup the toggle group for the pet buttons
     * @return a ToggleButton for the specified pet
     */
    private ToggleButton createPetButton(String tamagotchi, ToggleGroup petGroup) {
        try {
            Pet pet = PetFactory.create(tamagotchi);
            ImageView imageView = new ImageView(new Image(pet.getImageUrl()));
            imageView.setFitWidth(180);
            imageView.setFitHeight(180);

            Text name = new Text(pet.getName().toUpperCase() + "\n");
            name.setFont(customFont);
            name.setStyle("-fx-font-family: '"+ customFont.getFamily() +"'; -fx-font-size: 25;");

            Text description = new Text(pet.getDescription());
            description.setWrappingWidth(180);
            description.setFont(customFont);
            description.setStyle("-fx-font-family: '"+ customFont.getFamily() +"'; -fx-font-size: 18; -fx-text-alignment: center;");

            VBox combinedText = new VBox(name, description);
            combinedText.setAlignment(Pos.CENTER);

            ToggleButton petButton = new ToggleButton();
            petButton.setGraphic(new VBox(imageView, combinedText));
            petButton.setContentDisplay(ContentDisplay.TOP);

            petButton.setToggleGroup(petGroup);
            petButton.setUserData(pet);


            petButton.setStyle("-fx-font-family: '" + customFont.getFamily() + "'; -fx-font-size: 12;");
            petButton.setFont(customFont);

            petButton.setStyle(buttonStyle);

            petButton.setId(tamagotchi);


            petButton.hoverProperty().addListener((obs, wasHovered, isHovered) -> {
                if (isHovered && !petButton.isSelected()) {
                    petButton.setStyle(buttonStyleHover);
                } else {
                    petButton.setStyle(petButton.isSelected() ? buttonStyleSelected : buttonStyle);
                }
            });

            petButton.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
                if (isSelected) {
                    petButton.setStyle(buttonStyleSelected);
                } else {
                    petButton.setStyle(buttonStyle);
                }
            });

            return petButton;
        } catch (TamagotchiException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Creates the language selection buttons.
     *
     * @param languageGroup the toggle group for the language buttons
     * @return an HBox containing the language selection buttons
     */
    private HBox createLanguageButtons(ToggleGroup languageGroup) {
        HBox languageSelection = new HBox(20);
        languageSelection.setAlignment(Pos.CENTER);

        String[] languages = {"Python", "Java"};
        for (String language : languages) {
            ToggleButton langButton = new ToggleButton(language.toUpperCase());
            langButton.setToggleGroup(languageGroup);
            langButton.setUserData(language);

            ImageView imageView = new ImageView(new Image(language.toLowerCase() + ".png"));
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            langButton.setGraphic(imageView);
            langButton.setContentDisplay(ContentDisplay.TOP);

            langButton.setStyle("-fx-font-family: '" + customFont.getFamily() + "';-fx-font-size: 14;");
            langButton.setFont(customFont);

            langButton.setStyle(buttonStyle);

            langButton.setId(language.toLowerCase());


            langButton.hoverProperty().addListener((obs, wasHovered, isHovered) -> {
                if (isHovered && !langButton.isSelected()) {
                    langButton.setStyle(buttonStyleHover);
                } else {
                    langButton.setStyle(langButton.isSelected() ? buttonStyleSelected : buttonStyle);
                }
            });

            langButton.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
                if (isSelected) {
                    langButton.setStyle(buttonStyleSelected);
                } else {
                    langButton.setStyle(buttonStyle);
                }
            });

            languageSelection.getChildren().add(langButton);
        }

        return languageSelection;
    }

    /**
     * Checks if both pet and language selections are made and enables the start button if true.
     *
     * @param startButton the start button
     * @param petGroup the toggle group for the pet buttons
     * @param languageGroup the toggle group for the language buttons
     */
    private void checkSelections(Button startButton, ToggleGroup petGroup, ToggleGroup languageGroup) {
        boolean isPetSelected = petGroup.getSelectedToggle() != null;
        boolean isLanguageSelected = languageGroup.getSelectedToggle() != null;

        if (isPetSelected && isLanguageSelected) {
            startButton.setDisable(false);
            startButton.setStyle(startButtonStyleActive);
        } else {
            startButton.setDisable(true);
            startButton.setStyle(buttonStyle);
        }
    }

    /**
     * Plays the background music for the main screen.
     */
    void playBackgroundMusic() {
        try {
            URL musicURL = getClass().getResource("/music/Music.mp3");
            if (musicURL == null) {
                System.out.println("Music file not found!");
                return;
            }
            String musicFile = musicURL.toExternalForm();

            Media sound = new Media(musicFile);
            backgroundMusic = new MediaPlayer(sound);
            backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
            backgroundMusic.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The main method to launch the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
