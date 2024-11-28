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
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Josh
 */
//For Admin Page
public class QuizManager {

    private List<Question> questions; // List to store questions

    // Constructor to initialize the questions list
    public QuizManager() {
        this.questions = new ArrayList<>();
    }

    // Method to load questions from a file
    public void loadQuestionsFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse and add questions to the list
                String[] parts = line.split(",");
                if (parts.length == 6) { // Ensure valid data
                    String text = parts[0];
                    String optionA = parts[1];
                    String optionB = parts[2];
                    String optionC = parts[3];
                    String optionD = parts[4];
                    String answer = parts[5];
                    Question question = new Question(text, optionA, optionB, optionC, optionD, answer);
                    questions.add(question); // Add to the list
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    // Method to save questions to a file
public void saveQuestionsToFile(String filePath) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        for (Question question : questions) {
            writer.write(question.getText() + "," +
                    question.getOptionA() + "," +
                    question.getOptionB() + "," +
                    question.getOptionC() + "," +
                    question.getOptionD() + "," +
                    question.getCorrectAnswer());
            writer.newLine();
        }
        System.out.println("Questions saved successfully to " + filePath);
    } catch (IOException e) {
        System.err.println("Error writing file: " + e.getMessage());
    }
}

public void verifyFile(String filePath) {
    File file = new File(filePath);
    if (file.exists()) {
        System.out.println("File exists: " + filePath);
    } else {
        try {
            if (file.createNewFile()) {
                System.out.println("File created successfully: " + filePath);
            }
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }
    }
}

public void readFile(String filePath) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        System.out.println("Reading file contents:");
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    } catch (IOException e) {
        System.err.println("Error reading file: " + e.getMessage());
    }
}

    // Getter for the questions list
    public List<Question> getQuestions() {
        return questions;
    }

    // Method to add a question (for admin)
    public void addQuestion(Question question) {
        questions.add(question);
    }

    // Method to delete a question by index (for admin)
    public void deleteQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            questions.remove(index);
        } else {
            System.err.println("Invalid question index.");
        }
    }
}