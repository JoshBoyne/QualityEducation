/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import GUIComponents.DailyQuestPanel;
import GUIComponents.MainFrame;
import GUIComponents.QotdPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
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
   
    private static final long serialVersionUID = 123456789L;
    private String name;
    private String password;
    private boolean loggedIn = false;
    private String dir;//User's personal directory for storing all their data.
    String[] qotd;
    String[] ans;
    int[] logins;
    String[] dailyQuests;
    int[] questStatus = new int[] {0, 0, 0, 0};
    int[] goalCount = new int[] {0, 0, 0, 0};
    int[] subjectCount = new int[] {0, 0, 0, 0};
   
    
    public Account(String name, String pass) throws IOException {
       
        this.name = name;
        this.password = pass;
        dir = IOHandler.makeAccountDir(name);
        login();
    }
    
    public void login() throws IOException {
        this.loggedIn = false;
        System.out.println("debugging login in account");
        loggedIn = IOHandler.dailyLogin(new File(dir), this);
    }
    
    public void saveState() throws IOException {
        IOHandler.saveState(this);
    }
    
    public void updateUI(Home home) {
        //Updating the question of the day
        home.mainFrame1.getCenterPanel().getQotdPanel().getQuestion().setText(qotd[0]);
        
        //Updating the awnser buttons
        Component[] components = home.mainFrame1.getCenterPanel().getQotdPanel().getButtons().getComponents();

        // Loop through components and cast to JButton if they are JButton instances
        for (int i = 0; i < components.length && i < ans.length; i++) {
            if (components[i] instanceof JButton) {
                JButton btn = (JButton) components[i]; // Cast the component to JButton
                btn.setText(ans[i]); // Set the button's text
            }
        }
        JButton correctBtn = (JButton) components[components.length-1];
        correctBtn.setText(qotd[1]);
        
        // Extract button text into a list
        ArrayList<String> buttonTexts = new ArrayList<>();
        for (Component comp : components) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                buttonTexts.add(button.getText());
            }
        }

        // Shuffle the text
        Collections.shuffle(buttonTexts);

        // Reassign shuffled text to the buttons
        int index = 0;
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
                    //  System.out.println("Button clicked: " + button.getText());
                      checkAns(button.getText(), home);
                  }
              });
            }
        }
        
        //////////////////////////////////////////////////////////////////////
        ////            LOGIC FOR THE STREAK BOXES HERE                   ////
        //////////////////////////////////////////////////////////////////////
        
        //logins contains 7 entries. For a 0, user did not log in. For a 1, user did log in. 
        home.mainFrame1.getCenterPanel().getStreakContainer().setColors(logins);
        
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
//Getters
    public String getName() {
        return name;
    }

    public String getDir() {
        return dir;
    }
    
    
    
}
//Set up filepath to .sr for saving the object instance
//Create method for saving