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
    ArrayList<String> loginDates = new ArrayList<>(); // User's logins in an Array. Used for calculating login streak colors.

    // Dynamically determine the project's base directory
    File projectBaseDir = new File("").getAbsoluteFile(); // Base directory of the project
    dir = new File(projectBaseDir, "src/Users/Owen"); // Adjust relative path based on project structure

    // Ensure the directory exists
    if (!dir.exists() || !dir.isDirectory()) {
        throw new IOException("Directory does not exist: " + dir.getAbsolutePath());
    }

    // Getting the user's login file
    File loginFile = new File(dir, "login.txt");
    BufferedReader reader = new BufferedReader(new FileReader(loginFile));
    BufferedWriter writer = new BufferedWriter(new FileWriter(loginFile, true));

    // Get the current date
    LocalDate currentDate = LocalDate.now();
    String today = currentDate.toString();

    // Read the existing login dates into the ArrayList
    String line;
    boolean alreadyLoggedIn = false;
    while ((line = reader.readLine()) != null) {
        loginDates.add(line);
        if (line.equals(today)) {
            alreadyLoggedIn = true;
            return true;
        }
    }
    reader.close();

    // If the user hasn't logged in today, write the date to the file and update the list
    if (!alreadyLoggedIn) {
        //loginDates.add(today); // Add today to the in-memory list
        //writer.write(today);
        //writer.newLine();
        //writer.close();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Refresh QOTD. These are used within the updateUI method of the Account class, and come from the Questions class. Questions -> IOHandler -> Account
    Styles.setUser(user); // Setting global user reference
    user.qotd = q.getQOTD(); // Loading QOTD and its answer into the user.
    user.ans = q.getPossibleAns(); // Loading 3 other incorrect answers into the user.
    System.out.println("debugging in io handler 110, "+user.qotdAwnsered);
    user.qotdAwnsered = false;
    System.out.println("debugging in io handler 113, "+user.qotdAwnsered);
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Refresh Daily Quests
    user.dailyQuests = q.getDailyQuests();
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Update Streak
    LocalDate startOfStreak = currentDate.minusDays(6); // Start checking 6 days before today
    int[] loginBools = new int[7]; // Array to store login status for the past 7 days

    // Iterate through the past 7 days
    for (int i = 0; i < 7; i++) {
        String dateToCheck = startOfStreak.plusDays(i).toString(); // Get the date as a string
        if (loginDates.contains(dateToCheck)) { // Check if the date is in loginDates
            loginBools[i] = 1; // Mark as logged in
        } else {
            loginBools[i] = 0; // Mark as missed login
        }
    }

    user.logins = loginBools; // Pass the bools back to the user

    // Debugging: Print the results for verification
   // for (int i = 0; i < loginBools.length; i++) 
        //System.out.println("Date: " + startOfStreak.plusDays(i) + " Login: " + loginBools[i]);

    return true; // Return true once the method finishes executing
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


