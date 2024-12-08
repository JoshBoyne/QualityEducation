package Main;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//Class contains daily quests and questions for the user
public class Questions {
    
    //Variables
    public static Map<String, String> questions = new HashMap<>();//Map containing questions and awnsers
    
    //Maps for daily quests for different topics
    public static Map<String, Boolean> dailyMathsQuest = new HashMap<>();
    public static Map<String, Boolean> dailyScienceQuest = new HashMap<>();
    public static Map<String, Boolean> dailyGeographyQuest = new HashMap<>();
    public static Map<String, Boolean> dailyPlanetsQuest = new HashMap<>();
    
    //Constructor putting questions into the maps
    public Questions() {
        
        //Daily Questions
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
        
        //Daily Quests
        dailyMathsQuest.put("<html><body style='width: 100%; text-align: center;'>Complete the daily maths quiz</body></html>", false);
        dailyScienceQuest.put("<html><body style='width: 100%; text-align: center;'>Complete the daily science quiz</body></html>", false);
        dailyGeographyQuest.put("<html><body style='width: 100%; text-align: center;'>Complete the daily geography quiz</body></html>", false);
        dailyPlanetsQuest.put("<html><body style='width: 100%; text-align: center;'>Complete the daily planets quiz</body></html>", false);

    }
    
    //If needed to clear and repopulate question bank
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
    
    //Get question of the day - returns string array of question, awnser
    int qotd;//Index of question of the day
    public String[] getQOTD() {
        ArrayList<Map.Entry<String, String>> entryList = new ArrayList<>(questions.entrySet());//Array list of the q and a combo maps
        qotd = Styles.RngGenerator(entryList.size());//Random index
        Map.Entry<String, String> q = entryList.get(qotd);//Grabbing the q and a object
        return new String[] {q.getKey(), q.getValue()};
    }
    
    //Getting three more incorrect awnsers
    public String[] getPossibleAns() {
        List<Map.Entry<String, String>> entryList = new ArrayList<>(questions.entrySet());//Same thing again with the List of Map entries
        String[] ans = new String[3];//Array to return
        for(int i = 0; i < 3; i++) {//Run three times
            int index = Styles.RngGenerator(entryList.size());//New random index
            while(index == qotd) {//Rerolling if the q selected is the qotd
                index = Styles.RngGenerator(entryList.size());
            }
            ans[i] = entryList.get(index).getValue();
            entryList.remove(index);
        }
        
       //Resseting the questions removed from the list
       populateQuestions();
        
       return ans;
    }
    
    //Get daily quests for the user
    public String[] getDailyQuests() {
        //Create an array to hold the quest strings
        String[] dailyQuests = new String[4];
        
        //Add one quest from each topic map to the array
        dailyQuests[0] = dailyMathsQuest.keySet().iterator().next(); // Maths
        dailyQuests[1] = dailyScienceQuest.keySet().iterator().next(); // Science
        dailyQuests[2] = dailyGeographyQuest.keySet().iterator().next(); // Geography
        dailyQuests[3] = dailyPlanetsQuest.keySet().iterator().next(); // Planets
        
        return dailyQuests;
    }
}
