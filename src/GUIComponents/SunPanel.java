package GUIComponents;

import Main.IOHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

//Panel to hold the sun image
public class SunPanel extends JPanel {
    
    //Variables
    private BufferedImage sun = IOHandler.loadImage("../Resources/sun.png");
   
    //Constructor
    public SunPanel() {
        this.setPreferredSize(new Dimension(50, 50));
        this.setMaximumSize(new Dimension(50, 50));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (sun != null) {
            g.drawImage(sun, 0, 0, 50, 50, this);
        } else {
            
            g.setColor(Color.RED);
            g.fillRect(0, 0, 50, 50); 
            g.setColor(Color.BLACK);
            g.drawString("Image not found", 5, 25);
        }
    }

}
