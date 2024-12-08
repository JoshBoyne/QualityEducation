package GUIComponents;

import Main.Styles;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

//Frame serves as calander showing lifetime login history
public class LoginFrame extends JFrame {
    
    //Variables
    private LocalDate activeMonth;
    private Set<LocalDate> loggedInDates;

    //Constructor
    public LoginFrame(String title) {
        
        //Configuring this frame
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 800);
        this.setLocationRelativeTo(null);

        //Populating variables
        loggedInDates = readLoginDates();//Helper method
        LocalDate today = LocalDate.now();//Get today
        
        //Adding components and making frame visible
        this.add(createDateGrid(today.withDayOfMonth(1), today));//Passess first day of month, and today
        this.setVisible(true);
    }

    //Method for creating grid layout with dates, and adding to a scrollpane.
    private JPanel createDateGrid(LocalDate startDate, LocalDate today) {
        
        JPanel mainPanel = new JPanel(new BorderLayout());//Container
        JPanel gridPanel = new JPanel(new GridLayout(0, 7, 5, 5));//Grid panel for the dates
        JScrollPane scrollPane = new JScrollPane(gridPanel);//Adding grid to scroll pane

        populateDateGrid(gridPanel, startDate, today);//Calling helper method to populate the grid we just made
        
        //Adding components to container panel
        mainPanel.add(createNavigationPanel(startDate, gridPanel, today), BorderLayout.NORTH);//Creates heading panel with the month, and buttons
        mainPanel.add(scrollPane, BorderLayout.CENTER);//Adding now populated gridPanel

        return mainPanel;
    }

    //Method to actually populate the grid panel we pass to it, with todays date and the start of the month we want to populate
    private void populateDateGrid(JPanel gridPanel, LocalDate monthStart, LocalDate today) {
        
        gridPanel.removeAll();//Clear content
        YearMonth yearMonth = YearMonth.from(monthStart);//Getting the correct month and year for start of population

        for (int i = 1; i <= yearMonth.lengthOfMonth(); i++) {//Iterate through each day of the month
            LocalDate date = monthStart.withDayOfMonth(i);//Get the LocalDate of that day
            if (!date.isAfter(today))//Ensure that the date isn't after today
                gridPanel.add(createDatePanel(date));//Helper method to create a day panel for this grid cell.
        }

        gridPanel.revalidate();
        gridPanel.repaint();
    }

    //Helper method to actually create the panels for each cell in the "calender"
    private JPanel createDatePanel(LocalDate date) {
        
        //Creating the panel
        JPanel datePanel = new JPanel(new BorderLayout());
        datePanel.setPreferredSize(new Dimension(60, 60));
        datePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        //Color panel based on whether or not youve logged in
        if (loggedInDates.contains(date)) {
             datePanel.setBackground(Color.GREEN);
        } 
        else {
             datePanel.setBackground(Color.red);
        }

        //Creating the label with the date
        JLabel dateLabel = new JLabel(date.format(DateTimeFormatter.ofPattern("MMM dd")), SwingConstants.CENTER);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        datePanel.add(dateLabel, BorderLayout.CENTER);

        return datePanel;
    }

    //Helper method to create the header panel for the calander. Contains the date, and buttons to scroll months
    private JPanel createNavigationPanel(LocalDate startMonth, JPanel gridPanel, LocalDate today) {
        
        //Creating components
        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));//Add components after one another, in the center
        JLabel monthLabel = new JLabel(startMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy")));
        JButton prevButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");

        activeMonth = startMonth;

        prevButton.addActionListener(e -> updateMonth(-1, gridPanel, monthLabel, today));//Back one month
        nextButton.addActionListener(e -> updateMonth(1, gridPanel, monthLabel, today));//Forward one month

        //Adding components to panel
        navigationPanel.add(prevButton);
        navigationPanel.add(monthLabel);
        navigationPanel.add(nextButton);

        return navigationPanel;
    }

    //Helper method for repopulating calander after button is pressed
    private void updateMonth(int change, JPanel gridPanel, JLabel monthLabel, LocalDate today) {
        activeMonth = activeMonth.plusMonths(change);
        if (!activeMonth.isAfter(today)) {//Ensures user does not go into the future
            populateDateGrid(gridPanel, activeMonth, today);//Repopulate the date grid
            monthLabel.setText(activeMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy")));//Rechange the month label.
        }
    }

    //Method to read what dates the user has actually logged in on. 
    private Set<LocalDate> readLoginDates() {
        Set<LocalDate> dates = new HashSet<>();//Set to hold the dates we've logged in.
        String user = Styles.user.getName();
        File loginFile = new File(new File("").getAbsoluteFile(), "src/Users/"+user+"/login.txt");

        if (loginFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(loginFile))) {
                String line;
                while ((line = reader.readLine()) != null) 
                    dates.add(LocalDate.parse(line));//Adding the dates to the set
            } 
            catch (IOException e) {
                System.out.println("error reading date in login frame.");
            }
        }
        return dates;
    }
}
