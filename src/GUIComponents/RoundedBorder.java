package GUIComponents;

import javax.swing.border.AbstractBorder;
import java.awt.*;

//Class for having a rounded border on components
public class RoundedBorder extends AbstractBorder {
    
    //Variables
    private int radius;

    //Constructor - takes radius desire of corners
    public RoundedBorder(int radius) {
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets inset) {
        inset.top = 10;
        inset.left = 10;
        inset.bottom = 10;
        inset.right = 10;
        return inset;
    }
}
