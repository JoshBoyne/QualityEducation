package GUIComponents;

import javax.swing.*;
import java.awt.*;

//Custom JButton with some adjusted visuals.
public class CustomButton extends JButton {

    //Variables
    private Color color;
    
    //Constructor
    public CustomButton(String text) {
        super(text);//Pass button.setText content
        this.setPreferredSize(new Dimension(200, 100));
        this.color = Color.black;
    }
    
    //Overriding the paint component method to change button appearance.
    @Override
    protected void paintComponent(Graphics g) {
        
        //Creating Graphics2D object from g for more functionality.
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//Makes the rounded corners look better

        //Drawing the button more rounded.
        g2.setColor(color);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 3, 3);

        //Get the buttons text content/
        String text = getText();

        //Makes sure there is text
        if (text != null && !text.isEmpty()) {
            //Formatting font and drawing text
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getHeight();
            g2.setColor(Color.white);
            g2.drawString(text, (getWidth() - textWidth) / 2, (getHeight() + textHeight / 2) / 2 - 2);//Math to center the drawing of the text
        }
        g2.dispose();
    }
    
    //Method for changing the color of the button.
    public void setColor(Color clr) {
        this.color = clr;
        this.revalidate();
        this.repaint();
    }

}
