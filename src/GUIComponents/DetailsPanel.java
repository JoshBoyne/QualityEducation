package GUIComponents;

import Main.Account;
import Main.Styles;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class DetailsPanel extends JPanel {
private JLabel yourNameLabel;
    public DetailsPanel(MainFrame home) {
        this.setBackground(Color.yellow);
        this.setPreferredSize(new Dimension(600, 130));
        this.setLayout(new BorderLayout());

        //
        
        //
        
        // Avatar section
        JPanel avatar = new JPanel();
        avatar.setPreferredSize(new Dimension(50, 50));
        avatar.setBackground(Color.black);

        JPanel avatarContainer = new JPanel();
        avatarContainer.setLayout(new BorderLayout());
        avatarContainer.setPreferredSize(new Dimension(50, 100));
        avatarContainer.setBackground(Color.red);

        JPanel aviNameContainer = new JPanel();
        aviNameContainer.setLayout(new BorderLayout());
        aviNameContainer.setPreferredSize(new Dimension(595, 50));
        aviNameContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Removed padding

        // Name and XP labels
        JPanel nameAndXp = new JPanel();
        nameAndXp.setLayout(new BoxLayout(nameAndXp, BoxLayout.Y_AXIS));
        nameAndXp.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Removed padding
        yourNameLabel = new JLabel("Your Name");
        yourNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // Reduced padding
        JLabel xp = new JLabel("Level 1");
        xp.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // Reduced padding
        nameAndXp.add(yourNameLabel);
        nameAndXp.add(xp);

        aviNameContainer.add(avatar, BorderLayout.WEST);
        aviNameContainer.add(nameAndXp, BorderLayout.CENTER);
        this.add(aviNameContainer, BorderLayout.NORTH);

        // Center Panel (with GridBagLayout)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.blue);
        centerPanel.setPreferredSize(new Dimension(100, 25));

        // Creating GridBagLayout container
        JPanel centerC = new JPanel();
        centerC.setLayout(new GridBagLayout());
        //centerC.setBackground(Color.orange);
        centerC.setPreferredSize(new Dimension(10, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1d;
        gbc.weighty = 1d;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5); // Reduced padding between grid items

        // Add panels with text to centerC
        addPanelWithText(centerC, gbc, 0, "Math", "Quiz Goal:", "Quiz Count:", Color.green);
        addPanelWithText(centerC, gbc, 1, "Science", "Quiz Goal:", "Quiz Count:", Color.white);
        addPanelWithText(centerC, gbc, 2, "Geography", "Quiz Goal:", "Quiz Count:", Color.red);
        addPanelWithText(centerC, gbc, 3, "Planets", "Quiz Goal:", "Quiz Count:", Color.blue);

        centerPanel.add(centerC, BorderLayout.CENTER);
        this.add(centerPanel, BorderLayout.CENTER);

        // Bottom Panel (centerS)
        JPanel centerS = new JPanel();
        //centerS.setBackground(Color.green);
        centerS.setPreferredSize(new Dimension(595, 30));
        centerS.setLayout(new GridBagLayout());

        JButton btn1 = new JButton("Customize Goals");
        btn1.addActionListener((ActionEvent e) -> {
            JFrame tempFrame = new JFrame("Topics");
            tempFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame
            tempFrame.setSize(700, 700); // Set the size of the frame
            tempFrame.setLocationRelativeTo(null); // Center the frame on the screen

            // Set layout for the frame
            tempFrame.setLayout(new GridLayout(2, 2, 10, 10)); // 2x2 grid with gaps between sections

            // Create sections for each topic
            addSectionToFrame(tempFrame, "Maths", Color.GREEN);
            addSectionToFrame(tempFrame, "Science", Color.ORANGE);
            addSectionToFrame(tempFrame, "Geography", Color.CYAN);
            addSectionToFrame(tempFrame, "Planets", Color.MAGENTA);

            // Make the frame visible
            tempFrame.setVisible(true);
        });
        JButton btn2 = new JButton("Account");
        btn2.addActionListener((ActionEvent e) -> {
            JFrame tempFrame = new JFrame("Topics");
            tempFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame
            tempFrame.setSize(500, 1000); // Set the size of the frame
            tempFrame.setLocationRelativeTo(null); // Center the frame on the screen
            // Make the frame visible
            tempFrame.setVisible(true);
        });
        JButton btn3 = new JButton("Logins");
btn3.addActionListener((ActionEvent e) -> {
    JFrame tempFrame = new JFrame("Logins");
    tempFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame
    tempFrame.setSize(500, 800); // Adjusted size for better layout
    tempFrame.setLocationRelativeTo(null); // Center the frame on the screen

    // Get the current date
    LocalDate currentDate = LocalDate.now();
    
    // Set up the initial display with the current month
    JPanel mainPanel = createScrollableDateGrid(currentDate.withDayOfMonth(1), currentDate);
    tempFrame.add(mainPanel);

    // Make the frame visible
    tempFrame.setVisible(true);
});



        gbc.gridx = 1;
        centerS.add(btn1, gbc);

        gbc.gridx = 2;
        centerS.add(btn2, gbc);

        gbc.gridx = 3;
        centerS.add(btn3, gbc);

    this.add(centerS, BorderLayout.SOUTH);
    }
    
    private void addPanelWithText(JPanel container, GridBagConstraints gbc, int gridX, String subject, String quizGoal, String quizCount, Color bgColor) {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(3, 1)); // 3 rows for each text line
    panel.setBackground(Styles.secondarySalmon);
    panel.setPreferredSize(new Dimension(100, 75)); // Adjust for height if needed
    panel.setBorder(BorderFactory.createLineBorder(Color.black, 2));

    // Add labels to the panel
    JLabel label1 = new JLabel(subject, SwingConstants.CENTER);
    JLabel label2 = new JLabel(quizGoal, SwingConstants.CENTER);
    JLabel label3 = new JLabel(quizCount, SwingConstants.CENTER);

    panel.add(label1);
    panel.add(label2);
    panel.add(label3);

    // Add the panel to the container
    gbc.gridx = gridX;
    container.add(panel, gbc);
}

private void addSectionToFrame(JFrame frame, String topic, Color bgColor) {
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

    //Set button for goal
    JButton setButton = new JButton("Set");
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

private JPanel createScrollableDateGrid(LocalDate startDate, LocalDate today) {
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());

    //Grid panel for calander
    JPanel gridPanel = new JPanel();
    gridPanel.setLayout(new GridLayout(0, 7, 5, 5)); // 7 columns, dynamic rows
    gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around the grid

    //Calling populate data helper function to populate the grid array. 
    populateDateGrid(gridPanel, startDate, today);

    //Making the panel scrollable
    JScrollPane scrollPane = new JScrollPane(gridPanel, 
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    //The main panel for the moth being viewed
    JPanel navigationPanel = createNavigationPanel(startDate, gridPanel, today);

    // Add components to the main panel
    mainPanel.add(navigationPanel, BorderLayout.NORTH);
    mainPanel.add(scrollPane, BorderLayout.CENTER);

    return mainPanel;
}



private void populateDateGrid(JPanel gridPanel, LocalDate monthStart, LocalDate today) {
    gridPanel.removeAll(); //Getting rid of what is currently in the grid. 

    //Calculating the dates needed for the motnth
    YearMonth yearMonth = YearMonth.from(monthStart);
    LocalDate currentDate = yearMonth.atDay(1);
    int daysInMonth = yearMonth.lengthOfMonth();//Total number of panels needed for the grid

    //Adding the panels
    for (int day = 1; day <= daysInMonth; day++) {
        LocalDate date = currentDate.plusDays(day - 1);
        if (!date.isAfter(today)) { // Only add dates up to today
            gridPanel.add(createDatePanel(date));
        }
    }

    gridPanel.revalidate();
    gridPanel.repaint();
}


//To do: Add this to its own class, with other functions for these frames.
private JPanel createDatePanel(LocalDate date) {
    JPanel datePanel = new JPanel();
    datePanel.setPreferredSize(new Dimension(60, 60)); // Square panels
    datePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Black border
    datePanel.setLayout(new BorderLayout()); // Center the label

    JLabel dateLabel = new JLabel(date.format(DateTimeFormatter.ofPattern("MMM dd")), SwingConstants.CENTER);
    dateLabel.setFont(new Font("Arial", Font.PLAIN, 12)); // Customize font size

    datePanel.add(dateLabel, BorderLayout.CENTER);

    return datePanel;
}

LocalDate activeMonth;
private JPanel createNavigationPanel(LocalDate currentMonth, JPanel gridPanel, LocalDate today) {
    JPanel navigationPanel = new JPanel();
    navigationPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

    JButton prevButton = new JButton("Previous");
    JButton nextButton = new JButton("Next");


    activeMonth = currentMonth.minusMonths(0);//Easiest way I found of handling this localDate object
    
    //Previous month button
    prevButton.addActionListener(e -> {
        activeMonth = activeMonth.minusMonths(1);
        populateDateGrid(gridPanel, activeMonth, today);//Repopulating grid
        updateMonthLabel(navigationPanel, activeMonth);//Reassigning label
   
    });

    //Next month button
    nextButton.addActionListener(e -> {
        if(!activeMonth.plusMonths(1).isAfter(today))
            activeMonth = activeMonth.plusMonths(1);
        //
        if (!activeMonth.isAfter(today)) {
            populateDateGrid(gridPanel, activeMonth, today);
            updateMonthLabel(navigationPanel, activeMonth);
           
        }
    });

    //Btns
    navigationPanel.add(prevButton);
    navigationPanel.add(new JLabel(currentMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy")))); // Month-Year label
    navigationPanel.add(nextButton);

    return navigationPanel;
}



private void updateMonthLabel(JPanel navigationPanel, LocalDate month) {
    //Todo: take this out of its own function. Logic doesnt require it. 
    JLabel monthLabel = (JLabel) navigationPanel.getComponent(1);
    monthLabel.setText(month.format(DateTimeFormatter.ofPattern("MMMM yyyy")));
}

    public JLabel getYourNameLabel() {
        return this.yourNameLabel;
    }
}
