/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prototypetest;

import Main.Account;
import Main.IOHandler;
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

            
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

           
                frame.add(new Home());

                // Set the frame to pref size
                frame.pack();

                
                frame.setVisible(true);
                
                try {
                    Account test = new Account("a", "b");
                } catch (IOException ex) {
                    Logger.getLogger(PrototypeTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
