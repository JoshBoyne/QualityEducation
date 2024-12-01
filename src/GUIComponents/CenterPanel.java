package GUIComponents;

import Main.Styles;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

public class CenterPanel extends JPanel {
Dimension dimensions = new Dimension(595, 490);
private StreakContainer streakContainer;
private QotdPanel qotdPanel;
private DetailsPanel detailsPanel;
    public CenterPanel(MainFrame home) {
        //Formatting Container Panel
        this.setPreferredSize(dimensions);
        this.setMaximumSize(dimensions);
        this.setBackground(Styles.greyBackground);
        this.setLayout(new BorderLayout());

        //Adding Question of the Day Panel for BorderLayout.North
        qotdPanel = new QotdPanel();
        this.add(qotdPanel, BorderLayout.NORTH);

        //Adding center panel for streak in BorderLayout.Center
        JPanel temp = new JPanel();
        temp.setPreferredSize(new Dimension(595, 30));
        temp.setMaximumSize(new Dimension(595, 30));
       // temp.setBackground(Color.blue);
        streakContainer = new StreakContainer();
        temp.add(streakContainer);
        this.add(temp, BorderLayout.CENTER);



        //South Panel
        //Account Details
        detailsPanel = new DetailsPanel(home);
        this.add(detailsPanel, BorderLayout.SOUTH);
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
       // this.setBorder(new LineBorder(Color.black, 2));
        System.out.println(this.getSize());
    }
    
    public QotdPanel getQotdPanel() {
        return this.qotdPanel;
    }
    public StreakContainer getStreakContainer() {
        return this.streakContainer;
    }
    public DetailsPanel getDetailsPanel() {
        return this.detailsPanel;
    }
}
