package io.tamagotchi.question;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionsControllerTests {
    private final QuestionsController controller = new QuestionsController();

    @Test
    @DisplayName("Test for generating file name based on language and mode.")
    void testGenerateFileName() {
        assertEquals("/questions/java-play.json", controller.generateFileName("java", "play"), "Correct name of file must be questions/java-play.json");
        assertEquals("/questions/java-play.json", controller.generateFileName("JaVa", "PLAY"), "Method to generate filepath must be case-insensitive.");
    }

    @Test
    @DisplayName("Test for loading questions correctly from a valid file.")
    void testLoadQuestionsFromValidFile() {
        String filePath = "/questions/test-questions.json";
        Questions questions = controller.loadQuestionsFromFile(filePath);

        assertNotNull(questions, "With correct filepath object Questions must be returned.");
        assertNotNull(questions.getQuestions(), "With correct filepath object Questions must be created and List questions must be assigned.");
        assertEquals(20, questions.getQuestions().size(), "Test file consist of 20 questions, size of list must be appropriate.");

        assertEquals("What is Java?", questions.getQuestions().get(0).getQuestion(), "First question must be 'What is Java?'");
        assertEquals("Which is not an OOP principle?", questions.getQuestions().get(19).getQuestion(), "Last question must be 'Which is not an OOP principle?'");

        assertEquals(3, questions.getQuestions().get(0).getAnswers().size(), "Size of all answers is test file must be 3");
        assertEquals(List.of("A coffee brand", "A programming language", "An island"), questions.getQuestions().get(0).getAnswers(), "Answers for first question must be correct.");

        assertEquals(1, questions.getQuestions().get(0).getCorrectAnswer(), "Correct answer for first question must be 1");
    }

    @Test
    @DisplayName("Test for loading questions from a non-existent file.")
    void testLoadQuestionsFromNotValidFile() {
        String filePath = "src/test/resources/questions/nonexistent.json";
        Questions questions = controller.loadQuestionsFromFile(filePath);

        assertNotNull(questions);
        assertNull(questions.getQuestions()); // Expecting null list for invalid file
    }
}
