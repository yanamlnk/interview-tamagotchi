package io.tamagotchi.screens;

import io.tamagotchi.TamagotchiException;
import io.tamagotchi.food.Food;
import io.tamagotchi.food.FoodFactory;
import io.tamagotchi.pet.Pet;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
        if (pet.isWinner()) {
            showWinPopup(primaryStage);
        }

        // Root layout divided into left and right
        HBox root = new HBox(20);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #D6EEF2;");

        // Left side
        VBox leftPane = new VBox(20);
        leftPane.setAlignment(Pos.TOP_CENTER);
        leftPane.setPadding(new Insets(20));
        leftPane.setPrefWidth(400);

        Button rulesButton = new Button("Rules");
        rulesButton.setOnAction(event -> showRulesPopup(primaryStage));

        ImageView petImage = new ImageView(new Image(pet.getImageUrl()));
        petImage.setFitWidth(200);
        petImage.setPreserveRatio(true);

        Label petInfo = new Label(pet.getName() + " learns " + language + ".");
        petInfo.setFont(Font.font(20));

        ImageView languageImage = new ImageView(new Image(language.toLowerCase() + ".png"));
        languageImage.setFitWidth(50);
        languageImage.setPreserveRatio(true);

        TranslateTransition animation = new TranslateTransition(Duration.seconds(1), languageImage);
        animation.setByY(10);
        animation.setAutoReverse(true);
        animation.setCycleCount(TranslateTransition.INDEFINITE);
        animation.play();

        Label superPower = new Label(pet.getName() + "'s superpower: " + pet.getDescription());

        // Add elements to left side
        leftPane.getChildren().addAll(rulesButton, petImage, petInfo, languageImage, superPower);

        // Right side
        VBox rightPane = new VBox(20);
        rightPane.setAlignment(Pos.TOP_CENTER);
        rightPane.setPadding(new Insets(20));
        rightPane.setPrefWidth(600);

        ProgressBar healthBar = new ProgressBar(pet.getHealth() / 100.0);
        healthBar.setPrefWidth(300);
        Label healthLabel = new Label("Health: " + (int) pet.getHealth() + "/100");

        if (pet.isDead()) {
            showGameOverPopup(primaryStage);
        }

        ProgressBar xpBar = new ProgressBar(pet.getCurrentXp() / pet.getMaxXpForThisLevel());
        xpBar.setPrefWidth(300);
        Label xpLabel = new Label("XP: " + (int) pet.getCurrentXp() + "/" + (int) pet.getMaxXpForThisLevel());

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
        playButton.setOnAction(event -> new QuizScreen(pet, language, "play").start(primaryStage));

        HBox actionButtons = new HBox(10, feedButton, playButton);
        actionButtons.setAlignment(Pos.CENTER);

        // Go to Work Button
        Button workButton = new Button("Go to Work");
        workButton.setOnAction(event -> new QuizScreen(pet, language, "work").start(primaryStage));

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

        Text rules = new Text("""
                This is an interview preparation game.
                You need to keep your pet healthy by feeding it. If your pet looses all health - the game is over.
                You can earn money by going to work and use it to buy food for your pet.
                You can also play with your pet to increase its XP.
                The goal of the game is to reach level 10 of your pet.
                After each day of work and each play session your pet will loose some health (= gets hungrier).
                Also, you can't play games if your pet is too hungry.
                Good luck!""");
        rules.setFont(Font.font(18));
        rules.setWrappingWidth(350);

        Button closeButton = new Button("Return to Game");
        closeButton.setOnAction(e -> popup.close());

        layout.getChildren().addAll(rules, closeButton);

        Scene popupScene = new Scene(layout, 500, 400);
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

        // Create buttons for each type of food
        Button burgerButton = createFoodButton("burger");
        Button fishButton = createFoodButton("fish");
        Button potatoButton = createFoodButton("potato");

        HBox foods = new HBox(10, burgerButton, fishButton, potatoButton);

        // Close button
        Button closeButton = new Button("Close Menu");
        closeButton.setOnAction(e -> popup.close());

        // Add all buttons to the layout
        layout.getChildren().addAll(feedLabel, foods, closeButton);

        Scene popupScene = new Scene(layout, 500, 600);
        popup.setScene(popupScene);
        popup.showAndWait();
    }

    private Button createFoodButton(String foodName) {
        try {
            Food food = FoodFactory.create(foodName);

            VBox buttonContent = new VBox(5);
            buttonContent.setAlignment(Pos.CENTER);

            // Image
            ImageView foodImage = new ImageView(new Image(food.getImageUrl()));
            foodImage.setFitWidth(100);
            foodImage.setPreserveRatio(true);

            TranslateTransition animation = new TranslateTransition(Duration.seconds(1), foodImage);
            animation.setByY(10);
            animation.setAutoReverse(true);
            animation.setCycleCount(TranslateTransition.INDEFINITE);
            animation.play();

            // Food details
            Label nameLabel = new Label(foodName.toUpperCase());
            nameLabel.setFont(Font.font(16));

            Label detailsLabel = new Label("Price: " + food.getPrice() + " | HP: " + food.getHealth());

            // Button
            Button foodButton = new Button();
            foodButton.setGraphic(buttonContent);

            // Add elements to the button content
            buttonContent.getChildren().addAll(foodImage, nameLabel, detailsLabel);

            // Button action
            foodButton.setOnAction(e -> {
                try {
                    pet.spendMoney(food.getPrice());
                    pet.eat(food);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("You fed your pet with " + foodName + "!");
                    alert.showAndWait();
                } catch (TamagotchiException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("You don't have enough money, go to work!");
                    alert.showAndWait();
                }
            });

            return foodButton;

        } catch (TamagotchiException e) {
            // Handle unknown food exception gracefully
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to create food: " + e.getMessage());
            alert.showAndWait();
            return new Button("Error Loading Food");
        }
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

        Scene popupScene = new Scene(layout, 500, 400);
        popup.setScene(popupScene);
        popup.showAndWait();
    }

    private void showWinPopup(Stage owner) {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.initOwner(owner);

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #FFFFFF;");

        Label winLabel = new Label("Congratulations!\nYou Win!");
        winLabel.setFont(Font.font(18));
        winLabel.setWrapText(true);
        winLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> {
            popup.close();
            owner.close();
        });

        layout.getChildren().addAll(winLabel, exitButton);

        Scene popupScene = new Scene(layout, 500, 400);
        popup.setScene(popupScene);
        popup.showAndWait();
    }
}
