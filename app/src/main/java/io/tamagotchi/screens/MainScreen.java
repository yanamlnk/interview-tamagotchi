package io.tamagotchi.screens;

import io.tamagotchi.pet.Pet;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainScreen extends Application {
    private final Pet pet;
    private final String language;

    public MainScreen(Pet pet, String language) {
        this.pet = pet;
        this.language = language;
    }

    @Override
    public void start(Stage primaryStage) {
        // Root layout divided into left and right
        HBox root = new HBox(20);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #D6EEF2;");

        // Left Pane
        VBox leftPane = new VBox(20);
        leftPane.setAlignment(Pos.TOP_CENTER);
        leftPane.setPadding(new Insets(20));
        leftPane.setPrefWidth(400);

        // Rules Button
        Button rulesButton = new Button("Rules");
        rulesButton.setOnAction(event -> showRulesPopup(primaryStage));

        // Pet Image
        ImageView petImage = new ImageView(new Image(pet.getImageUrl()));
        petImage.setFitWidth(200);
        petImage.setPreserveRatio(true);

        // Text: [Pet.getName()] learns [language]
        Label petInfo = new Label(pet.getName() + " learns " + language);
        petInfo.setFont(Font.font(20));
        petInfo.setStyle("-fx-font-weight: bold;");

        // Language Icon with Animation
        ImageView languageImage = new ImageView(new Image(language.toLowerCase() + ".png"));
        languageImage.setFitWidth(50);
        languageImage.setPreserveRatio(true);

        TranslateTransition animation = new TranslateTransition(Duration.seconds(1), languageImage);
        animation.setByY(10);
        animation.setAutoReverse(true);
        animation.setCycleCount(TranslateTransition.INDEFINITE);
        animation.play();

        // Add elements to left pane
        leftPane.getChildren().addAll(rulesButton, petImage, petInfo, languageImage);

        // Right Pane
        VBox rightPane = new VBox(20);
        rightPane.setAlignment(Pos.TOP_CENTER);
        rightPane.setPadding(new Insets(20));
        rightPane.setPrefWidth(600);

        // Health Bar
        ProgressBar healthBar = new ProgressBar(pet.getHealth() / 100.0);
        healthBar.setPrefWidth(300);
        Label healthLabel = new Label("Health: " + (int) pet.getHealth() + "/100");

        // Check for Game Over
        if (pet.getHealth() == 0) {
            showGameOverPopup(primaryStage);
        }

        // XP Bar
        ProgressBar xpBar = new ProgressBar(pet.getXp() / 670.0);
        xpBar.setPrefWidth(300);
        Label xpLabel = new Label("XP: " + (int) pet.getXp());

        // Level
        Label levelLabel = new Label("Level: " + pet.getLevel());
        levelLabel.setFont(Font.font(18));

        // Money
        Label moneyLabel = new Label("Money: " + (int) pet.getMoney());
        moneyLabel.setFont(Font.font(18));

        ImageView coinImage = new ImageView(new Image("coin.png"));
        coinImage.setFitWidth(20);
        coinImage.setPreserveRatio(true);

        HBox moneyRow = new HBox(5, moneyLabel, coinImage);
        moneyRow.setAlignment(Pos.CENTER);

        // Buttons: Feed and Play
        Button feedButton = new Button("Feed");
        feedButton.setOnAction(event -> showFeedPopup(primaryStage));

        Button playButton = new Button("Play");
//        playButton.setOnAction(event -> new PlayScreen(pet, language).start(primaryStage));

        HBox actionButtons = new HBox(10, feedButton, playButton);
        actionButtons.setAlignment(Pos.CENTER);

        // Go to Work Button
        Button workButton = new Button("Go to Work");
//        workButton.setOnAction(event -> new WorkScreen(pet, language).start(primaryStage));

        // Add elements to right pane
        rightPane.getChildren().addAll(healthLabel, healthBar, xpLabel, xpBar, levelLabel, moneyRow, actionButtons, workButton);

        // Add panes to root layout
        root.getChildren().addAll(leftPane, rightPane);

        // Show the scene
        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("PrepPal");
        primaryStage.show();
    }

    private void showRulesPopup(Stage owner) {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.initOwner(owner);

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #FFFFFF;");

        Label rulesLabel = new Label("Rules");
        rulesLabel.setFont(Font.font(18));

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> popup.close());

        layout.getChildren().addAll(rulesLabel, closeButton);

        Scene popupScene = new Scene(layout, 300, 200);
        popup.setScene(popupScene);
        popup.showAndWait();
    }

    private void showFeedPopup(Stage owner) {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.initOwner(owner);

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #FFFFFF;");

        Label feedLabel = new Label("Feed your pet");
        feedLabel.setFont(Font.font(18));

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> popup.close());

        layout.getChildren().addAll(feedLabel, closeButton);

        Scene popupScene = new Scene(layout, 300, 200);
        popup.setScene(popupScene);
        popup.showAndWait();
    }

    private void showGameOverPopup(Stage owner) {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.initOwner(owner);

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #FFFFFF;");

        Label gameOverLabel = new Label("Game Over\nPlease start over and next time don't forget to feed your pet!");
        gameOverLabel.setFont(Font.font(18));
        gameOverLabel.setWrapText(true);
        gameOverLabel.setTextAlignment(TextAlignment.CENTER);

        Button startOverButton = new Button("Start Over");
        startOverButton.setOnAction(e -> {
            new MenuScreen().start(owner);
            popup.close();
        });

        layout.getChildren().addAll(gameOverLabel, startOverButton);

        Scene popupScene = new Scene(layout, 400, 200);
        popup.setScene(popupScene);
        popup.showAndWait();
    }
}
