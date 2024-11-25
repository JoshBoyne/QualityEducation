/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prototypetest;

import java.awt.Desktop;
import java.net.URI;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author jason
 */
public class RManager {
    
    
        // Method to attach click event to the label and open a link
    public void enableLinkLabel(JLabel linkLabel, String url) {
        linkLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openLink(url); //pass url as paramtr
            }
        });
    }

    // Method to open link on LearningP jason))
    private void openLink(String url) {
        try {
            URI uri = new URI(url); //url object
            if (Desktop.isDesktopSupported()) { //check if open browser feature is supported
                Desktop.getDesktop().browse(uri);
            } else {
                JOptionPane.showMessageDialog(null, "Desktop is not supported", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) { //debugging stack here 
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to open link: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
}
