/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Owen
 */
public class Questions {
    
    public static Map<String, String> questions = new HashMap<>();
    
    // Maps for daily quests for different topics
    public static Map<String, Boolean> dailyMathsQuest = new HashMap<>();
    public static Map<String, Boolean> dailyScienceQuest = new HashMap<>();
    public static Map<String, Boolean> dailyGeographyQuest = new HashMap<>();
    public static Map<String, Boolean> dailyPlanetsQuest = new HashMap<>();
    
    public Questions() {
        questions.put("What is the default value of an int variable in Java?", "0");
        questions.put("Which method is used to start a thread in Java?", "start()");
        questions.put("What is the size of a char in Java?", "2 bytes");
        questions.put("What keyword is used to define a constant in Java?", "final");
        questions.put("Which method is used to compare two strings in Java?", "equals()");
        questions.put("What is the default value of a boolean variable in Java?", "false");
        questions.put("Which collection class is used to store unique elements in Java?", "HashSet");
        questions.put("What is the purpose of the 'super' keyword in Java?", "To refer to the superclass");
        questions.put("What is the default value of a reference variable in Java?", "null");
        questions.put("Which method is used to get the length of an array in Java?", "length");
        
        // Populate daily quests for each topic
        dailyMathsQuest.put("<html><body style='width: 100%; text-align: center;'>Complete the daily maths quiz</body></html>", false);
        dailyScienceQuest.put("<html><body style='width: 100%; text-align: center;'>Complete the daily science quiz</body></html>", false);
        dailyGeographyQuest.put("<html><body style='width: 100%; text-align: center;'>Complete the daily geography quiz</body></html>", false);
        dailyPlanetsQuest.put("<html><body style='width: 100%; text-align: center;'>Complete the daily planets quiz</body></html>", false);

    }
    
    private void populateQuestions() {
        questions.clear();
        questions.put("What is the default value of an int variable in Java?", "0");
        questions.put("Which method is used to start a thread in Java?", "start()");
        questions.put("What is the size of a char in Java?", "2 bytes");
        questions.put("What keyword is used to define a constant in Java?", "final");
        questions.put("Which method is used to compare two strings in Java?", "equals()");
        questions.put("What is the default value of a boolean variable in Java?", "false");
        questions.put("Which collection class is used to store unique elements in Java?", "HashSet");
        questions.put("What is the purpose of the 'super' keyword in Java?", "To refer to the superclass");
        questions.put("What is the default value of a reference variable in Java?", "null");
        questions.put("Which method is used to get the length of an array in Java?", "length");

    }
    
    int qotd;
    public String[] getQOTD() {
        List<Map.Entry<String, String>> entryList = new ArrayList<>(questions.entrySet());
        qotd = Styles.RngGenerator(entryList.size());
        Map.Entry<String, String> q = entryList.get(qotd);
        System.out.println("Question: "+q.getKey());
        System.out.println("Ans: "+q.getValue());
        return new String[] {q.getKey(), q.getValue()};
    }
    
    public String[] getPossibleAns() {
        List<Map.Entry<String, String>> entryList = new ArrayList<>(questions.entrySet());
        String[] ans = new String[3];
        for(int i = 0; i < 3; i++) {
            int index = Styles.RngGenerator(entryList.size());
            //Rerolling if the q selected is the qotd
            while(index == qotd) {
                index = Styles.RngGenerator(entryList.size());
            }
            ans[i] = entryList.get(index).getValue();
            entryList.remove(index);
        }
        
       //Resseting the questions removed from the list
       populateQuestions();
        
        return ans;
    }
    
    //////////////////////////////////////////////////////////////
    ////                    DAILY QUESTS                      ////
    //////////////////////////////////////////////////////////////
    
    public String[] getDailyQuests() {
        // Create an array to hold the quest strings
        String[] dailyQuests = new String[4];
        
        // Add one quest from each topic map to the array
        dailyQuests[0] = dailyMathsQuest.keySet().iterator().next(); // Maths
        dailyQuests[1] = dailyScienceQuest.keySet().iterator().next(); // Science
        dailyQuests[2] = dailyGeographyQuest.keySet().iterator().next(); // Geography
        dailyQuests[3] = dailyPlanetsQuest.keySet().iterator().next(); // Planets
        
        return dailyQuests;
    }

}
