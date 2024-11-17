package GUIComponents;

import javax.swing.*;
import java.awt.*;

public class StreakContainer extends JPanel {

    public StreakContainer() {
        this.setBackground(Color.white);
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;


        for(int i = 0; i<7; i++) {
            int x = i;
            int y = 0;
            constraints.gridx = x;
            constraints.gridy = y;
            this.add(new BarClass(), constraints);
            x++;
        }
    }
}
