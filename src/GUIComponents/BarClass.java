package GUIComponents;

import Main.Styles;
import javax.swing.*;
import java.awt.*;

public class BarClass extends JPanel {

    public BarClass() {
        this.setBackground(Styles.secondarySalmon);
        this.setPreferredSize(new Dimension(70, 70));
        this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    }
}
