/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIComponents;

import Main.Account;
import Main.Styles;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author Owen
 */
public class GoalsFrame extends JFrame {
    DetailsPanel detailsPanel;
    public GoalsFrame(DetailsPanel detailsPanel) {
        this.detailsPanel = detailsPanel;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame
        this.setSize(700, 700); // Set the size of the frame
        this.setLocationRelativeTo(null); // Center the frame on the screen
        
        this.setLayout(new GridLayout(2, 2, 10, 10)); // 2x2 grid with gaps between sections
        
        // Create sections for each topic
        addSectionToFrame(this, "Maths", Color.GREEN, detailsPanel);
        addSectionToFrame(this, "Science", Color.ORANGE, detailsPanel);
        addSectionToFrame(this, "Geography", Color.CYAN, detailsPanel);
        addSectionToFrame(this, "Planets", Color.MAGENTA, detailsPanel);

        this.setVisible(true);
    }
    
    private void addSectionToFrame(JFrame frame, String topic, Color bgColor, DetailsPanel detailsPanel) {
    JPanel section = new JPanel();
    section.setBackground(bgColor);
    section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS)); // Vertical layout

 
    JLabel headingLabel = new JLabel(topic, SwingConstants.CENTER);
    headingLabel.setFont(new Font("Arial", Font.BOLD, 18));
    headingLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT); // Center the label horizontally
    section.add(headingLabel);

    section.add(Box.createRigidArea(new Dimension(0, 10)));

    
    JPanel streakPanel = new JPanel();
    streakPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5)); // Minimal padding
    streakPanel.setBackground(bgColor);

    JLabel streakLabel = new JLabel("Current Streak Goal:");
    JTextField streakTextField = new JTextField(10);
    streakTextField.addKeyListener(new KeyAdapter() {
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
            e.consume(); // Ignore non-numeric input
        }
    }
});

    
    //Set button for goal
    JButton setButton = new JButton("Set");
    
  
    
  
    setButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        Account user = Styles.user;
        if (user != null) {
            try {
                int goalValue = Integer.parseInt(streakTextField.getText());
                switch (topic) {
                    case "Maths":
                        user.getMaths().setQuizGoal(goalValue);
                        detailsPanel.setGoals("Math");
                        break;
                    case "Science":
                        user.getScience().setQuizGoal(goalValue);
                        detailsPanel.setGoals("Science");
                        break;
                    case "Geography":
                        user.getGeography().setQuizGoal(goalValue);
                        detailsPanel.setGoals("Geography");
                        break;
                    case "Planets":
                        user.getPlanets().setQuizGoal(goalValue);
                        detailsPanel.setGoals("Planets");
                        System.out.println("new goal: "+user.getPlanets().getQuizGoal());
                        break;
                    default:
                        break;
                }
              
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for the goal.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
});

    
    setButton.setBackground(Color.BLUE);
    setButton.setForeground(Color.WHITE);
    setButton.setFocusPainted(false);
    setButton.setFont(new Font("Arial", Font.PLAIN, 12)); // Smaller font for the button

    
    setButton.addActionListener(e -> {
        String streakGoal = streakTextField.getText();
       
    });

    //Adding compontents
    streakPanel.add(streakLabel);
    streakPanel.add(streakTextField);
    streakPanel.add(setButton);
    section.add(streakPanel);


    section.add(Box.createRigidArea(new Dimension(0, 10)));

    //Custom goals
    JPanel customGoalsPanel = new JPanel();
    customGoalsPanel.setLayout(new BoxLayout(customGoalsPanel, BoxLayout.Y_AXIS));
    customGoalsPanel.setBackground(bgColor);

    
    JLabel customGoalsHeading = new JLabel("Custom Goals", SwingConstants.LEFT);
    customGoalsHeading.setFont(new Font("Arial", Font.BOLD, 14));
    customGoalsHeading.setAlignmentX(JLabel.LEFT_ALIGNMENT);
    customGoalsPanel.add(customGoalsHeading);

    //Scrollable textArea for goals
    JTextArea goalsArea = new JTextArea(5, 20);
    goalsArea.setEditable(false);
    goalsArea.setLineWrap(true);
    goalsArea.setWrapStyleWord(true);
    JScrollPane goalsScrollPane = new JScrollPane(goalsArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Vertical scrollbar only
    customGoalsPanel.add(goalsScrollPane);

    
    JPanel recordGoalHeaderPanel = new JPanel();
    recordGoalHeaderPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5)); // Align left with small padding
    recordGoalHeaderPanel.setBackground(bgColor);

    //Save button
    JLabel recordNewGoalHeading = new JLabel("Record New Goal");
    recordNewGoalHeading.setFont(new Font("Arial", Font.BOLD, 14));
    JButton saveButton = new JButton("Save");
    //saveButton.setBackground(Color.GREEN);
    saveButton.setForeground(Color.WHITE);
    saveButton.setFocusPainted(false);

    // Add label and button to the header panel
    recordGoalHeaderPanel.add(recordNewGoalHeading);
    recordGoalHeaderPanel.add(saveButton);

    customGoalsPanel.add(recordGoalHeaderPanel);

   //Text area for adding new goals
    JTextArea newGoalArea = new JTextArea(3, 20);
    newGoalArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    customGoalsPanel.add(newGoalArea);

    
    saveButton.addActionListener(e -> {
        String newGoal = newGoalArea.getText();
     
    });

    section.add(customGoalsPanel);

    frame.add(section);
}
}
