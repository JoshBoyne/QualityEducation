package Main;

import GUIComponents.MainFrame;
import javax.swing.JFrame;
import java.awt.Dimension;
import Quiz.QuizManager;
import Quiz.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Quiz Application");
           System.out.println("Current working directory: " + System.getProperty("user.dir"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(new Dimension(920, 550));
        //frame.add(new MainFrame());
        
        
    QuizManager manager = new QuizManager();
        String filePath = "questions.txt";

        // Load questions from a file
        manager.loadQuestionsFromFile(filePath);

        // Simulate adding user answers
        List<Question> questions = manager.getQuestions();
        manager.addUserAnswer(0, "A"); // User selected "A" for the first question
        manager.addUserAnswer(1, "B"); // User selected "B" for the second question

        // Save questions and answers to the file
        manager.saveToFile("quiz_results.txt");

        // Read and display the saved file
        manager.readFile("quiz_results.txt");
    }
}
