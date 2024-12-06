/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quiz;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Josh
 */
public class QuizTimer implements Serializable {
    private static final long serialVersionUID = 1L; // Unique identifier for serialization

    private int timeTakenSeconds;
    private String topic;
    private Date dateTaken;

    
    public QuizTimer(int timeTakenSeconds, String topic) {
        this.timeTakenSeconds = timeTakenSeconds;
        this.topic = topic;
        this.dateTaken = new Date(); // Sets the current date and time
    }

    // Getter methods
    public int getTimeTakenSeconds() {
        return timeTakenSeconds;
    }

    public String getTopic() {
        return topic;
    }

    public Date getDateTaken() {
        return dateTaken;
    }

    @Override
    public String toString() {
        return "QuizTimer{" +
                "timeTakenSeconds=" + timeTakenSeconds +
                ", topic='" + topic + '\'' +
                ", dateTaken=" + dateTaken +
                '}';
    }

    /**
     * Saves a QuizTimer object to a .dat file within the quizdata package.
     *
     * @param quizTimer The QuizTimer object to be saved.
     */
    public static void saveQuizTimerToFile(QuizTimer quizTimer) {
        String topic = quizTimer.getTopic();
        String filePath = getFilePath(topic); // Method to get file path based on topic
        File file = new File(filePath);

        // Ensure the directory exists
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            if (parentDir.mkdirs()) {
                System.out.println("Directory created at: " + parentDir.getAbsolutePath());
            } else {
                System.err.println("Failed to create directory.");
                return;
            }
        }

        List<QuizTimer> quizTimers = new ArrayList<>();
        if (file.exists()) {
            try (FileInputStream fStream = new FileInputStream(file);
                 ObjectInputStream oStream = new ObjectInputStream(fStream)) {
                quizTimers = (List<QuizTimer>) oStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error reading QuizTimer data: " + e.getMessage());
                e.printStackTrace();
                // Initialize a new list if reading fails
                quizTimers = new ArrayList<>();
            }
        }

        // Add the new QuizTimer object to the list
        quizTimers.add(quizTimer);

        // Write the updated list back to the file
        try (FileOutputStream fStream = new FileOutputStream(file);
             ObjectOutputStream oStream = new ObjectOutputStream(fStream)) {
            oStream.writeObject(quizTimers);
            System.out.println("QuizTimer for topic '" + topic + "' saved to: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error saving QuizTimer: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Generates the file path for storing QuizTimer data based on the topic.
     *
     * @param topic The quiz topic.
     * @return The file path as a String.
     */
    private static String getFilePath(String topic) {
        return "quizdata/" + topic + "_QuizTimer.dat";
    }
}