/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quiz;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Josh
 */

public class Question {
    private String text;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;

    // Constructor
    public Question(String text, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
        this.text = text;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
    }

    // Getters
    public String getText() {
        return text;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    // Static method to return questions by topic
    public static List<Question> getQuestionsByTopic(String topic) {
        List<Question> questions = new ArrayList<>();

        switch (topic) {
            case "Maths":
                questions.add(new Question("What is 2 + 2?", "4", "3", "5", "2", "A"));
                questions.add(new Question("What is 3 x 3?", "6", "9", "3", "12", "B"));
                questions.add(new Question("What is 10 - 4?", "6", "7", "5", "8", "A"));
                questions.add(new Question("What is 8 / 2?", "4", "6", "8", "2", "A"));
                questions.add(new Question("What is 5 + 7?", "12", "10", "11", "13", "A"));
                break;
            case "Geography":
                questions.add(new Question("What is the capital of France?", "Berlin", "Paris", "Rome", "Madrid", "B"));
                questions.add(new Question("What is the capital of Germany?", "Berlin", "Vienna", "Warsaw", "Prague", "A"));
                questions.add(new Question("What is the capital of Italy?", "Florence", "Venice", "Rome", "Milan", "C"));
                questions.add(new Question("What is the capital of Spain?", "Barcelona", "Madrid", "Lisbon", "Valencia", "B"));
                questions.add(new Question("What is the capital of Portugal?", "Porto", "Lisbon", "Madrid", "Seville", "B"));
                break;
            case "Space":
                questions.add(new Question("Which planet is known as the Red Planet?", "Venus", "Mars", "Jupiter", "Saturn", "B"));
                questions.add(new Question("Which planet is the largest in the solar system?", "Earth", "Neptune", "Jupiter", "Saturn", "C"));
                questions.add(new Question("Which planet is closest to the Sun?", "Venus", "Mercury", "Earth", "Mars", "B"));
                questions.add(new Question("Which planet has the most moons?", "Jupiter", "Saturn", "Uranus", "Neptune", "A"));
                questions.add(new Question("Which planet is known for its rings?", "Mars", "Venus", "Saturn", "Jupiter", "C"));
                break;
            case "Science":
                questions.add(new Question("What is the chemical symbol for water?", "H2O", "O2", "CO2", "HO2", "A"));
                questions.add(new Question("What is the chemical symbol for oxygen?", "O", "O2", "O3", "H", "B"));
                questions.add(new Question("What is the chemical symbol for carbon dioxide?", "CO", "CO2", "C2O", "O2", "B"));
                questions.add(new Question("What is the chemical symbol for sodium?", "Na", "S", "N", "Cl", "A"));
                questions.add(new Question("What is the chemical symbol for gold?", "Au", "Ag", "G", "Go", "A"));
            default:
                break;
        }
        return questions;
    }
    
    //do read and write for questions
    public static void saveQuestionsToFile(String topic, List<Question> questions) throws IOException {
        // Create the QuizData directory if it doesn't exist
        String projectDir = System.getProperty("user.dir");
        String packagePath = projectDir + File.separator + "src" + File.separator + "QuizData";
        File packageDir = new File(packagePath);

        if (!packageDir.exists()) {
            if (packageDir.mkdirs()) {
                System.out.println("QuizData package created at: " + packagePath);
            } else {
                System.err.println("Failed to create QuizData package.");
                return;
            }
        }

        // Create the topic-specific file
        String filePath = packagePath + File.separator + "questions_" + topic + ".txt";
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }

        // Write questions to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Question question : questions) {
                writer.write(question.getText() + "," +
                        question.getOptionA() + "," +
                        question.getOptionB() + "," +
                        question.getOptionC() + "," +
                        question.getOptionD() + "," +
                        question.getCorrectAnswer());
                writer.newLine();
            }
        }

        System.out.println("Questions for " + topic + " saved to: " + filePath);
    }
}