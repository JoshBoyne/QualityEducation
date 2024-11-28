package Main;

import GUIComponents.MainFrame;
import javax.swing.JFrame;
import java.awt.Dimension;
import Quiz.QuizManager;

import javax.swing.*;
import java.awt.*;

public class MainApp {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(new Dimension(920, 550));
        //frame.add(new MainFrame());
        
        
    System.out.println("Starting Quiz Application...");

    QuizManager manager = new QuizManager();

    String filePath = "questions.txt";

    System.out.println("Saving questions to file...");
    manager.saveQuestionsToFile(filePath);

    System.out.println("Verifying file...");
    manager.verifyFile(filePath);

    System.out.println("Reading questions from file...");
    manager.readFile(filePath);

    System.out.println("Quiz Application initialized successfully.");
    }
}
