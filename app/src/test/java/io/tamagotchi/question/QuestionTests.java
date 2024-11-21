package io.tamagotchi.question;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionTests {
    @Test
    @DisplayName("Test for correct creation of Question object.")
    public void testQuestion() {
        Question question = new Question("What is the capital of France?", List.of("Paris", "London", "Berlin", "Madrid"), 0);
        assertEquals(question.getQuestion(), "What is the capital of France?");
        assertEquals(question.getAnswers(), List.of("Paris", "London", "Berlin", "Madrid"));
        assertEquals(0, question.getCorrectAnswer());
    }
}
