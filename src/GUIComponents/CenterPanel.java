package GUIComponents;

import Main.Styles;
import javax.swing.*;
import java.awt.*;


//This is the primary center panel in the main container's layout. It holds the QOTD, Streaks, and Details components.
public class CenterPanel extends JPanel {
    
    //Variables
    private Dimension dimensions = new Dimension(595, 490);
    private StreakContainer streakContainer;
    private QotdPanel qotdPanel;
    private DetailsPanel detailsPanel;
    
    //Constructor
    public CenterPanel(MainPanel home) {//Passing home for access to the rest of the app. 
        
        //Formatting this Panel
        this.setPreferredSize(dimensions);
        this.setMaximumSize(dimensions);
        this.setBackground(Styles.greyBackground);
        this.setLayout(new BorderLayout());

        //Adding Question of the Day Panel for BorderLayout.North
        qotdPanel = new QotdPanel();
        this.add(qotdPanel, BorderLayout.NORTH);

        //Container for streak in center section.
        JPanel cont = new JPanel();
        cont.setPreferredSize(new Dimension(595, 30));
        cont.setMaximumSize(new Dimension(595, 30));
        
        //New StreakContainer for BarClass Instances
        streakContainer = new StreakContainer();
        cont.add(streakContainer);
        this.add(cont, BorderLayout.CENTER);

        //DetailsPanel for account details in south section.
        detailsPanel = new DetailsPanel();
        this.add(detailsPanel, BorderLayout.SOUTH);
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
    }
    
    //Getters - for accessing parts of the GUI for dynamic updating - Account.UpdateUI.
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
