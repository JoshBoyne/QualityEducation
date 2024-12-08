package Main;

import java.awt.Color;
import java.util.Random;

//Global class for globally needed variables
public class Styles {
        
    //Colors
    public static Color greyBackground = new Color(241, 240, 240);
    public static Color secondarySalmon = new Color(255, 102, 102);
    public static Color secondaryGrey = new Color(153, 153, 153);
    public static Color headerColor = new Color(51, 51, 51);
    public static Account user;
    
    //RNG Generator
    public static int RngGenerator(int max) {
        Random rng = new Random();     
        return rng.nextInt(max);
    }
    
    //Global Account object
    public static void setUser(Account user) {
        Styles.user = user;
    }
}
