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
    
    //Variable Decleration
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
    
    
    //For initializing new accounts so they have a folder and files. Takes the user's name as an arguemnt.
    public static String makeAccountDir(String name) throws IOException {
        
        //Putting together the user's own directory path.
        String dir = System.getProperty("user.dir");//This gets the project root path.
        String dir1 = dir+File.separator+"src"+File.separator+"Users"+File.separator+name;//This gets root/src/Users/ + the user's name.
        File userDir = new File(dir1);//Taking the user's own directory path and making it a file
        
        //Making the user's directory, and their login log file. 
        if(userDir.mkdirs()) {//If the dir doesn't already exist

            File loginLog = new File(dir1+File.separator+"login.txt");//Login txt file that's in the user's new dir
            loginLog.createNewFile();//Creating that login txt file
            
            //Writer for adding this login to the user's login text file.
            BufferedWriter writer = new BufferedWriter(new FileWriter(loginLog));
            //writer.write("Start of login");
            writer.close();
            return dir1;//Returns the user's directory path
        } 
        System.out.println("IOHandler line 56 - Directory already exists");
        return null;
    }
    
    
    //Method to control the logic for when a user logs in. Will check if the user has already logged in today. If yes - regular login. If no - refresh daily data. Recieves the user's file path and Account object. 
    public static boolean dailyLogin(File dir, Account user) throws IOException {
      
        ArrayList<String> loginDates = new ArrayList<>();//User's logins in an Array. Used for calculating login streak colors.
        
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
            if(line.equals(today)) {//User has alread logged in, we will return true and stop executing this method.
                return true;
            } 
        }
        
        //This while loop should always exectue 'return true' in the event the user has logged in today, existing this full method and stopping the below code from triggering. 
        /////////////////////////////////////////////////////////////////////////////////////
        // ALL OF THIS CODE EXECUTES WHEN THE USER HAS LOGGED IN FOR THE FIRST TIME TODAY. //
        /////////////////////////////////////////////////////////////////////////////////////

        //Logging today's login
        writer.write(today);
        writer.newLine();
        writer.close();
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
        
        //Refresh QOTD. These are used within the updateUI method of the Account class, and come from the Questions class. Questions -> IOHandler -> Account
        Styles.setUser(user);//Setting global user reference
        user.qotd = q.getQOTD();//Loading qotd and its answer into the user. 
        user.ans = q.getPossibleAns();//Loading 3 other incorrect answers into the user. 
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Refresh Daily Quests
        user.dailyQuests = q.getDailyQuests();
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////          
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Update Streak
        LocalDate startOfStreak = currentDate.minusDays( 6);//Getting the date 7 days from today (inclusive).
        String startOfStreakS = startOfStreak.toString();//Converting the date to string for each operations.
        int startLine = Math.max(0, loginDates.size() - 6);//Getting the first line to start checking the user's login file. Set with .max for the edge case of the user having less than 7 logins. 
        int[] loginBools = new int[]{0, 0, 0, 0, 0, 0, 0};//Because of the Account class being seriazable, I am storing the login status in this array, with 0 being false and 1 being true.
        
        //Iterating through the last 7 days, and comparing the dates against the user's login text file.
        dateLoop:
        for(int i = 0; i<6; i++) {//Iterate through the last 7 days
            for(String login : loginDates) {//Iterate through all logins in the loginDates array 
                if(login.equals(startOfStreakS)) {//Logged in on this date
                    startOfStreak = startOfStreak.plusDays(1);
                    startOfStreakS = startOfStreak.toString();
                    loginBools[i] = 1;
                    continue dateLoop; //Proceed to next date 
                }
            }
            startOfStreak = startOfStreak.plusDays(1);
            startOfStreakS = startOfStreak.toString();
        }    
        
        user.logins = loginBools;//Pass the bools back to the user.
        return true;
    }
    
    //Saving the state of an account object to a serial file to allow data to persist over sessions
    public static void saveState(Account user) throws FileNotFoundException, IOException {
        
        String path = user.getDir().toString();//Getting the user's directory.
        path += File.separator+user.getName()+".ser";//Adding the file extension to the user's name and creating the .ser file.
        
        //Getting the output stream open for the serial file
        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(path));
        stream.writeObject(user);//Saving the object
        System.out.println("User State Saved");
    }
    
    //Loading object state from serial file - ran via user's username, as that is what the dir and serial file should be saved under.
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


