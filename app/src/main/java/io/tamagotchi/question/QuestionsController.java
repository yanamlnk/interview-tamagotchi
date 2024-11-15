package io.tamagotchi.question;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class QuestionsController {

    public Questions loadQuestionsFromFile(String filePath) {
        List<Question> questions = null;
        try (FileReader reader = new FileReader(filePath)) {
            Type questionListType = new TypeToken<List<Question>>() {}.getType();
            Gson gson = new Gson();
            questions = gson.fromJson(reader, questionListType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Questions(questions);
    }

    public void saveQuestionsToFile(String filePath, Questions questions) {
        try (FileWriter writer = new FileWriter(filePath)) {
            Gson gson = new Gson();
            gson.toJson(questions.getQuestions(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}