package Main;

import java.awt.List;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class IOHandler {
public static Questions q = new Questions();
    //Loading an image into the application
    public static BufferedImage loadImage(String path) {
        InputStream is = IOHandler.class.getResourceAsStream(path);//Get the image as a inputStream
        try {
            return ImageIO.read(is); //Read the input stream as an image and return it
        } catch (IOException e) {
        }
        return null;
    }
    
    //For initializing new accounts so they have a folder and files.
    //Takes the user's name as an arguemnt
    public static String makeAccountDir(String name) throws IOException {
        
        //Putting together the user's own directory path.
        String dir = System.getProperty("user.dir");//This gets the project root path.
        String dir1 = dir+File.separator+"src"+File.separator+"Users"+File.separator+name;//This gets root/src/Users/ + the user's name.
 
        //Taking the user's own directory path and making it a file
        File userDir = new File(dir1);
        
        //Making the user's directory, and their login log file. 
        if(userDir.mkdirs()) {//If the dir doesn't already exist
            //System.out.println(userDir);
            File loginLog = new File(dir1+File.separator+"login.txt");//Login txt file that's in the user's new dir
            loginLog.createNewFile();//Creating that login txt file
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(loginLog));
            //writer.write("Start of login");
            writer.close();
            return dir1;
        } 
        System.out.println("Directory already exists");
        return null;
    }
    
    //Method to control the logic for when a user logs in. 
    //Will check if the user has already logged in today. If yes - regular login. If no - refresh daily data
    public static boolean dailyLogin(File dir, Account user) throws IOException {
      
        
        ArrayList<String> loginDates = new ArrayList<>();
        
        //Getting the user's login file, and setting up reader and writer to interact with it. 
        File loginFile = new File(dir+File.separator+"login.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(loginFile, true));
        BufferedReader reader = new BufferedReader(new FileReader(loginFile));
        
        //Get the current date
        LocalDate currentDate = LocalDate.now();
        String today = currentDate.toString();
        
        //Get the last login date
        String line;
        while((line = reader.readLine()) != null) {
            loginDates.add(line);
           //System.out.println("Line: "+line);
            if(line.equals(today)) {
                //User has alread logged in, we will return true and stop executing this method
              
                return true;
            } 
        }
        
        /////////////////////////////////////////////////////////////////////////////////////
        // ALL OF THIS CODE EXECUTES WHEN THE USER HAS LOGGED IN FOR THE FIRST TIME TODAY. //
        /////////////////////////////////////////////////////////////////////////////////////
        
        //User has not logged in today - we have passed the while condition. 
      
       //writer.write(today);//Logging today's login
       //writer.newLine();
        writer.close();
        
        //Refresh QOTD
            
            user.qotd = q.getQOTD();//Loading qotd and its answer into the user. 
            user.ans = q.getPossibleAns();//Loading 3 other incorrect answers into the user. 
            //These are used within the updateUI method of the Account class, and come from the Questions class. Questions -> IOHandler -> Account
        
        //Refresh Daily Quests
            //Hashmap with IDs and Qs, then save the IDs to the account
            user.dailyQuests = q.getDailyQuests();
            //Passing daily quests to the user from the Questions class.
        
        //Update Streak
            //Get today's date, count back 7 lines in login file, get date 7 days ago, check each line against the date-7+i
           
        LocalDate startOfStreak = currentDate.minusDays( 7);
        //System.out.println("Start of streak: "+startOfStreak);
        String startOfStreakS = startOfStreak.toString();
        int startLine = Math.max(0, loginDates.size() - 7);
        //System.out.println("StartLine :"+startLine);
        
        int[] loginBools = new int[]{0, 0, 0, 0, 0, 0, 0};
        
        dateLoop:
        for(int i = 0; i<7; i++) {//Iterate through the last 7 days
            System.out.println("The outer date being checked: "+startOfStreakS);
            for(String login : loginDates) {//Iterate through all logins in the loginDates array 
                System.out.println("date being checked: "+login);
                
                if(login.equals(startOfStreakS)) {
                    //Logged in on this date
                    startOfStreak = startOfStreak.plusDays(1);
                    startOfStreakS = startOfStreak.toString();
                    loginBools[i] = 1;
                    System.out.println("logged In This Day");
                    continue dateLoop; //Proceed to next date 
                }
            }
            startOfStreak = startOfStreak.plusDays(1);
            startOfStreakS = startOfStreak.toString();
        }    
        //Pass the bools back to the user
        user.logins = loginBools;
        //Update user UI to iterate through the bools and streak containers, and change background color depending on 1 / 0.
        //IOHandler just gets teh data the user needs, and passes it to it, as well as taking care of the login function. Any visiaul updates are done in the Account Class updateUI method.
        
       // for(int s : loginBools)
            //System.out.println("logins: "+s);
        return true;
    }
    
    //Saving the state of an account object to a serial file to allow data to persist over sessions
    public static void saveState(Account user) throws FileNotFoundException, IOException {
        String path = user.getDir().toString();
        path += File.separator+user.getName()+".ser";
        
        //Getting the output stream open for the serial file
        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(path));
        stream.writeObject(user);
        System.out.println("User State Saved");
    }
    
    //Loading object state from serial file - ran via user's username, as that is what the dir and serial file should be saved under
    public static Account loadState(String username) throws FileNotFoundException, IOException, ClassNotFoundException {
        String dir = System.getProperty("user.dir");//This gets the project root path.
        String dir1 = dir+File.separator+"src"+File.separator+"Users"+File.separator+username;//This gets root/src/Users/ + the user's name.
    
        //Check if this dir exists
        
        //If not - username not correct, or user does not exist. 
        
        //If yes
        //Set global logged in Account OBJ to this serial obj. On login - globalAc = loadState(username);
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(dir1+File.separator+username+".ser"));
        return (Account) input.readObject();
    
    }
    
}

//Main to-do
/*
Create a save user and load user serialization function
Create a test user with logs
Ensure user's can be saved and loaded correctly. User save's on application close, if possible. And loads on application open. 
Create QOTD file.
Get QOTD to dynamically update, and only refersh each day - save QOTD ID to file's state. 
Get Daily quests to work - same method as QOTD. 
Get streak to update. Get the date, get the last seven days, check if a login exists for them, and style accordingly.

Two lines of logic -
Create Account

Login existing account
*/

