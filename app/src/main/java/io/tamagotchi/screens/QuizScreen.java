package io.tamagotchi.screens;

import io.tamagotchi.pet.Pet;
import io.tamagotchi.question.Question;
import io.tamagotchi.question.Questions;
import io.tamagotchi.question.QuestionsController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the quiz screen in the Tamagotchi game.
 * Allows the user to answer questions and tracks the score.
 */
public class QuizScreen extends Application {

    /**
     * The pet selected by the user.
     */
    private final Pet pet;

    /**
     * The language selected by the user.
     */
    private final String language;

    /**
     * The mode selected by the user.
     */
    private final String mode;

    /**
     * The list of questions for the quiz.
     */
    private final List<Question> questions;

    /**
     * The index of the current question.
     */
    private int currentQuestionIndex = 0;

    /**
     * The number of correct answers given by the user.
     */
    private int correctAnswers = 0;

    /**
     * The custom font used in the quiz screen.
     */
    private Font customFont;

    /**
     * The style for buttons in their default state.
     */
    private String buttonStyle;

    /**
     * The style for buttons when hovered over.
     */
    private String buttonStyleHover;

    /**
     * The style for buttons when selected.
     */
    private String buttonStyleSelected;

    /**
     * The style for the submit button in its default state.
     */
    private String submitButtonStyle;

    /**
     * The style for the submit button when active.
     */
    private String submitButtonStyleActive;

    /**
     * The style for the submit button when hovered over.
     */
    private String submitButtonStyleHover;

    /**
     * Constructs a new QuizScreen with the specified pet, language, mode, and font.
     *
     * @param pet the selected pet
     * @param language the selected language
     * @param mode the selected mode
     * @param font the custom font used in the quiz screen
     */
    public QuizScreen(Pet pet, String language, String mode, Font font) {
        this.pet = pet;
        this.language = language;
        this.mode = mode;

        QuestionsController controller = new QuestionsController();
        String filePath = controller.generateFileName(language, mode);
        Questions loadedQuestions = controller.loadQuestionsFromFile(getClass().getResource(filePath).getPath());
        this.questions = loadedQuestions.getRandomQuestions();
        this.customFont = font;

        this.buttonStyle = "-fx-background-color: transparent; " +
                "-fx-border-color: black; " +
                "-fx-border-width: 2px;" +
                "-fx-font-family: '" + (mode.equals("play") ? "Courier New" : customFont.getFamily()) + "';" +
                "-fx-font-size: 20px;" +
                "-fx-text-alignment: center;";
        this.buttonStyleHover = "-fx-background-color: #C2ECF4; " +
                "-fx-border-color: black; " +
                "-fx-border-width: 2px;" +
                "-fx-font-family: '" + (mode.equals("play") ? "Courier New" : customFont.getFamily()) + "';" +
                "-fx-font-size: 20px;"  +
                "-fx-text-alignment: center;";
        this.buttonStyleSelected = "-fx-background-color: #94CED8; " +
                "-fx-border-color: black; " +
                "-fx-border-width: 2px;" +
                "-fx-font-family: '" + (mode.equals("play") ? "Courier New" : customFont.getFamily()) + "';" +
                "-fx-font-size: 20px;"  +
                "-fx-text-alignment: center;";

        this.submitButtonStyle = "-fx-background-color: transparent; " +
                "-fx-border-color: black; " +
                "-fx-border-width: 2px;" +
                "-fx-font-family: '" + customFont.getFamily() + "';" +
                "-fx-font-size: 20px;" +
                "-fx-text-alignment: center;";
        this.submitButtonStyleActive = "-fx-background-color: #A0DF95; " +
                "-fx-border-color: black; " +
                "-fx-border-width: 2px;" +
                "-fx-font-family: '" +  customFont.getFamily() + "';" +
                "-fx-font-size: 20px;"  +
                "-fx-text-alignment: center;";
        this.submitButtonStyleHover = "-fx-background-color: #8AC380; " +
                "-fx-border-color: black; " +
                "-fx-border-width: 2px;" +
                "-fx-font-family: '" + customFont.getFamily() + "';" +
                "-fx-font-size: 20px;"  +
                "-fx-text-alignment: center;";
    }

    /**
     * Starts the application and displays the first question.
     *
     * @param primaryStage the primary stage for this application
     */
    @Override
    public void start(Stage primaryStage) {
        showNextQuestion(primaryStage);
    }

    /**
     * Displays the next question in the quiz.
     *
     * @param primaryStage the primary stage for this application
     */
    private void showNextQuestion(Stage primaryStage) {
        if (currentQuestionIndex >= questions.size()) {
            showSummary(primaryStage);
            return;
        }

        Label numberOfQuestion = new Label("Question " + (currentQuestionIndex + 1) + " of " + questions.size() + "\n\n");
        numberOfQuestion.setFont(customFont);
        numberOfQuestion.setAlignment(Pos.CENTER);
        numberOfQuestion.setStyle("-fx-font-family: '"+ customFont.getFamily()+ "'; -fx-font-size: 16px; -fx-text-alignment: center;");

        Question currentQuestion = questions.get(currentQuestionIndex);

        // Layout for the quiz screen
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #D6EEF2;");

        // Display question
        Label questionLabel;
        VBox questionBox = new VBox();
        questionBox.setAlignment(Pos.CENTER);

        if (mode.equals("play")) {
            String[] parts = currentQuestion.getQuestion().split("\n\n\n", 2);
            questionLabel = new Label(parts[0] + "\n\n");
            Label codeLabel = new Label(parts[1] + "\n\n");

            codeLabel.setWrapText(true);
            codeLabel.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 25px;");

            questionBox.getChildren().addAll(questionLabel, codeLabel);
        } else {
            questionLabel = new Label(currentQuestion.getQuestion() + "\n\n");
            questionBox.getChildren().add(questionLabel);
        }

        questionLabel.setWrapText(true);
        questionLabel.setFont(customFont);
        questionLabel.setStyle("-fx-font-family: '"+ customFont.getFamily()+ "'; -fx-font-size: 30px; -fx-text-alignment: center;");

        // Display answers as toggle buttons
        ToggleGroup answerGroup = new ToggleGroup();
        List<ToggleButton> answerButtons = new ArrayList<>();
        for (int i = 0; i < currentQuestion.getAnswers().size(); i++) {
            String answerText = currentQuestion.getAnswers().get(i);
            ToggleButton answerButton = new ToggleButton(answerText);
            answerButton.setToggleGroup(answerGroup);
            answerButton.setUserData(i);
            answerButton.setMaxWidth(Double.MAX_VALUE);
            answerButton.setAlignment(Pos.CENTER);
            answerButton.setFont(customFont);
            answerButton.setWrapText(true);
            answerButtons.add(answerButton);

            answerButton.setStyle(buttonStyle);


            answerButton.hoverProperty().addListener((obs, wasHovered, isHovered) -> {
                if (isHovered && !answerButton.isSelected()) {
                    answerButton.setStyle(buttonStyleHover);
                } else {
                    answerButton.setStyle(answerButton.isSelected() ? buttonStyleSelected : buttonStyle);
                }
            });

            answerButton.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
                if (isSelected) {
                    answerButton.setStyle(buttonStyleSelected);
                } else {
                    answerButton.setStyle(buttonStyle);
                }
            });
        }

        GridPane answerGrid = new GridPane();
        answerGrid.setHgap(10);
        answerGrid.setVgap(10);
        answerGrid.setAlignment(Pos.CENTER);
//        answerGrid.add(answerButtons.get(0), 0, 0);
//        answerGrid.add(answerButtons.get(1), 1, 0);
//        answerGrid.add(answerButtons.get(2), 0, 1);
//        answerGrid.add(answerButtons.get(3), 1, 1);

        answerGrid.setPrefWidth(Double.MAX_VALUE); // Make grid fill the parent

// Add column constraints for equal division
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        col1.setPercentWidth(50);
        col2.setPercentWidth(50);
        answerGrid.getColumnConstraints().addAll(col1, col2);

// Add buttons
        for (int i = 0; i < answerButtons.size(); i++) {
            ToggleButton answerButton = answerButtons.get(i);
            answerButton.setMaxWidth(Double.MAX_VALUE); // Button fills cell width
            GridPane.setFillWidth(answerButton, true);

            int col = i % 2; // 0 for first column, 1 for second
            int row = i / 2; // Increment row every 2 buttons
            answerGrid.add(answerButton, col, row);
        }

        answerButtons.forEach(button -> button.setPrefHeight(100));

        // Submit button
        Button submitButton = new Button("Submit");
        submitButton.setDisable(true);
        submitButton.setFont(customFont);
        submitButton.setStyle(submitButtonStyle);

        answerGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                submitButton.setDisable(false);
                submitButton.setStyle(submitButtonStyleActive);
            }

            if (newValue == null) {
                answerGroup.selectToggle(oldValue);
            }
        });

        submitButton.hoverProperty().addListener((obs, wasHovered, isHovered) -> {
            if (isHovered && !submitButton.isDisabled()) {
                submitButton.setStyle(submitButtonStyleHover);
            } else if (!submitButton.isDisabled()) {
                submitButton.setStyle(submitButtonStyleActive);
            } else {
                submitButton.setStyle(submitButtonStyle);
            }
        });

        submitButton.setOnAction(event -> {
            int selectedAnswer = (int) answerGroup.getSelectedToggle().getUserData();
            boolean isCorrect = selectedAnswer == currentQuestion.getCorrectAnswer();
            if (isCorrect) {
                correctAnswers++;
            }

            // Show correct/incorrect answers
            for (int i = 0; i < answerButtons.size(); i++) {
                if (i == currentQuestion.getCorrectAnswer()) {
                    answerButtons.get(i).setStyle("-fx-background-color: #A0DF95; " +
                            "-fx-text-fill: black;" +
                            "-fx-border-color: black; " +
                            "-fx-border-width: 2px;" +
                            "-fx-font-family: '" + (mode.equals("play") ? "Courier New" : customFont.getFamily()) + "';" +
                            "-fx-font-size: 20px;" +
                            "-fx-text-alignment: center;");
                } else {
                    answerButtons.get(i).setStyle("-fx-background-color: #fa5252; " +
                            "-fx-text-fill: black;" +
                            "-fx-border-color: black; " +
                            "-fx-border-width: 2px;" +
                            "-fx-font-family: '" + (mode.equals("play") ? "Courier New" : customFont.getFamily()) + "';" +
                            "-fx-font-size: 20px;"+
                            "-fx-text-alignment: center;");
                }
                answerButtons.get(i).setDisable(true);
            }

            // Next button
            submitButton.setText("Next");
            submitButton.setOnAction(e -> {
                currentQuestionIndex++;
                showNextQuestion(primaryStage);
            });
        });

        root.getChildren().addAll(numberOfQuestion, questionBox, answerGrid, submitButton);

        // Scene setup
        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle(mode.equals("work") ? "Interview Preparation" : "Coding Game");
        primaryStage.show();
    }

    /**
     * Displays the summary of the quiz.
     *
     * @param primaryStage the primary stage for this application
     */
    private void showSummary(Stage primaryStage) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #D6EEF2;");

        Label result = new Label();
        // Calculate rewards and penalties
        if (mode.equals("work")) {
            int coinsGained = 3 + (int) (correctAnswers * 0.5);
            pet.gainMoney(coinsGained);
            pet.looseHealth(20);
            result.setText("You earned " + coinsGained + " coins!");
        } else if (mode.equals("play")) {
            int xpGained = 10 + (2 * correctAnswers);
            pet.gainXp(xpGained);
            pet.looseHealth(30);
            result.setText("You gained " + xpGained + " XP!");
        }
        result.setFont(customFont);
        result.setStyle("-fx-font-family: '"+ customFont.getFamily()+ "'; -fx-font-size: 30px; -fx-text-alignment: center;");

        root.getChildren().add(result);

        // Return to Main Screen button
        Button returnButton = new Button("Return to Main Screen");
        returnButton.setOnAction(event -> new MainScreen(pet, language).start(primaryStage));

        returnButton.setStyle(submitButtonStyleActive);

        returnButton.hoverProperty().addListener((obs, wasHovered, isHovered) -> {
            if (isHovered) {
                returnButton.setStyle(submitButtonStyleHover);
            } else {
                returnButton.setStyle(submitButtonStyleActive);
            }
        });

        root.getChildren().add(returnButton);

        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Quiz Summary");
        primaryStage.show();
    }
}
