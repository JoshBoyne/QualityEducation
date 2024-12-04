/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prototypetest;

import Main.Account;
import Main.IOHandler;
import Main.Styles;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author jason
 */
public class PrototypeTest { 

    /**
     * @param args the command line arguments
     */
  
    public static void main(String[] args) {
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              
                JFrame frame = new JFrame("Home Panel");
                Home home = new Home();
                frame.add(home);
                
                
                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        try {
                            IOHandler.saveState(Styles.user);
                            System.out.println("Save before close.");
                        } catch (IOException ex) {
                            
                            Logger.getLogger(PrototypeTest.class.getName()).log(Level.SEVERE, null, ex);
                            
                        } finally {
                            frame.dispose();
                        }
                        
                        
                    }
                });

                frame.pack();
                frame.setVisible(true);
                
                
                //This code will create a new user and save it. 
               /* try {
                    Account test = new Account("Owen", "b");
                    try {
                        IOHandler.saveState(test);
                    } 
                    catch (FileNotFoundException ex) {
                        Logger.getLogger(PrototypeTest.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                } 
                catch (IOException ex) {
                    Logger.getLogger(PrototypeTest.class.getName()).log(Level.SEVERE, null, ex);
                }*/
            }
        });
    }
}
//To do:
/* - Add time checker to change the welcome message. 
Set up account functionality. 
Fix goals UI. 
Add custom goal saving functionality. 
Set up login frame coloring. 

optional:
set color of correct button for qotd. 

*/