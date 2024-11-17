package io.tamagotchi.question;

import java.util.List;

public class Question {
    private String question;
    List<String> answers;
    private int correctAnswer;
//    private int repetitions;
//    private boolean mastered;

    public Question(String question, List<String> answers, int correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
//        this.repetitions = 3;
//        this.mastered = false;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

//    public void setRepetitions(int repetitions) {
//        this.repetitions = repetitions;
//    }
//
//    public int getRepetitions() {
//        return repetitions;
//    }
//
//    public boolean isMastered() {
//        return mastered;
//    }
//
//    public void setMastered(boolean mastered) {
//        this.mastered = mastered;
//    }
//
//    public void answeredCorrectly() {
//        repetitions--;
//        if (repetitions == 0) {
//            mastered = true;
//        }
//    }
}
