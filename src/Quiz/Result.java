/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quiz;

/**
 *
 * @author Josh
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {
    private int score;
    private int totalQuestions;

    // Constructor
    public Result() {
        this.score = 0;
        this.totalQuestions = 0;
    }

    // Calculate the score based on user's answers
    public void calculateScore(Map<Integer, String> userAnswers, List<Question> questions) {
        score = 0;
        totalQuestions = questions.size();

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            String correctAnswer = question.getCorrectAnswer();
            String userAnswer = userAnswers.get(i);

            if (correctAnswer != null && correctAnswer.equals(userAnswer)) {
                score++;
            }
        }
    }

    // Get the score
    public int getScore() {
        return score;
    }

    // Get the total questions
    public int getTotalQuestions() {
        return totalQuestions;
    }

    // Generate a summary of the results
    public String getSummary() {
        return "You scored " + score + " out of " + totalQuestions + ".";
    }
    
    
    public void saveResultsToFile(String filePath, Map<String, Integer> results) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        for (Map.Entry<String, Integer> entry : results.entrySet()) {
            writer.write(entry.getKey() + ":" + entry.getValue());
            writer.newLine();
        }
    } catch (IOException e) {
        System.err.println("Error writing results: " + e.getMessage());
    }
}
    
    public Map<String, Integer> loadResultsFromFile(String filePath) {
    Map<String, Integer> results = new HashMap<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(":");
            if (parts.length == 2) {
                String user = parts[0];
                int score = Integer.parseInt(parts[1]);
                results.put(user, score);
            }
        }
    } catch (IOException | NumberFormatException e) {
        System.err.println("Error reading results: " + e.getMessage());
    }
    return results;
}
}