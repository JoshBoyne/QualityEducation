package GUIComponents;


import Main.Styles;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GoalsFrame extends JFrame {
    
    //Variables
    private DetailsPanel detailsPanel;
    private Map<String, JTable> goalsTables = new HashMap<>(); //Storing each subject as the key, and the corrosponding table as the value.

    //Constructor
    public GoalsFrame(DetailsPanel detailsPanel) {
        
        //Configuring the frame itself.
        this.detailsPanel = detailsPanel;//Keeping an instance of details panel, as this class modifies data in it. 
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Close this frame when exitied. 
        this.setLocationRelativeTo(null); //Centering frame.
        this.setSize(700, 700); 
        this.setBackground(Color.BLACK);
        
        this.setLayout(new GridLayout(2, 2, 10, 10)); //There are four sections in this frame - devided into a 2x2 grid.

        //Calling helper method that adds each section to the frame. Decided on this approach instead of making another Panel class for practice.
        addSectionToFrame(this, "Math", detailsPanel);
        addSectionToFrame(this, "Science", detailsPanel);
        addSectionToFrame(this, "Geography", detailsPanel);
        addSectionToFrame(this, "Planets", detailsPanel);

        this.setVisible(true);
        this.setResizable(false);
    }

    //Primary helper method - creates the panels and adds them to the frame.
    private void addSectionToFrame(JFrame frame, String topic, DetailsPanel detailsPanel) {
        
        //Creating the JPanel.
        JPanel section = new JPanel();
        section.setBackground(Styles.secondarySalmon);
        section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS)); //Primary layout - components arranged vertically.

        //Main heading label. 
        JLabel headingLabel = new JLabel(topic, SwingConstants.CENTER);//Centering the text inside the label.
        headingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headingLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT); //Centering the lable inside the parent. 
        section.add(headingLabel);

        //Vertical spacing between the heading label and quiz streak goal box beneath. 
        section.add(Box.createRigidArea(new Dimension(0, 10)));

        //Streak Goal parent panel. 
        JPanel streakPanel = new JPanel();
        streakPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));//Horizontal flow layout - 2 components side by side. 
        streakPanel.setBackground(Styles.secondarySalmon);

        //Label on left-hand side. 
        JLabel streakLabel = new JLabel("Current Streak Goal:");
        
        //Text field for the user's input for their quiz streak goal. 
        JTextField streakTextField = new JTextField(10);
        
        //Adding two components to the streak goal parent panel. 
        streakPanel.add(streakLabel);
        streakPanel.add(streakTextField);

         //Set button for taking the user's input in the above text field, and passing it to the user/Account object, and updating the detailsPanel GUI. 
        JButton setButton = new JButton("Set");
        setButton.setForeground(Color.BLACK);
        setButton.setFocusPainted(false);//Disables visual effect when button is focused. Can be replaced with setFocusable(false).
        setButton.addActionListener(e -> {//Adding the button's functionality. 
            String streakGoalText = streakTextField.getText();//Getting the user's input - new quiz streak goal.
            try {//Rather than use a DocumentFilter or regex, I wanted to get more experience with try catch functionality - so I'm using it here to ensure the only accepted input is an int.
                int streakGoal = Integer.parseInt(streakGoalText);//Will cause the catch code to execute if user input isn't an int.
                setGoalForSubject(topic, streakGoal); //Calling helper method to pass goal to user object. Passes the relevant subject, and the user input.
                detailsPanel.setGoals(topic); //Updates the GUI in the detailsPanel class.
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for the streak goal.", "Input Error", JOptionPane.ERROR_MESSAGE);//Displaying an error message for incorrect input.
            }
        });
        
        //Adding the button to the streak panel horizontal container - 3 components. 
        streakPanel.add(setButton);        
        
        //Adding the streak panel to the vertical parent container - second row. 
        section.add(streakPanel);

        //Adding a vertical gap to space components. 
        section.add(Box.createRigidArea(new Dimension(0, 10)));

        //Custom goals panel parent container - vertical layout. 
        JPanel customGoalsPanel = new JPanel();
        customGoalsPanel.setLayout(new BoxLayout(customGoalsPanel, BoxLayout.Y_AXIS));
        customGoalsPanel.setBackground(Styles.secondarySalmon);
        
        //Horizontal container for the custom goals heading, and delete button. 
        JPanel customGoalsHeadingContainer = new JPanel();
        customGoalsHeadingContainer.setBackground(Styles.secondarySalmon);
        customGoalsHeadingContainer.setLayout(new BoxLayout(customGoalsHeadingContainer, BoxLayout.X_AXIS));
        
        //Custom goals JLabel heading. 
        JLabel customGoalsHeading = new JLabel("Custom Goals", SwingConstants.RIGHT);
        customGoalsHeading.setFont(new Font("Arial", Font.BOLD, 14));
        customGoalsHeading.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
        
        //For some inexplicable reason, setting the alignment to right is lining it up to the left - which is what I want. 
        //I would like it more to the left - but can't figure it out.
        //The delete button relies on components not yet initialized here - so it will be getting created and added to the container later on. 
        
        //Adding heading to the horitzontal container. 
        customGoalsHeadingContainer.add(customGoalsHeading);
        customGoalsHeadingContainer.add(Box.createHorizontalStrut(50));//Add horizontal spacing between heading and delete button. 
        
        customGoalsPanel.add(customGoalsHeadingContainer);//Adding the heading container to the panel. 
        customGoalsPanel.add(Box.createVerticalStrut(10));//Add vertical gap between heading and table. 

        //Creating a table to hold people's custom goals. 
        JTable goalsTableForTopic = new JTable(new DefaultTableModel(new String[]{""}, 0));//New JTable, with a new TabelModel, with no inital rows, and an empty header.
        goalsTableForTopic.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//Ensuring only one row is selectable at a time.
        JScrollPane tableScrollPane = new JScrollPane(goalsTableForTopic);//Adding the table to a scroll pane as the content will be loaded into it dynamically.
        
        //Adding scroll pane / table to the customGoalsPanel vertical container. 
        customGoalsPanel.add(tableScrollPane);
        customGoalsPanel.add(Box.createVerticalStrut(5));

        //Storing each instance of the JTable (for each topic) in the global Map for population. 
        goalsTables.put(topic, goalsTableForTopic);

        //Populating table with data from the user's subject text files - if they exist. 
        File userDirectory = new File("src/Users/"+Styles.user.getName());
        String fileName = topic + ".txt";
        File subjectFile = new File(userDirectory, fileName);//Final path "src/Users/Owen/Math.txt" - sub Owen for current user name.
        if (subjectFile.exists()) {
            saveNewGoal(topic, subjectFile);//Populate the topic's Jtable using saveNewGoal helper method. 
        }

        //Label heading - left compontent inside recordGoal - which itself is under the jtable component in the parent. 
        JLabel recordNewGoalHeading = new JLabel("Record New Goal", SwingConstants.RIGHT);
        recordNewGoalHeading.setFont(new Font("Arial", Font.BOLD, 14));
        recordNewGoalHeading.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
        
        customGoalsPanel.add(recordNewGoalHeading);

        //Container for new goal text area and button - horizontal. 
        JPanel newGoalContainer = new JPanel();
        newGoalContainer.setBackground(Styles.secondarySalmon);
        newGoalContainer.setLayout(new BoxLayout(newGoalContainer, BoxLayout.X_AXIS));//Horizontal layout
        
        //Text area for adding new goals
        JTextArea newGoalArea = new JTextArea(3, 20);
        newGoalArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        newGoalArea.setLineWrap(true);
        newGoalArea.setWrapStyleWord(true);
        newGoalContainer.add(newGoalArea);
        
        //Save button for saving new goals. 
        JButton saveButton = new JButton("Save");
        saveButton.setForeground(Color.BLACK);
        saveButton.setFocusPainted(false);
     

        saveButton.addActionListener(e -> {
            
            String newGoal = newGoalArea.getText();//Get user input
            
            if (newGoal.isEmpty()) {//If the user tries to save a blank note. 
                return;
            }
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(subjectFile, true))) {//Creating the file writer to append new notes.
                writer.write(newGoal);//Add the user input to the file. 
                writer.newLine();//Add a new ling. 
                System.out.println("Goal saved successfully.");
            } 
            catch (IOException ex) {
                System.out.println("Error saving the goal - "+ex);
            }

            newGoalArea.setText(""); //Empty user input field.

            //Update the table with the newly saved note. 
            saveNewGoal(topic, subjectFile);//Helper method to repopulate the table. 
        });
           
        //Adding spacing and save button to newGoalContainer.
        newGoalContainer.add(Box.createHorizontalStrut(10));
        newGoalContainer.add(saveButton);
        newGoalContainer.add(Box.createHorizontalStrut(5));
        
        //Adding newGoalContainer and spacing to parent container.
        customGoalsPanel.add(newGoalContainer);
        customGoalsPanel.add(Box.createVerticalStrut(10));

        //Delete button for deleting selected goal. 
        JButton deleteButton = new JButton("Delete");
        deleteButton.setForeground(Color.BLACK);
        deleteButton.setFocusPainted(false);//Prevent focusing visuals. 
        
        deleteButton.addActionListener(e -> {
            int selectedRow = goalsTableForTopic.getSelectedRow();//Grabbing the index of the focused row in the jtable. 
            if (selectedRow != -1) {//If there is a valid selection. 
                String goalToDelete = (String) goalsTableForTopic.getValueAt(selectedRow, 0);//Getting the text to be deleted. 
                deleteGoalFromFile(subjectFile, goalToDelete);//Calling helper method, passing file and text to be deleted. 
                ((DefaultTableModel) goalsTableForTopic.getModel()).removeRow(selectedRow); //Remove row from the table model.         
            } 
            else {
                System.out.println("No goal selected.");
            }
        });
        
        //Adding delete button to the goalsHeading container initialized earlier. 
        customGoalsHeadingContainer.add(deleteButton);

        //Heading for update field and button - added straight to parent vertical container.
        JLabel updateHeading = new JLabel("Update Goal", SwingConstants.RIGHT);
        updateHeading.setFont(new Font("Arial", Font.BOLD, 14));
        updateHeading.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
        customGoalsPanel.add(updateHeading);
        
        //Container panel for updateGoalField textField and button
        JPanel updateContainer = new JPanel();
        updateContainer.setLayout(new BoxLayout(updateContainer, BoxLayout.X_AXIS));
        updateContainer.setBackground(Styles.secondarySalmon);
        
        //Text field to update selected goal. 
        JTextField updateGoalField = new JTextField(20);
        updateGoalField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        
        //Adding textField to container panel. 
        updateContainer.add(updateGoalField);

        //Update button for changing goal data.
        JButton updateButton = new JButton("Update");
        updateButton.setForeground(Color.BLACK);
        updateButton.setFocusPainted(false);
    
        
        updateButton.addActionListener(e -> {
            int selectedRow = goalsTableForTopic.getSelectedRow();//Get selected row for updating
            
            if (selectedRow != -1) {//If valid
                String newGoal = updateGoalField.getText();//Get user input from update field
                
                if (!newGoal.isEmpty()) {//If valid
                    String oldGoal = (String) goalsTableForTopic.getValueAt(selectedRow, 0);//Get the old text content being updated
                    updateGoalInFile(subjectFile, oldGoal, newGoal);//Helper method to update goal. Passes txt file, old content, and updated content
                    
                    //Update the table content - done seperately to updating the content in the txt file. 
                    goalsTableForTopic.setValueAt(newGoal, selectedRow, 0);
                    updateGoalField.setText(""); //Clearing user input field. 
                } 
                else {
                    System.out.println("No content entered.");
                }
            } 
            else {
                System.out.println("No row selected.");
            }
        });

        //Adding final components and spacing. 
        updateContainer.add(Box.createHorizontalStrut(10));
        updateContainer.add(updateButton);
        updateContainer.add(Box.createHorizontalStrut(5));
        
        customGoalsPanel.add(updateContainer);
        customGoalsPanel.add(Box.createVerticalStrut(5));
        
        section.add(customGoalsPanel);
        
        frame.add(section);//Adding the panel to the frame (this class) as opposed to returning the panel. Could be done either way. frame.add(addSectionToFrame(args));
    }
    
    //Simple helper method to change the user's quiz streak goals. 
    private void setGoalForSubject(String topic, int streakGoal) {
        switch (topic) {
            case "Math":
                Styles.user.getMaths().setQuizGoal(streakGoal);
                break;
            case "Science":
                Styles.user.getScience().setQuizGoal(streakGoal);
                break;
            case "Geography":
                Styles.user.getGeography().setQuizGoal(streakGoal);
                break;
            case "Planets":
                Styles.user.getPlanets().setQuizGoal(streakGoal); 
                break;
            default:
                break;
        }
    }

    //Helper method for saving new goals. The goal is saved to the text file in the button action listener code, then the table is repopulated here.
    private void saveNewGoal(String topic, File subjectFile) {
        
        JTable goalsTableForTopic = goalsTables.get(topic);//Getting the correct JTable from the map - using the subject as the key. 

        //If the table doesnt exist.
        if (goalsTableForTopic == null) {
            System.err.println("No JTable found for subject: " + topic);
            return;
        }

        //Getting the table model, clearing it, and repopulating it. 
        DefaultTableModel model = (DefaultTableModel) goalsTableForTopic.getModel();
        model.setRowCount(0);

        try (BufferedReader reader = new BufferedReader(new FileReader(subjectFile))) {
            
            String line;
            while ((line = reader.readLine()) != null) {//Itterating through all lines in the file
                model.addRow(new Object[]{line});//Adding the text content of each line to the table as a new row. 
            }
        } 
        catch (IOException ex) {
            System.out.println("Error saving data: "+ex);
        }
    }

    //Helper method for deleting goals. 
    private void deleteGoalFromFile(File subjectFile, String goalToDelete) {
        
        try {
            //Read all lines from the file - getting the path from the file object.
            List<String> lines = Files.readAllLines(subjectFile.toPath());
            lines.remove(goalToDelete); //Remove the matching line from the list.

            //Rewrite the file. Not very effecient. 
            Files.write(subjectFile.toPath(), lines, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error deleting the goal: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Helper method for updating goals. 
    private void updateGoalInFile(File subjectFile, String oldGoal, String newGoal) {
        
        try {
            //Load all lines from the file into a list. 
            List<String> lines = Files.readAllLines(subjectFile.toPath());
            int index = lines.indexOf(oldGoal);//Gets the index of the text to be updated in the list. 

            if (index != -1) {//If valid
                lines.set(index, newGoal); //Updating the text in the list
                Files.write(subjectFile.toPath(), lines, StandardOpenOption.TRUNCATE_EXISTING);//Removing content, and rewriting the file according to the list. 
            }
            
        } 
        catch (IOException ex) {
            System.out.println("Error deleting goal: "+ex);
        }
    }
}
