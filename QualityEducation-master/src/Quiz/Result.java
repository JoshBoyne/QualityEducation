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
import java.io.File;
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

    // Calculate the score based on user answers
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
    
    
     // Save results to a file in the QuizData package
    public void saveResultsToFile(String userName, Map<Integer, String> userAnswers, List<Question> questions) {
        try {
            // Create the QuizData directory if it doesn't exist
            String dir = System.getProperty("user.dir");
            String dirPath = dir + File.separator + "src" + File.separator + "QuizData";
            File dataDir = new File(dirPath);

            if (!dataDir.exists()) {
                if (dataDir.mkdirs()) {
                    System.out.println("QuizData directory created at: " + dataDir.getAbsolutePath());
                } else {
                    System.err.println("Failed to create QuizData directory.");
                    return;
                }
            } else {
                System.out.println("QuizData directory already exists: " + dataDir.getAbsolutePath());
            }

            // Create a results file for the user
            File resultsFile = new File(dataDir, userName + "_results.txt");
            if (!resultsFile.exists() && resultsFile.createNewFile()) {
                System.out.println("Results file created: " + resultsFile.getAbsolutePath());
            }

            // Write results to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultsFile))) {
                writer.write("Quiz Results for " + userName + ":\n");
                writer.write("=============================\n");

                for (int i = 0; i < questions.size(); i++) {
                    Question question = questions.get(i);
                    String userAnswer = userAnswers.getOrDefault(i, "No Answer");
                    writer.write("Q" + (i + 1) + ": " + question.getText() + "\n");
                    writer.write("Your Answer: " + userAnswer + "\n");
                    writer.write("Correct Answer: " + question.getCorrectAnswer() + "\n");
                    writer.write("-----------------------------\n");
                }

                writer.write("Final Score: " + score + "/" + totalQuestions + "\n");
                writer.write(getSummary());
                writer.newLine();
                System.out.println("Results saved successfully to: " + resultsFile.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("Error saving results: " + e.getMessage());
        }
    }


    
    
    //implement read/write for results
    //connect results with user
    //print question and result with users
    
}