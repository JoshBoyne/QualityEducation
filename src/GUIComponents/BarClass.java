package GUIComponents;

import javax.swing.*;
import java.awt.*;

public class BarClass extends JPanel {

    public BarClass() {
        this.setBackground(Color.green);
        this.setPreferredSize(new Dimension(70, 20));
        this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    }
}
