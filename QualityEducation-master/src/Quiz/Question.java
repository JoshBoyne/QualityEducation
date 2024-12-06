/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quiz;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Josh
 */


public class Question implements Serializable {
    //variables
    private static final long serialVersionUID = 1L; // Added for Serializable
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
    
    // Setters
    public void setText(String text) {
        this.text = text;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
     // method to get the questions by the topic
    public static List<Question> getQuestionsByTopic(String topic) {
    List<Question> questions;
    String filePath = getFilePath(topic);
    File file = new File(filePath);

    if (file.exists()) {
        System.out.println("Loading questions from file: " + file.getAbsolutePath());
        // If file exists load questions  from file
        questions = loadQuestionsFromFile(topic);
        if (questions == null) {
            System.out.println("Failed to load questions from file. Initializing hardcoded questions.");
            // If loading failed, initialize with hardcoded questions
            questions = initializeHardcodedQuestions(topic);
            saveQuestionsToFile(topic, questions); // Save the initialized questions
        }
    } else {
        System.out.println("Questions file not found. Initializing hardcoded questions and saving to file.");
        // if the file doesnt exist, it loads the hardcoded questions instead
        questions = initializeHardcodedQuestions(topic);
        saveQuestionsToFile(topic, questions);
    }

    return questions;
}

    // array list for hardcoded questions
    private static List<Question> initializeHardcodedQuestions(String topic) {
        List<Question> questions = new ArrayList<>();

        switch (topic) {//maths questions
            case "Maths":
                questions.add(new Question("What is 2 + 2?", "4", "3", "5", "2", "A"));
                questions.add(new Question("What is 3 x 3?", "6", "9", "3", "12", "B"));
                questions.add(new Question("What is 10 - 4?", "6", "7", "5", "8", "A"));
                questions.add(new Question("What is 8 / 2?", "4", "6", "8", "2", "A"));
                questions.add(new Question("What is 5 + 7?", "12", "10", "11", "13", "A"));
                break;
            case "Geography"://geography questions
                questions.add(new Question("What is the capital of France?", "Berlin", "Paris", "Rome", "Madrid", "B"));
                questions.add(new Question("What is the capital of Germany?", "Berlin", "Vienna", "Warsaw", "Prague", "A"));
                questions.add(new Question("What is the capital of Italy?", "Florence", "Venice", "Rome", "Milan", "C"));
                questions.add(new Question("What is the capital of Spain?", "Barcelona", "Madrid", "Lisbon", "Valencia", "B"));
                questions.add(new Question("What is the capital of Portugal?", "Porto", "Lisbon", "Madrid", "Seville", "B"));
                break;
            case "Space"://space questions
                questions.add(new Question("Which planet is known as the Red Planet?", "Venus", "Mars", "Jupiter", "Saturn", "B"));
                questions.add(new Question("Which planet is the largest in the solar system?", "Earth", "Neptune", "Jupiter", "Saturn", "C"));
                questions.add(new Question("Which planet is closest to the Sun?", "Venus", "Mercury", "Earth", "Mars", "B"));
                questions.add(new Question("Which planet has the most moons?", "Jupiter", "Saturn", "Uranus", "Neptune", "A"));
                questions.add(new Question("Which planet is known for its rings?", "Mars", "Venus", "Saturn", "Jupiter", "C"));
                break;
            case "Science"://science questions
                questions.add(new Question("What is the chemical symbol for water?", "H2O", "O2", "CO2", "HO2", "A"));
                questions.add(new Question("What is the chemical symbol for oxygen?", "O", "O2", "O3", "H", "B"));
                questions.add(new Question("What is the chemical symbol for carbon dioxide?", "CO", "CO2", "C2O", "O2", "B"));
                questions.add(new Question("What is the chemical symbol for sodium?", "Na", "S", "N", "Cl", "A"));
                questions.add(new Question("What is the chemical symbol for gold?", "Au", "Ag", "G", "Go", "A"));
                break;
            default:
                System.out.println("No hardcoded questions available for topic: " + topic);
                break;
        }
        return questions;
    }

    // Save questions to .dat file
    public static void saveQuestionsToFile(String topic, List<Question> questions) {
        String filePath = getFilePath(topic);
        File file = new File(filePath);

        // makes sure the directory exists
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            if (parentDir.mkdirs()) {
                System.out.println("Directory created at: " + parentDir.getAbsolutePath());
            } else {
                System.err.println("Failed to create directory.");
                return;
            }
        }

        try (FileOutputStream fStream = new FileOutputStream(file);
             ObjectOutputStream oStream = new ObjectOutputStream(fStream)) {
            oStream.writeObject(questions);
            System.out.println("Questions for " + topic + " saved to: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error saving questions: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Load questions from .dat file
    public static List<Question> loadQuestionsFromFile(String topic) {
        List<Question> questions = null;
        String filePath = getFilePath(topic);
        File file = new File(filePath);

        if (!file.exists()) {//file found for topic
            System.err.println("No questions file found for topic: " + topic);
            return null;
        }

        try (FileInputStream fStream = new FileInputStream(file);
             ObjectInputStream oStream = new ObjectInputStream(fStream)) {
            questions = (List<Question>) oStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading questions: " + e.getMessage());//error loading the quesitons
            e.printStackTrace();
        }

        return questions;
    }

   // method to get the file path
    private static String getFilePath(String topic) {
        String projectDir = System.getProperty("user.dir");
        String filePath = projectDir + File.separator + "src" + File.separator + "quizdata" + File.separator + "questions_" + topic + ".dat";
        return filePath;
    }
    
    

}