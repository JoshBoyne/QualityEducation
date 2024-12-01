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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Josh
 */

public class QuizManager {

    private List<Question> questions; // List to hold questions
    private Map<Integer, String> userAnswers; // Map to store user answers
    private int currentQuestionIndex = 0; // Track the current question for admin navigation
     
    public QuizManager() {
        questions = new ArrayList<>();
        userAnswers = new HashMap<>();
    }

    // Load questions from a file
    public void loadQuestionsFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String text = parts[0];
                    String optionA = parts[1];
                    String optionB = parts[2];
                    String optionC = parts[3];
                    String optionD = parts[4];
                    String correctAnswer = parts[5];
                    questions.add(new Question(text, optionA, optionB, optionC, optionD, correctAnswer));
                }
            }
            System.out.println("Questions loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading questions: " + e.getMessage());
        }
    }

    // Save questions and user answers to a file
    public void saveToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Questions and User Answers:\n");
            for (int i = 0; i < questions.size(); i++) {
                Question question = questions.get(i);
                writer.write("Question " + (i + 1) + ": " + question.getText() + "\n");
                writer.write("Options: A) " + question.getOptionA() + " B) " + question.getOptionB() +
                        " C) " + question.getOptionC() + " D) " + question.getOptionD() + "\n");
                String userAnswer = userAnswers.get(i) != null ? userAnswers.get(i) : "No Answer";
                writer.write("User Answer: " + userAnswer + "\n");
                writer.write("Correct Answer: " + question.getCorrectAnswer() + "\n\n");
            }
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    // Read and display the file content
    public void readFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            System.out.println("File Content:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    // Add user answer for a question
    public void addUserAnswer(int questionIndex, String answer) {
        userAnswers.put(questionIndex, answer);
    }

    // Get questions (for display or modification)
    public List<Question> getQuestions() {
        return questions;
    }
     

    // Get the current question
    public Question getCurrentQuestion() {
        if (questions.isEmpty()) {
            return null; // No questions available
        }
        return questions.get(currentQuestionIndex);
    }

    // Navigate to the next question
    public Question nextQuestion() {
        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
        }
        return getCurrentQuestion();
    }

    // Navigate to the previous question
    public Question previousQuestion() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
        }
        return getCurrentQuestion();
    }

    // Add a new question
    public void addQuestion(Question question) {
        questions.add(question);
    }

    // Update the current question
    public void updateCurrentQuestion(String text, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
        if (!questions.isEmpty()) {
            Question currentQuestion = getCurrentQuestion();
            if (currentQuestion != null) {
                currentQuestion.setText(text);
                currentQuestion.setOptionA(optionA);
                currentQuestion.setOptionB(optionB);
                currentQuestion.setOptionC(optionC);
                currentQuestion.setOptionD(optionD);
                currentQuestion.setCorrectAnswer(correctAnswer);
            }
        }
    }

    // Delete the current question
    public void deleteCurrentQuestion() {
        if (!questions.isEmpty() && currentQuestionIndex >= 0 && currentQuestionIndex < questions.size()) {
            questions.remove(currentQuestionIndex);
            if (currentQuestionIndex >= questions.size()) {
                currentQuestionIndex = questions.size() - 1;
            }
        }
    }
}