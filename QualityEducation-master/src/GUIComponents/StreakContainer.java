package GUIComponents;

import Main.Styles;
import javax.swing.*;
import java.awt.*;

public class StreakContainer extends JPanel {

    public StreakContainer() {
        this.setBackground(Styles.greyBackground);
        this.setLayout(new GridBagLayout());
        this.setMaximumSize(new Dimension(595, 130));
        this.setPreferredSize(new Dimension(595, 130));
        this.setMinimumSize(new Dimension(595, 130));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(1, 5, 1, 5);
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weighty = 0.0;

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
