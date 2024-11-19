package io.tamagotchi.screens;

import java.net.URL;

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
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainScreen extends Application {
    private MediaPlayer backgroundMusic;
    private final Pet pet;
    private final String language;
    private HBox root;

    Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/upheavtt.ttf"), 20);

    public MainScreen(Pet pet, String language) {
        this.pet = pet;
        this.language = language;
    }

    private void playBackgroundMusic() {
        try {
            URL musicURL = getClass().getResource("/music/Music.mp3");
            if (musicURL == null) {
                System.out.println("Music file not found!");
                return;
            }
            String musicFile = musicURL.toExternalForm();
            System.out.println("Music file loaded from: " + musicFile); // Debugging output
            
            Media sound = new Media(musicFile);
            backgroundMusic = new MediaPlayer(sound);
            backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
            backgroundMusic.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        playBackgroundMusic();
        if (pet.isWinner()) {
            showWinPopup(primaryStage);
        }

        //Top Bar
        HBox navBar = new HBox(10);
        navBar.setAlignment(Pos.CENTER);
        navBar.setMinWidth(600);
        navBar.setPadding(new Insets(10));
        navBar.setStyle("-fx-background-color: #D6EEF2;");

        Button rulesButton = new Button("Rules");
        rulesButton.setFont(font);
        rulesButton.setStyle("-fx-background-color: #4CAF50; "
                    + "-fx-text-fill: white; "
                    + "-fx-padding: 10px 20px; "
                    + "-fx-background-radius: 5px; "
                    + "-fx-border-color: #efbf04; "
                    + "-fx-border-width: 2px; " 
                    + "-fx-border-radius: 5px;");
        rulesButton.setOnAction(event -> showRulesPopup(primaryStage));

        Button customizeButton = new Button("Customize");
        customizeButton.setFont(font);
        customizeButton.setStyle("-fx-background-color: #FFC0CB;" 
                    +"-fx-text-fill: white; " 
                    +"-fx-padding: 10px 20px; " 
                    +"-fx-background-radius: 5px; " 
                    +"-fx-border-color: #efbf04; " 
                    +"-fx-border-width: 2px; " 
                    +"-fx-border-radius: 5px;");
        customizeButton.setOnAction(event -> showCustomizePopup(primaryStage));

        navBar.getChildren().addAll(rulesButton, customizeButton);

        // Root layout divided into left and right
        root = new HBox(1);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #D6EEF2;");
        root.setAlignment(Pos.CENTER); 
        HBox.setHgrow(root, Priority.ALWAYS);

        // Left side
        VBox leftPane = new VBox(10);
        leftPane.setAlignment(Pos.CENTER);
        leftPane.setPadding(new Insets(10));
        leftPane.setMinWidth(300); 
        leftPane.setMinHeight(700); 

        ImageView petImage = new ImageView(new Image(pet.getImageUrl()));
        petImage.setFitWidth(300);
        petImage.setPreserveRatio(true);

        //Speech bubble
        VBox speechBubble = createSpeechBubble("Permission granted to execute your potential! Ready to learn?");

        StackPane petWithSpeechBubble = new StackPane();
petWithSpeechBubble.getChildren().addAll(petImage,speechBubble);
        StackPane.setAlignment(speechBubble, Pos.TOP_CENTER);
        StackPane.setMargin(speechBubble, new Insets(-100, 50, 0, 50));
        
        Label petInfo = new Label("Name:  "+ pet.getName());
        petInfo.setFont(font);

        // Superpower info
        Label superPower = new Label("Superpower:  " + pet.getDescription());
        superPower.setFont(font);

        Label languageText = new Label("Let's review " + language + "!");
        languageText.setFont(font);
        ImageView languageImage = new ImageView(new Image(language.toLowerCase() + ".png"));
        languageImage.setFitWidth(50);
        languageImage.setPreserveRatio(true);

        TranslateTransition animation = new TranslateTransition(Duration.seconds(1), languageImage);
        animation.setByY(10);
        animation.setAutoReverse(true);
        animation.setCycleCount(TranslateTransition.INDEFINITE);
        animation.play();

        // Add elements to left side
leftPane.getChildren().addAll(petWithSpeechBubble,petInfo, superPower,languageText,languageImage);

        // Right side
        VBox rightPane = new VBox(20);
        rightPane.setAlignment(Pos.CENTER);
        rightPane.setPadding(new Insets(20));
        rightPane.setMinWidth(300); 

        ProgressBar healthBar = new ProgressBar(pet.getHealth() / 100.0);
        healthBar.setPrefWidth(300);
        Label healthLabel = new Label("Health: " + (int) pet.getHealth() + "/100");
        healthLabel.setFont(font);

        if (pet.isDead()) {
            showGameOverPopup(primaryStage);
        }

        ProgressBar xpBar = new ProgressBar(pet.getCurrentXp() / pet.getMaxXpForThisLevel());
        xpBar.setPrefWidth(300);
        Label xpLabel = new Label("XP: " + (int) pet.getCurrentXp() + "/" + (int) pet.getMaxXpForThisLevel());
        xpLabel.setFont(font);

        // Level
        Label levelLabel = new Label("Level: " + pet.getLevel());
        levelLabel.setFont(font);

        // Money
        Label moneyLabel = new Label("Money: " + (int) pet.getMoney());
        moneyLabel.setFont(font);

        ImageView coinImage = new ImageView(new Image("coin.png"));
        coinImage.setFitWidth(20);
        coinImage.setPreserveRatio(true);

        HBox moneyRow = new HBox(5, moneyLabel, coinImage);
        moneyRow.setAlignment(Pos.CENTER);

        // Buttons: Feed and Play
        Button feedButton = new Button("Feed");
        feedButton.setFont(font);
        feedButton.setStyle("-fx-background-color: #4CAF50; "
                    + "-fx-text-fill: white; "
                    + "-fx-padding: 10px 20px; "
                    + "-fx-background-radius: 5px; "
                    + "-fx-border-color: #efbf04; "
                    + "-fx-border-width: 2px; " 
                    + "-fx-border-radius: 5px;");

        feedButton.setOnAction(event -> showFeedPopup(primaryStage));

        Button playButton = new Button("Play");
        playButton.setFont(font);
        playButton.setStyle("-fx-background-color: #89CFF0; "
                    + "-fx-text-fill: white; "
                    + "-fx-padding: 10px 20px; "
                    + "-fx-background-radius: 5px; "
                    + "-fx-border-color: #efbf04; "
                    + "-fx-border-width: 2px; " 
                    + "-fx-border-radius: 5px;");
        playButton.setOnAction(event -> new QuizScreen(pet, language, "play").start(primaryStage));

        HBox actionButtons = new HBox(10, feedButton, playButton);
        actionButtons.setAlignment(Pos.CENTER);

        // Go to Work Button
        Button workButton = new Button("Go to Work");
        workButton.setFont(font);
        workButton.setStyle("-fx-background-color: #89CFF0; "
                    + "-fx-text-fill: white; "
                    + "-fx-padding: 10px 20px; "
                    + "-fx-background-radius: 5px; "
                    + "-fx-border-color: #efbf04; "
                    + "-fx-border-width: 2px; " 
                    + "-fx-border-radius: 5px;");
        workButton.setOnAction(event -> new QuizScreen(pet, language, "work").start(primaryStage));

        // Add elements to right pane
        rightPane.getChildren().addAll(healthLabel, healthBar, xpLabel, xpBar, levelLabel, moneyRow, actionButtons, workButton);

        // Add panes to root layout
        root.getChildren().addAll(leftPane, rightPane);

        VBox mainLayout = new VBox();
    mainLayout.setStyle("-fx-background-color: #D6EEF2;");
    mainLayout.getChildren().addAll(navBar, root);

        // Show the scene
        Scene scene = new Scene(mainLayout,1000,800);
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
        layout.setStyle("-fx-background-color: #89CFF0; "
        + "-fx-text-fill: white; "
        + "-fx-padding: 10px 20px; "
        + "-fx-background-radius: 5px; "
        + "-fx-border-color: #efbf04; "
        + "-fx-border-width: 2px; " 
        + "-fx-border-radius: 5px;");

        Text rules = new Text("""
            This is an interview preparation game. \nKeep your pet healthy by feeding it. \nIf it loses all health, the game ends. \nEarn money by working to buy food, and play with your pet to gain XP. The goal is to reach level 10.\nWorking or playing makes your pet hungrier, and you can't play if your pet is too hungry. Goodluck!""");

        rules.setFont(font);
        rules.setWrappingWidth(350);

        Button closeButton = new Button("Return to Game");
        closeButton.setFont(font);
        closeButton.setStyle("-fx-background-color: #4CAF50; "
        + "-fx-text-fill: white; "
        + "-fx-padding: 10px 20px; "
        + "-fx-background-radius: 5px; "
        + "-fx-border-color: #efbf04; "
        + "-fx-border-width: 2px; " 
        + "-fx-border-radius: 5px;");
        closeButton.setOnAction(e -> popup.close());

        layout.getChildren().addAll(rules, closeButton);

        Scene popupScene = new Scene(layout, 500, 400);
        popup.setScene(popupScene);
        popup.showAndWait();
    }

    private void showCustomizePopup(Stage owner) {
    Stage popup = new Stage();
    popup.initModality(Modality.APPLICATION_MODAL);
    popup.initOwner(owner);

    VBox layout = new VBox(20);
    layout.setAlignment(Pos.TOP_LEFT);
    layout.setPadding(new Insets(20));
    layout.setStyle("-fx-background-color: #FFC0CB;");

    // Background color selection
    Text backgroundText = new Text("Choose your background:");
    backgroundText.setFont(font);

    HBox colorCircles = new HBox(20);
    colorCircles.setAlignment(Pos.CENTER);

    String[] colors = {"#D6EEF2", "#FFC0CB", "#89CFF0"};
    for (String color : colors) {
        Circle circle = new Circle(30);
        circle.setFill(Color.web(color));
        circle.setStroke(Color.BLACK);
        circle.setOnMouseClicked(e -> {
            root.setStyle("-fx-background-color: " + color + ";");
            popup.close();
        });
        colorCircles.getChildren().add(circle);
    }

    // Volume control
    Text volumeText = new Text("Sound:");
    volumeText.setFont(font);

    Slider volumeSlider = new Slider(0, 100, 50);
    volumeSlider.setShowTickLabels(true);
    volumeSlider.setShowTickMarks(true);
    volumeSlider.setMajorTickUnit(50);
    volumeSlider.setMinorTickCount(5);

    if (backgroundMusic != null) {
        volumeSlider.setValue(backgroundMusic.getVolume() * 100);
    }

    volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
        if (backgroundMusic != null) {
            backgroundMusic.setVolume(newValue.doubleValue() / 100);
        }
    });

    // Brightness control
    Text brightnessText = new Text("Brightness:");
    brightnessText.setFont(font);

    Slider brightnessSlider = new Slider(0.5, 1.0, 1.0);
    brightnessSlider.setShowTickLabels(true);
    brightnessSlider.setShowTickMarks(true);
    brightnessSlider.setMajorTickUnit(0.25);
    brightnessSlider.setMinorTickCount(5);

    brightnessSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
        root.setOpacity(newValue.doubleValue());
    });

    Button closeButton = new Button("Close");
    closeButton.setFont(font);
    closeButton.setStyle("-fx-background-color: #4CAF50; " +
                         "-fx-text-fill: white; " +
                         "-fx-padding: 10px 20px; " +
                         "-fx-background-radius: 5px; " +
                         "-fx-border-color: #efbf04; " +
                         "-fx-border-width: 2px; " +
                         "-fx-border-radius: 5px;");
    closeButton.setOnAction(e -> popup.close());

    layout.getChildren().addAll(
        backgroundText, colorCircles, closeButton,volumeText, volumeSlider, brightnessText, brightnessSlider
    );

    Scene popupScene = new Scene(layout, 500, 400);
    popup.setScene(popupScene);
    popup.showAndWait();
}

    private VBox createSpeechBubble(String text) {
        // Create the text first to determine its size
        Text bubbleText = new Text(text);
        bubbleText.setFont(font);
        bubbleText.setTextAlignment(TextAlignment.CENTER);
        bubbleText.setWrappingWidth(200);
    
        // Calculate the bubble size based on the text
        double textWidth = bubbleText.getLayoutBounds().getWidth();
        double textHeight = bubbleText.getLayoutBounds().getHeight();
        double bubbleWidth = Math.max(200, textWidth + 20);
        double bubbleHeight = textHeight + 20;
    
        // Create the bubble shape
        javafx.scene.shape.Rectangle bubble = new javafx.scene.shape.Rectangle(bubbleWidth, bubbleHeight);        bubble.setFill(javafx.scene.paint.Color.web("#eff7fa"));
        bubble.setStroke(javafx.scene.paint.Color.BLACK);
        bubble.setStrokeWidth(2);
        bubble.setArcWidth(5);
        bubble.setArcHeight(5);
        bubble.getStrokeDashArray().addAll(10.0, 5.0);
    
        // Combine bubble, pointer, and text
        StackPane stackPane = new StackPane(bubble, bubbleText);
    
        VBox speechBubble = new VBox(stackPane);
        speechBubble.setAlignment(Pos.TOP_RIGHT);
        speechBubble.setPadding(new Insets(0, 0, 100, 0));
    
        return speechBubble;
    }

    private void showFeedPopup(Stage owner) {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.initOwner(owner);

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #D6EEF2;");


        Label feedLabel = new Label("Feed your pet");
        feedLabel.setFont(font);

        // Create buttons for each type of food
        Button burgerButton = createFoodButton("burger");
        Button fishButton = createFoodButton("fish");
        Button potatoButton = createFoodButton("potato");

        HBox foods = new HBox(10, burgerButton, fishButton, potatoButton);
        foods.setAlignment(Pos.CENTER);

        // Close button
        Button closeButton = new Button("Close Menu");
        closeButton.setFont(font);
        closeButton.setStyle("-fx-background-color: #4CAF50; "
        + "-fx-text-fill: white; "
        + "-fx-padding: 10px 20px; "
        + "-fx-background-radius: 5px; "
        + "-fx-border-color: #efbf04; "
        + "-fx-border-width: 2px;");
        closeButton.setOnAction(e -> popup.close());

        // Add all buttons to the layout
        layout.getChildren().addAll(feedLabel, foods, closeButton);

        Scene popupScene = new Scene(layout, 700, 600);
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
            detailsLabel.setFont(font);

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
        gameOverLabel.setFont(font);
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
        winLabel.setFont(font);
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
