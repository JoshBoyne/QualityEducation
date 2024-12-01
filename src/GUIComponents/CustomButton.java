package GUIComponents;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {

    public CustomButton(String text) {
        super(text);
        this.setPreferredSize(new Dimension(200, 100));

    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Found out about this line online

        // Draw the button background
        g2.setColor(Color.black);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 3, 3);

        // Get the button text
        String text = getText();

        // Check if the text is null before proceeding
        if (text != null && !text.isEmpty()) {
            // Draw the text
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getHeight();
            g2.setColor(Color.white);
            g2.drawString(text, (getWidth() - textWidth) / 2, (getHeight() + textHeight / 2) / 2 - 2);
        } else {
            // Optionally, you can draw a default message or do nothing
            // g2.drawString("Default Text", getWidth() / 2, getHeight() / 2);
        }

        g2.dispose();
    }

}
