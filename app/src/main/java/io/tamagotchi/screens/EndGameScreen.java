package io.tamagotchi.screens;

import io.tamagotchi.pet.Pet;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * The EndGameScreen class represents the final screen of the game, displaying
 * either a winning or losing message based on the game result.
 */
public class EndGameScreen extends Application {

    /**
     * The pet object associated with the end game screen.
     */
    private Pet pet;

    /**
     * The language the pet has mastered.
     */
    private String language;

    /**
     * The result of the game ("winner" or "loser").
     */
    private String result;

    /**
     * The custom font to be used in the screen.
     */
    private Font customFont;

    /**
     * Constructs an EndGameScreen with the specified pet, language, result, and custom font.
     *
     * @param pet the pet object
     * @param language the language the pet has mastered
     * @param result the result of the game ("winner" or "loser")
     * @param customFont the custom font to be used in the screen
     */
    public EndGameScreen(Pet pet, String language, String result, Font customFont) {
        this.pet = pet;
        this.language = language.toLowerCase();
        this.result = result;
        this.customFont = customFont;
    }

    /**
     * Creates and returns the scene for the end game screen based on the game result.
     *
     * @return the scene for the end game screen
     */
    public Scene createScene() {
        VBox root = new VBox(20);
        root.setAlignment(javafx.geometry.Pos.CENTER);

        // Configure based on result
        if (result.equals("winner")) {
            root.setStyle("-fx-background-color: #FDF559;");

            // Winner text
            Text winnerText = new Text("Congratulations! " + pet.getName() +
                    " reached level 10! He has mastered " + language + ".");
            winnerText.setFont(Font.font(customFont.getFamily(), 40));
            winnerText.setFill(Color.BLACK);
            winnerText.setTextAlignment(TextAlignment.CENTER); // Center-align text
            winnerText.setWrappingWidth(800);

            // Pet image
            ImageView petImage = createImageView(pet.getImageUrl(), false);

            // Add to root
            root.getChildren().addAll(winnerText, petImage);
        } else if (result.equals("loser")) {
            root.setStyle("-fx-background-color: #AAAAAA;");

            // Loser text
            Text loserText = new Text("Oh no! " + pet.getName() +
                    " got so hungry that he decided to abandon everything (you included).");
            loserText.setFont(Font.font(customFont.getFamily(), 40));
            loserText.setFill(Color.DARKRED);
            loserText.setTextAlignment(TextAlignment.CENTER);
            loserText.setWrappingWidth(800);


            // Greyed-out pet image
            ImageView petImage = createImageView(pet.getImageUrl(), true);

            // Add to root
            root.getChildren().addAll(loserText, petImage);
        }

        return new Scene(root, 1000, 800);
    }


    /**
     * Starts the EndGameScreen and sets the scene on the primary stage.
     *
     * @param primaryStage the primary stage for this application
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(createScene());
        primaryStage.setTitle("End of Game");
        primaryStage.show();
    }

    /**
     * Helper method to create an ImageView for the pet image.
     *
     * @param imageUrl The URL of the pet's image.
     * @param isGreyScale Whether the image should be greyed out.
     * @return Configured ImageView.
     */
    private ImageView createImageView(String imageUrl, boolean isGreyScale) {
        Image image = new Image(imageUrl);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);

        if (isGreyScale) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setSaturation(-1.0); // Makes the image greyscale
            imageView.setEffect(colorAdjust);
        }

        return imageView;
    }
}
