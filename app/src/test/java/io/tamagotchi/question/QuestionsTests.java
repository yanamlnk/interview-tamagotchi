package io.tamagotchi.question;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionsTests {
    private Questions questions;

    @BeforeEach
    void setUp() {
        QuestionsController controller = new QuestionsController();
        String filePath = "src/test/resources/questions/test-questions.json";
        questions = controller.loadQuestionsFromFile(filePath);
    }

    @Test
    @DisplayName("Test for getting random questions from Questions field.")
    void testGetRandomQuestions() {
        List<Question> randomQuestions = questions.getRandomQuestions();
        assertNotNull(randomQuestions, "Random questions list should not be null");
        assertTrue(randomQuestions.size() <= 10, "Random questions list should contain at most 10 questions");
    }

    @Test
    @DisplayName("Test to verify that result lit of the method does not have duplicates.")
    void testGetRandomQuestionsNoDuplicates() {
        List<Question> randomQuestions = questions.getRandomQuestions();
        assertEquals(randomQuestions.size(),
                randomQuestions.stream().distinct().count(),
                "Random questions list should not contain duplicates"
        );
    }

    @Test
    @DisplayName("Test to verify that two random selections are not the same.")
    void testGetRandomQuestionsConsistency() {
        List<Question> randomQuestions1 = questions.getRandomQuestions();
        List<Question> randomQuestions2 = questions.getRandomQuestions();
        assertNotEquals(randomQuestions1, randomQuestions2, "Two random selections should not be the same");

        boolean hasDifferentElement = randomQuestions1.stream()
                .anyMatch(element -> !randomQuestions2.contains(element)) ||
                randomQuestions2.stream()
                        .anyMatch(element -> !randomQuestions1.contains(element));

        assertTrue(hasDifferentElement, "The lists should have at least one different element");
    }

}
