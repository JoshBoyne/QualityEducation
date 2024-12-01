/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prototypetest;

import Main.Account;
import Main.IOHandler;
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
                            IOHandler.saveState(home.user);
                            System.out.println("Save before close.");
                        } catch (IOException ex) {
                            Logger.getLogger(PrototypeTest.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        frame.dispose();
                    }
                });


           
                

                // Set the frame to pref size
                frame.pack();

                
                frame.setVisible(true);
                
                /*
                try {
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
