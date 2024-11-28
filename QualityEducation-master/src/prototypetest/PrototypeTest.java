/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prototypetest;

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

           
                frame.add(new MainGUI());

                // Set the frame to pref size
                frame.pack();

                
                frame.setVisible(true);
            }
        });
    }
}
