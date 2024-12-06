/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prototypetest;

import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author jason
 */
public class FeedbackForm {
    private JTextField Emailfield;
    private JTextArea QtArea;
    private JButton CFconfirmButton;
    
     // Constructor
    public FeedbackForm(JTextField Emailfield, JTextArea QtArea, JButton CFconfirmButton) {
        this.Emailfield = Emailfield;
        this.QtArea = QtArea;
        this.CFconfirmButton = CFconfirmButton;



        CFconfirmButton.addActionListener(evt -> handleConfirmAction());
    }
     // Handle confirm button action
    private void handleConfirmAction() {
        String email = Emailfield.getText().trim();
        String query = QtArea.getText().trim();

        // Validate email
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(null, "Please enter a valid email address.", "Invalid Email", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate query text
        if (query.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter your question or query.", "Empty Query", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Display confirmation popup
        String message = "Thank you for your feedback!\n\nEmail: " + email + "\nQuery: " + query;
        JOptionPane.showMessageDialog(null, message, "Feedback Submitted", JOptionPane.INFORMATION_MESSAGE);

        // Clear fields after submission
        Emailfield.setText("");
        QtArea.setText("");
    }
    
    // Helper method to validate email using regex
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }


}