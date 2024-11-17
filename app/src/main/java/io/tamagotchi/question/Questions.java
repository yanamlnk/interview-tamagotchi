package io.tamagotchi.question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Questions {
    private List<Question> questions;

    public Questions(List<Question> question) {
        this.questions = question;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public List<Question> getRandomQuestions() {
        List<Question> selectedQuestions = new ArrayList<>();
        Random random = new Random();
        int attempts = 0;

//        while (selectedQuestions.size() < 10 && attempts < 50) {  // Give up after 50 attempts to avoid infinite loops
//            Question randomQuestion = questions.get(random.nextInt(questions.size()));
//            if (!randomQuestion.isMastered() && !selectedQuestions.contains(randomQuestion)) {
//                selectedQuestions.add(randomQuestion);
//            }
//            attempts++;
//        }

//        if (selectedQuestions.size() < 10) {
//            attempts = 0;
            while (selectedQuestions.size() < 10 && attempts < 50) {  // Give up after 50 attempts to avoid infinite loops
                Question randomQuestion = questions.get(random.nextInt(questions.size()));
                if (!selectedQuestions.contains(randomQuestion)) {
                    selectedQuestions.add(randomQuestion);
                }
                attempts++;
            }
//        }

        return selectedQuestions;
    }
}
