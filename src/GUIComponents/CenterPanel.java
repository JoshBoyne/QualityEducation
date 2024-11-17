package GUIComponents;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {
Dimension dimensions = new Dimension(595, 100);
    public CenterPanel() {
        //Formatting Container Panel
        this.setPreferredSize(dimensions);
        this.setBackground(Color.white);
        this.setLayout(new BorderLayout());

        //Adding Question of the Day Panel for BorderLayout.North
        this.add(new QotdPanel(), BorderLayout.NORTH);

        //Adding center panel for streak in BorderLayout.Center
        this.add(new StreakContainer(), BorderLayout.CENTER);



        //South Panel
        //Account Details
        this.add(new DetailsPanel(), BorderLayout.SOUTH);
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
    }
}
