package io.tamagotchi.screens;

import io.tamagotchi.pet.Pet;
import io.tamagotchi.question.Question;
import io.tamagotchi.question.Questions;
import io.tamagotchi.question.QuestionsController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class QuizScreen extends Application {
    private final Pet pet;
    private final String language;
    private final String mode;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;
    private Button submitButton;
    private ToggleGroup answerGroup;
    private VBox questionBox;

    public QuizScreen(Pet pet, String language, String mode) {
        this.pet = pet;
        this.language = language;
        this.mode = mode;
    }

    @Override
    public void start(Stage primaryStage) {
        QuestionsController questionsController = new QuestionsController();
        String filePath = getClass().getResource("/questions/" + language.toLowerCase() + "-" + mode + ".json").getPath();
        questions = questionsController.loadQuestionsFromFile(filePath).getRandomQuestions();

        questionBox = new VBox(20);
        questionBox.setPadding(new Insets(20));
        questionBox.setAlignment(Pos.CENTER);

        submitButton = new Button("Submit");
        submitButton.setDisable(true);
        submitButton.setOnAction(event -> handleSubmit(primaryStage));

        showQuestion();

        VBox root = new VBox(20, questionBox, submitButton);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Quiz");
        primaryStage.show();
    }

    private void showQuestion() {
        Question question = questions.get(currentQuestionIndex);
        Label questionLabel = new Label(question.getQuestion());
        answerGroup = new ToggleGroup();

        VBox answersBox = new VBox(10);
        for (int i = 0; i < question.getAnswers().size(); i++) {
            RadioButton answerButton = new RadioButton(question.getAnswers().get(i));
            answerButton.setToggleGroup(answerGroup);
            answerButton.setUserData(i);
            answersBox.getChildren().add(answerButton);
        }

        answerGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                submitButton.setDisable(false);
            }
        });

        questionBox.getChildren().setAll(questionLabel, answersBox);
    }

    private void handleSubmit(Stage primaryStage) {
        int selectedAnswer = (int) answerGroup.getSelectedToggle().getUserData();
        Question question = questions.get(currentQuestionIndex);

        if (selectedAnswer == question.getCorrectAnswer()) {
            correctAnswers++;
        }

        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
            showQuestion();
            submitButton.setDisable(true);
        } else {
            showSummary(primaryStage);
        }
    }

    private void showSummary(Stage primaryStage) {
        int reward = 0;
        if (mode.equals("work")) {
            reward = 3 + (int) (correctAnswers * 0.5);
            pet.gainMoney(reward);
            pet.looseHealth(20);
        } else if (mode.equals("play")) {
            reward = 10 + (correctAnswers * 2);
            pet.gainXp(reward);
            pet.looseHealth(30);
        }

        Label summaryLabel = new Label("You gained " + reward + (mode.equals("work") ? " coins." : " XP."));
        Button returnButton = new Button("Return to Main Screen");
        returnButton.setOnAction(event -> new MainScreen(pet, language).start(primaryStage));

        VBox summaryBox = new VBox(20, summaryLabel, returnButton);
        summaryBox.setPadding(new Insets(20));
        summaryBox.setAlignment(Pos.CENTER);

        Scene summaryScene = new Scene(summaryBox, 800, 600);
        primaryStage.setScene(summaryScene);
    }
}