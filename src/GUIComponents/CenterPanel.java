package GUIComponents;

import Main.Styles;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

public class CenterPanel extends JPanel {
Dimension dimensions = new Dimension(595, 490);
    public CenterPanel() {
        //Formatting Container Panel
        this.setPreferredSize(dimensions);
        this.setMaximumSize(dimensions);
        this.setBackground(Styles.greyBackground);
        this.setLayout(new BorderLayout());

        //Adding Question of the Day Panel for BorderLayout.North
        this.add(new QotdPanel(), BorderLayout.NORTH);

        //Adding center panel for streak in BorderLayout.Center
        JPanel temp = new JPanel();
        temp.setPreferredSize(new Dimension(595, 30));
        temp.setMaximumSize(new Dimension(595, 30));
       // temp.setBackground(Color.blue);
        temp.add(new StreakContainer());
        this.add(temp, BorderLayout.CENTER);



        //South Panel
        //Account Details
        this.add(new DetailsPanel(), BorderLayout.SOUTH);
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
       // this.setBorder(new LineBorder(Color.black, 2));
        System.out.println(this.getSize());
    }
}
