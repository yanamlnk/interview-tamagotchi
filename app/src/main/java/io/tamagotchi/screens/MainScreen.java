package io.tamagotchi.screens;

import java.net.URL;

import io.tamagotchi.TamagotchiException;
import io.tamagotchi.food.Food;
import io.tamagotchi.food.FoodFactory;
import io.tamagotchi.pet.Pet;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
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
    private HBox navBar;

    Font font =     
Font.loadFont(getClass().getResourceAsStream("/fonts/upheavtt.ttf"), 20);

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

        primaryStage.getIcons().add(new Image("logo.gif"));

        //Top Bar
        navBar = new HBox(10);
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
                    + "-fx-border-radius: 5px;"
                    +"-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 0);");
        rulesButton.setOnMousePressed(e -> 
        rulesButton.setScaleX(0.90));
        rulesButton.setOnMousePressed(e -> 
        rulesButton.setScaleY(0.90));
        rulesButton.setOnMouseReleased(e -> 
        rulesButton.setScaleX(1.0));
        rulesButton.setOnMouseReleased(e -> 
        rulesButton.setScaleY(1.0));
        rulesButton.setOnAction(event -> showRulesPopup(primaryStage));

        Button customizeButton = new Button("Customize");
        customizeButton.setFont(font);
        customizeButton.setStyle("-fx-background-color: #FFC0CB;" 
                    +"-fx-text-fill: white; " 
                    +"-fx-padding: 10px 20px; " 
                    +"-fx-background-radius: 5px; " 
                    +"-fx-border-color: #efbf04; " 
                    +"-fx-border-width: 2px; " 
                    +"-fx-border-radius: 5px;"
                    +"-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 0);");
        customizeButton.setOnMousePressed(e -> 
        customizeButton.setScaleX(0.90));
        customizeButton.setOnMousePressed(e -> 
        customizeButton.setScaleY(0.90));
        customizeButton.setOnMouseReleased(e -> 
        customizeButton.setScaleX(1.0));
        customizeButton.setOnMouseReleased(e -> 
        customizeButton.setScaleY(1.0));
        customizeButton.setOnAction(event -> showCustomizePopup(primaryStage,root,navBar));

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
        healthBar.setPrefHeight(30);


        double oldHealthValue = healthBar.getProgress();
        double newHealthValue = pet.getHealth() / 100.0;
        Timeline timeline = new Timeline(
        new KeyFrame(Duration.ZERO, new
        KeyValue(healthBar.progressProperty(), oldHealthValue)),
        new KeyFrame(Duration.seconds(0.5), new
        KeyValue(healthBar.progressProperty(), newHealthValue))
        );
        timeline.play();


        Label healthLabel = new Label("Health: " + (int)
        pet.getHealth() + "/100");
        healthLabel.setFont(font);

        if (pet.isDead()) {
            showGameOverPopup(primaryStage);
        }

        ProgressBar xpBar = new ProgressBar(pet.getCurrentXp() /
        pet.getMaxXpForThisLevel());
        xpBar.setPrefWidth(300);
        xpBar.setPrefHeight(30);
        xpBar.setStyle("-fx-accent: #4CAF50;");

        double oldXpValue = xpBar.getProgress();
        double newXpValue = pet.getCurrentXp()/
        pet.getMaxXpForThisLevel();
        Timeline xpTimeline = new Timeline(
        new KeyFrame(Duration.ZERO, new
        KeyValue(xpBar.progressProperty(), oldXpValue)),
        new KeyFrame(Duration.seconds(0.5), new
        KeyValue(xpBar.progressProperty(), newXpValue))
        );
        xpTimeline.play();

        Label xpLabel = new Label("XP: " 
        + (int)pet.getCurrentXp() + "/" 
        + (int)pet.getMaxXpForThisLevel());
        xpLabel.setFont(font);

        // Level
        Label levelLabel = new Label("Level: " + pet.getLevel());
        levelLabel.setFont(font);

        // Money
        Label moneyLabel = new Label("Money: " + (int) pet.getMoney());
        moneyLabel.setFont(font);

        ImageView coinImage = new ImageView(new Image("coin.gif"));
        coinImage.setFitWidth(20);
        coinImage.setPreserveRatio(true);

        HBox moneyRow = new HBox(5, moneyLabel, coinImage);
        moneyRow.setAlignment(Pos.CENTER);

        // Buttons: Feed and Play
        Button feedButton = new Button("Feed");
        feedButton.setFont(font);
        feedButton.setStyle("-fx-background-color: linear-gradient(to right,#FFC107, #FFD700, #FFC107);"
                    + "-fx-text-fill: white; "
                    + "-fx-padding: 10px 20px; "
                    + "-fx-background-radius: 5px; "
                    + "-fx-border-color: #efbf04; "
                    + "-fx-border-width: 2px; " 
                    + "-fx-border-radius: 5px;"
                    +"-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 0);");
        feedButton.setOnMousePressed(e -> 
        feedButton.setScaleX(0.90));
        feedButton.setOnMousePressed(e -> 
        feedButton.setScaleY(0.90));
        feedButton.setOnMouseReleased(e -> 
        feedButton.setScaleX(1.0));
        feedButton.setOnMouseReleased(e -> 
        feedButton.setScaleY(1.0));

        feedButton.setOnAction(event -> showFeedPopup(primaryStage));

        Button playButton = new Button("Coding Game");
        playButton.setFont(font);
        playButton.setStyle("-fx-background-color: #89CFF0; "
                    + "-fx-text-fill: white; "
                    + "-fx-padding: 10px 20px; "
                    + "-fx-background-radius: 5px; "
                    + "-fx-border-color: #efbf04; "
                    + "-fx-border-width: 2px; " 
                    + "-fx-border-radius: 5px;"
                    +"-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 0);");

        playButton.setOnMousePressed(e -> 
        playButton.setScaleX(0.90));
        playButton.setOnMousePressed(e -> 
        playButton.setScaleY(0.90));
        playButton.setOnMouseReleased(e -> 
        playButton.setScaleX(1.0));
        playButton.setOnMouseReleased(e -> 
        playButton.setScaleY(1.0));
        playButton.setOnAction(event -> new QuizScreen(pet, language, "play").start(primaryStage));

        HBox actionButtons = new HBox(10, feedButton, playButton);
        actionButtons.setAlignment(Pos.CENTER);

        // Go to Work Button
        Button workButton = new Button("Interview Questions");
        workButton.setFont(font);
        workButton.setStyle("-fx-background-color: #89CFF0; "
                    + "-fx-text-fill: white; "
                    + "-fx-padding: 10px 20px; "
                    + "-fx-background-radius: 5px; "
                    + "-fx-border-color: #efbf04; "
                    + "-fx-border-width: 2px; " 
                    + "-fx-border-radius: 5px;"
                    +"-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 0);");
        workButton.setOnMousePressed(e -> 
        workButton.setScaleX(0.90));
        workButton.setOnMousePressed(e -> 
        workButton.setScaleY(0.90));
        workButton.setOnMouseReleased(e -> 
        workButton.setScaleX(1.0));
        workButton.setOnMouseReleased(e -> 
        workButton.setScaleY(1.0));
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
        popup.setTitle("Rules");
        popup.getIcons().add(new Image(getClass().getResourceAsStream("/logo.gif")));

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #a0ebb4; "
        + "-fx-text-fill: white; "
        + "-fx-padding: 10px 20px; "
        + "-fx-background-radius: 5px; "
        + "-fx-border-color: #efbf04; "
        + "-fx-border-width: 2px; " 
        + "-fx-border-radius: 5px;");

        Text rules = new Text("""
            Let's prepare for your interview! \n\n1. Make sure to keep your pet healthy by feeding it. \n\n2. Answer interview questions to earn money and 
            buy food. \n\n4. Answer coding questions with your pet to gain XP and reach Level 10 for full preparation!\n\n5. Remember that you can't play 
            if your pet is too hungry.\n\nGoodluck!""");

        rules.setFont(font);
        rules.setWrappingWidth(400);

        layout.getChildren().addAll(rules);

        Scene popupScene = new Scene(layout, 500, 400);
        popup.setScene(popupScene);
        popup.showAndWait();
    }

    private void showCustomizePopup(Stage owner,HBox root, HBox navBar) {
    Stage popup = new Stage();
    popup.initModality(Modality.APPLICATION_MODAL);
    popup.initOwner(owner);
    popup.setTitle("Settings");
    popup.getIcons().add(new Image(getClass().getResourceAsStream("/logo.gif")));

    VBox layout = new VBox(20);
    layout.setAlignment(Pos.CENTER);
    layout.setPadding(new Insets(50));
    layout.setStyle("-fx-background-color: #FFC0CB;"
    + "-fx-border-color: #efbf04; "
    + "-fx-border-width: 2px; " 
    + "-fx-border-radius: 5px;");

    // Background color selection
    Text backgroundText = new Text("Choose your background:");
    backgroundText.setFont(font);

    HBox colorCircles = new HBox(20);
    colorCircles.setAlignment(Pos.CENTER);

    String[] colors = {"#f8e8d3", "#a8d5ba", "#D6EEF2"};
    for (String color : colors) {
        Circle circle = new Circle(30);
        circle.setFill(Color.web(color));
        circle.setStroke(Color.BLACK);
        circle.setOnMouseClicked(e -> {
            root.setStyle("-fx-background-color: " + color + ";");
            navBar.setStyle("-fx-background-color: " + color + ";");
            popup.close();
        });
        colorCircles.getChildren().add(circle);
    }

    Region spacer = new Region();

    // Volume control
    Text volumeText = new Text("Sound:");
    volumeText.setFont(font);

    Slider volumeSlider = new Slider(0, 100, 50);
    volumeSlider.setShowTickLabels(true);
    volumeSlider.setShowTickMarks(true);
    volumeSlider.setMajorTickUnit(50);
    volumeSlider.setMinorTickCount(5);
    volumeSlider.setStyle(
        "-fx-background-color: transparent;" +
        "-fx-pref-height: 40px;" +
        "-fx-pref-width: 300px;"
    );

    Platform.runLater(() -> {
        volumeSlider.lookup(".thumb").setStyle(
            "-fx-background-color: linear-gradient(to bottom, #FF69B4, #FF1493);" +
            "-fx-background-radius: 20px;" +
            "-fx-pref-height: 30px;" +
            "-fx-pref-width: 30px;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);"
        );

        volumeSlider.lookup(".track").setStyle(
            "-fx-background-color: linear-gradient(to right, #FFC0CB, #FF69B4);" +
            "-fx-background-radius: 15px;" +
            "-fx-pref-height: 20px;"
        );

        volumeSlider.lookup(".track-background").setStyle(
            "-fx-background-color: #FFE4E1;" +
            "-fx-background-radius: 15px;" +
            "-fx-pref-height: 10px;"
        );
    });
    if (backgroundMusic != null) {
        volumeSlider.setValue(backgroundMusic.getVolume() * 100);
    }

    volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
        if (backgroundMusic != null) {
            backgroundMusic.setVolume(newValue.doubleValue() / 100);
        }
    });

    layout.getChildren().addAll(
        backgroundText, colorCircles,spacer,volumeText, volumeSlider
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
        javafx.scene.shape.Rectangle bubble = new javafx.scene.shape.Rectangle(bubbleWidth, bubbleHeight);
        bubble.setFill(javafx.scene.paint.Color.web("#eff7fa"));
        bubble.setStroke(javafx.scene.paint.Color.BLACK);
        bubble.setStrokeWidth(2);
        bubble.setArcWidth(5);
        bubble.setArcHeight(5);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.GRAY); // Set shadow color
        dropShadow.setOffsetX(5); // Horizontal shadow offset
        dropShadow.setOffsetY(5); // Vertical shadow offset
        dropShadow.setRadius(10); // Shadow blur radius

    // Apply the shadow effect to the bubble
        bubble.setEffect(dropShadow);

    
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
        popup.setTitle("Menu");
        popup.getIcons().add(new Image(getClass().getResourceAsStream("/burger.png")));

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #f3fcd2;");

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

        Scene popupScene = new Scene(layout, 650,500);
        popup.setScene(popupScene);
        popup.setTitle("Menu");
        popup.showAndWait();
    }

    private Button createFoodButton(String foodName) {
    try {
        Food food = FoodFactory.create(foodName);
        VBox buttonContent = new VBox(5);
        buttonContent.setAlignment(Pos.CENTER);

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

        //default button style
        foodButton.setStyle("-fx-background-color: #c1f092; " +  
                            "-fx-text-fill: white; " +
                            "-fx-padding: 10px; " +
                            "-fx-background-radius: 5px; " +
                            "-fx-border-color: #edd145; " +
                            "-fx-border-width: 2px; " +
                            "-fx-border-radius: 5px;");

        // Hover effect with gradient gold color
        foodButton.setOnMouseEntered(e -> {
            foodButton.setStyle("-fx-background-color: linear-gradient(to right, #FFD700, #FFEA00);");  // Gradient gold on hover
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), foodButton);
            scaleTransition.setToX(1.05);
            scaleTransition.setToY(1.05);
            scaleTransition.play();
        });

        foodButton.setOnMouseExited(e -> {
            foodButton.setStyle("-fx-background-color: #c1f092; " +  
                            "-fx-text-fill: white; " +
                            "-fx-padding: 10px; " +
                            "-fx-background-radius: 5px; " +
                            "-fx-border-color: #edd145; " +
                            "-fx-border-width: 2px; " +
                            "-fx-border-radius: 5px;");
            ScaleTransition scaleTransition = new 
            ScaleTransition(Duration.millis(200), foodButton);
            scaleTransition.setToX(1.0);
            scaleTransition.setToY(1.0);
            scaleTransition.play();
        });

        // Button action
        foodButton.setOnAction(e -> {
            try {
                pet.spendMoney(food.getPrice());
                pet.eat(food);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("You fed your pet with " + foodName + "!");
                 DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #d4f1c5; " +
                            "-fx-border-color: #4CAF50; " + 
                            "-fx-border-width: 2px;");
        dialogPane.lookup(".content.label").setStyle("-fx-font-size: 14px; -fx-text-fill: #333;"); // Font customization

        
        alert.showAndWait();
    } catch (TamagotchiException ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("You don't have enough money, answer some interview questions!");

        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/upheavtt.ttf"), 16);
        if (customFont == null) {
            System.out.println("Failed to load font!");
        }

        // Access and style the DialogPane
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #fdecea; -fx-border-color: #ff4d4d; -fx-border-width: 2px;");
        
        // Set custom font for the content label
        Label contentLabel = (Label) dialogPane.lookup(".content.label");
        if (contentLabel != null) {
            contentLabel.setFont(customFont); // Apply the custom font
        }

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
