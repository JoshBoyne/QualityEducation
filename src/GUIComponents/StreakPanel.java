package GUIComponents;

import Main.IOHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class StreakPanel extends JPanel {
    BufferedImage img = IOHandler.loadImage("../Resources/fire.png");
    public StreakPanel() {

        this.setBackground(Color.white);
        this.setMaximumSize(new Dimension(50, 50));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // Correct: Calls the superclass method for proper background rendering

        // Check if the image is loaded
        if (img != null) {
            // Draw the image at position (0, 0) with size 50x50
            g.drawImage(img, 0, 0, 50, 50, this);
        } else {
            // If the image is not loaded, show an error message or a placeholder
            g.setColor(Color.RED);
            g.fillRect(0, 0, 50, 50);  // Draw a red rectangle as a placeholder
            g.setColor(Color.BLACK);
            g.drawString("Image not found", 5, 25);  // Display an error message
        }
    }
}
