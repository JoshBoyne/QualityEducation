/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import GUIComponents.BarClass;
import GUIComponents.CustomButton;
import GUIComponents.DailyQuestPanel;
import GUIComponents.MainFrame;
import GUIComponents.QotdPanel;
import Subjects.Geography;
import Subjects.Maths;
import Subjects.Planets;
import Subjects.Science;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JLabel;
import prototypetest.Home;

/**
 *
 * @author owen
 */
public class Account implements Serializable {
   
    //Variable Decleration
    private static final long serialVersionUID = 123456789L;//Added to help with data persistance issues when class properties are changed.
   
    //User details
    private String name;
    private String password;
    
    //Logged In control boolean.
    private boolean loggedIn = false;
    
    //This data is populated each day when the user is logged in. IOHandler - DailyLogIn().
    private String dir;//User's personal directory for storing all their data.
    String[] qotd;//Question of the day, and its awnser.
    String[] ans;//3 Other incorrect awnsers. 
    int[] logins;//User's login status for the last 7 days. 
    String[] dailyQuests;//Four daily quests for today.
    
    
    int[] questStatus = new int[] {0, 0, 0, 0};//Boolean status of quest completion. 
    int[] goalCount = new int[] {0, 0, 0, 0};//Array of user's quiz goal number for easy access. 
    int[] subjectCount = new int[] {0, 0, 0, 0};//Array of how many total quizes the user has completed for each subject. 
    
    //Subject objects
    private Maths maths;
    private Science science;
    private Geography geography;
    private Planets planets;
   
    //Constructor.
    public Account(String name, String pass) throws IOException {
        //Creating subjects.
        this.maths = new Maths(0);
        this.science = new Science(0);
        this.geography = new Geography(0);
        this.planets = new Planets(0);
        
        //Assigning Variables.
        this.name = name;
        this.password = pass;
        dir = IOHandler.makeAccountDir(name);
        login();//Running the login method. This constructor should only be called when a user creates an account, which automatically logs them in.
    }
    
    //Login method. 
    public void login() throws IOException {
        this.loggedIn = false;
        loggedIn = IOHandler.dailyLogin(new File(dir), this);
    }
    
    //Method for saving the user.
    public void saveState() throws IOException {
        IOHandler.saveState(this);
    }
    
    //Primary method of UI population. As the tracker UI is heavily dynamic, most of its content relies on this class, and that content changes. 
    //Again, all of the data being set here is reassigned to the class each new day via the login method.
    public void updateUI(Home home) {
        
        //////////////////////////////////////////////////////////////////////
        ////            LOGIC FOR QUESTION OF THE DAY HERE                ////
        //////////////////////////////////////////////////////////////////////
        
        //Setting the question of the day in the gui. 
        home.mainFrame1.getCenterPanel().getQotdPanel().getQuestion().setText(qotd[0]);
        
        //Updating the awnser buttons
        Component[] components = home.mainFrame1.getCenterPanel().getQotdPanel().getButtons().getComponents();

        //Loop through components and cast to JButton.
        for (int i = 0; i < components.length && i < ans.length; i++) {
            if (components[i] instanceof JButton) {
                JButton btn = (JButton) components[i]; 
                btn.setText(ans[i]); //Setting the button text to one of the incorrect answers.
            }
        }
        
        //Assigning the correct awnser to the last button in the panel. 
        JButton correctBtn = (JButton) components[components.length-1];
        correctBtn.setText(qotd[1]);
        
        //The button logic is dependent on the text in them. So, this extracts the awnesers (text), shuffles then, and reassigns them. 
        //This makes sure that the correct awnser is randomly generated amongst the four buttons. 
        ArrayList<String> buttonTexts = new ArrayList<>();
        for (Component comp : components) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                buttonTexts.add(button.getText());
            }
        }

        //Shuffle the awnsers.
        Collections.shuffle(buttonTexts);

        //Reassign the awnsers.
        int index = 0;//Could just use a for loop instead. 
        for (Component comp : components) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                button.setText(buttonTexts.get(index));
                index++;
            }
        }
        
        //Adding actionListener to the buttons 
        for (Component comp : components) {
          if (comp instanceof JButton) {
              JButton button = (JButton) comp; // Cast to JButton
              button.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      checkAns(button.getText(), home);//Calls the check awnser method in this class. Passes the awnser, and the instance of the home panel.
                  }
              });
            }
        }
        
        //////////////////////////////////////////////////////////////////////
        ////            LOGIC FOR THE STREAK BOXES HERE                   ////
        //////////////////////////////////////////////////////////////////////
        
        //logins contains 7 entries. For a 0, user did not log in. For a 1, user did log in. 
        home.mainFrame1.getCenterPanel().getStreakContainer().setColors(logins);//Passes the logins to the streak container object, which assigns its own colors.
        ArrayList<BarClass> bars = home.mainFrame1.getCenterPanel().getStreakContainer().getStreakBars();//Getting each individual day panel in the streak.
        
        //Setting the date inside the streak boxes.
        LocalDate day = LocalDate.now().minusDays(6);
        int date = day.getDayOfMonth();
        for(BarClass bar : bars) {
            bar.setText(Integer.toString(date));
            day = day.plusDays(1);
            date = day.getDayOfMonth();
        }
        
        //////////////////////////////////////////////////////////////////////
        ////            LOGIC FOR THE DAILY QUESTS HERE                   ////
        //////////////////////////////////////////////////////////////////////
        
       DailyQuestPanel math = home.mainFrame1.getDailyQuestsContainer().getMaths();
       DailyQuestPanel science = home.mainFrame1.getDailyQuestsContainer().getScience();
       DailyQuestPanel geography = home.mainFrame1.getDailyQuestsContainer().getGeography();
       DailyQuestPanel planets = home.mainFrame1.getDailyQuestsContainer().getPlanets();
       
       math.getContent().setText(dailyQuests[0]);
       science.getContent().setText(dailyQuests[1]);
       geography.getContent().setText(dailyQuests[2]);
       planets.getContent().setText(dailyQuests[3]);
       
       //
       // STILL NEED TO ADD FUNCTIONALITY TO CHECK IF DAILY QUEST IS COMPLETE
       //
       
        //////////////////////////////////////////////////////////////////////
        ////            LOGIC FOR DETAILS SECTION HERE                    ////
        //////////////////////////////////////////////////////////////////////
        
        home.mainFrame1.getCenterPanel().getDetailsPanel().getYourNameLabel().setText(this.name);
        
        home.mainFrame1.getCenterPanel().getDetailsPanel().initializeGoals();
        
    }
    
    private void checkAns(String ansr, Home home) {
        Component[] components = home.mainFrame1.getCenterPanel().getQotdPanel().getButtons().getComponents();
        JLabel qotdPanel = home.mainFrame1.getCenterPanel().getQotdPanel().getQuestion();
        //Remove all the action listeners so the user can't guess again
        for(Component comp : components) {
            if(comp instanceof JButton) {
                JButton button = (JButton) comp;
                for (ActionListener listener : button.getActionListeners()) {
                    button.removeActionListener(listener);
                     
                }
            }
        }
       // System.out.println("ans: "+ansr);
       // System.out.println("ans 2: "+ans[1]);
        //If correct
        if(ansr.equals(this.qotd[1])) {
             qotdPanel.setBackground(Color.green);
        } //If incorrect
        else {
            qotdPanel.setBackground(Color.red);
        }
  
    }
    
    public void setGoalCount(int goal, int subject) {
         if (subject >= 0 && subject <=4) {
             goalCount[subject] = goal;
         } 
         else {
           //  System.out.println("wrong subject int passed");
         }
    }
    
    public void setGoals(String subject, Home home) {
       //home.mainFrame1.getCenterPanel().getDetailsPanel().
    }
//Getters
    public String getName() {
        return name;
    }

    public String getDir() {
        return dir;
    }

    public Maths getMaths() {
        return this.maths;
    }

    public Science getScience() {
        return science;
    }

    public Geography getGeography() {
        return geography;
    }

    public Planets getPlanets() {
        return planets;
    }
    
    
    
}
//Set up filepath to .sr for saving the object instance
//Create method for saving