package io.tamagotchi.question;

import java.util.List;

/**
 * Represents a question in the Tamagotchi game.
 * Each question has a text, a list of possible answers, and an index of the correct answer.
 */
public class Question {

    /**
     * The text of the question.
     */
    private String question;

    /**
     * The list of possible answers to the question.
     */
    List<String> answers;

    /**
     * The index of the correct answer in the list of answers.
     */
    private int correctAnswer;

    /**
     * Constructs a new Question with the specified text, answers, and correct answer index.
     *
     * @param question the text of the question
     * @param answers the list of possible answers
     * @param correctAnswer the index of the correct answer in the list of answers
     */
    public Question(String question, List<String> answers, int correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    /**
     * Returns the text of the question.
     *
     * @return the text of the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Returns the list of possible answers to the question.
     *
     * @return the list of possible answers
     */
    public List<String> getAnswers() {
        return answers;
    }

    /**
     * Returns the index of the correct answer in the list of answers.
     *
     * @return the index of the correct answer
     */
    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
