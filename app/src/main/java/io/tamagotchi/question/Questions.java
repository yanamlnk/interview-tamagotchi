package io.tamagotchi.question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a collection of questions in the Tamagotchi game.
 * Provides methods to retrieve questions and select random questions.
 */
public class Questions {

    /**
     * The list of questions.
     */
    private List<Question> questions;

    /**
     * Constructs a new Questions object with the specified list of questions.
     *
     * @param questions the list of questions
     */
    public Questions(List<Question> questions) {
        this.questions = questions;
    }

    /**
     * Returns the list of questions.
     *
     * @return the list of questions
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * Returns a list of 10 random questions from the collection.
     * If there are fewer than 10 questions, returns all available questions.
     *
     * @return a list of 10 random questions
     */
    public List<Question> getRandomQuestions() {
        List<Question> selectedQuestions = new ArrayList<>();
        Random random = new Random();
        int attempts = 0;

        while (selectedQuestions.size() < 10 && attempts < 50) {  // Give up after 50 attempts to avoid infinite loops
            Question randomQuestion = questions.get(random.nextInt(questions.size()));
            if (!selectedQuestions.contains(randomQuestion)) {
                selectedQuestions.add(randomQuestion);
            }
            attempts++;
        }

        return selectedQuestions;
    }
}
