/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prototypetest;

import javax.swing.JFrame;
import Quiz.QuizManager;
import java.util.List;
import Main.Account;
import Main.IOHandler;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import Quiz.Question;
/**
 *
 * @author Jason Donnelly  
 * @author Joshua Boyne 23343338
 * @author Owen Brown
 */
public class MainApp { 

  
    public static void main(String[] args) {//main method
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              
                JFrame frame = new JFrame("Home Panel");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new MainGUI());
                // Set the frame to pref size
                frame.pack();
                frame.setVisible(true);
                
                try {
                    // save the questions to the QuizData package
                    List<String> topics = List.of("Maths", "Geography", "Space", "Science");
                    for (String topic : topics) {
                        List<Question> questions = Question.getQuestionsByTopic(topic);
                        Question.saveQuestionsToFile(topic, questions);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                try {
                    Account test = new Account("a", "b");
                } catch (IOException ex) {
                    Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
   }