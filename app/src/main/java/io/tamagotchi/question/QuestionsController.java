package io.tamagotchi.question;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Controller class for managing questions in the Tamagotchi game.
 * Provides methods to load questions from a file and generate file names based on language and mode.
 */
public class QuestionsController {

    /**
     * Loads questions from a JSON file specified by the file path.
     *
     * @param filePath the path to the JSON file containing the questions
     * @return a Questions object containing the list of questions loaded from the file
     */
    public Questions loadQuestionsFromFile(String filePath) {
        List<Question> questions = null;
        try (FileReader reader = new FileReader(filePath)) {
            Type questionListType = new TypeToken<List<Question>>() {}.getType();
            Gson gson = new Gson();
            questions = gson.fromJson(reader, questionListType);
        } catch (IOException e) {
            System.out.println("No file found with this filepath: " + filePath);
        }
        return new Questions(questions);
    }

    /**
     * Generates a file name based on the specified language and mode.
     *
     * @param language the language of the questions
     * @param mode the mode of the questions
     * @return the generated file name
     */
    public String generateFileName(String language, String mode) {
        return "/questions/" + language.toLowerCase() + "-" + mode.toLowerCase() + ".json";
    }

}
