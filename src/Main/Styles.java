/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Owen
 */
public class Styles {
        
    public static Color greyBackground = new Color(241, 240, 240);
    public static Color secondarySalmon = new Color(255, 102, 102);
    public static Color headerColor = new Color(51, 51, 51);
    
    public static int RngGenerator(int max) {
        Random rng = new Random();
        
        return rng.nextInt(max);
    }
}
