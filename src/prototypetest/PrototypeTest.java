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
<<<<<<< HEAD
              
                JFrame frame = new JFrame("Home Panel");

            
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

           
                frame.add(new Home());

                // Set the frame to pref size
                frame.pack();

                
=======
                // Create a JFrame to hold the JPanel
                JFrame frame = new JFrame("Home Panel");

                // Set the default close operation
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Add the Home JPanel to the frame
                frame.add(new Home());

                // Set the frame size to match the JPanel's preferred size
                frame.pack();

                // Make the frame visible
>>>>>>> f03478a (Initial commit for PrototypeTest)
                frame.setVisible(true);
            }
        });
    }
}
