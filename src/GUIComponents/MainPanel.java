package GUIComponents;

import Main.Styles;
import javax.swing.*;
import java.awt.*;
import prototypetest.Home;

//Primary panel in GUI. - The root of the GUI Heirarchy. 
public class MainPanel extends JPanel {
    
    //Variables
    public Home restOfApp;//Instance of Home to access the code of my project-mates. 
    private CenterPanel centerPanel;
    private DailyQuestsContainer dailyQuestsContainer;
    
    //Constructor
    public MainPanel(Home home) {
        
        //Configuring this panel
        this.restOfApp = home;
        this.setSize(new Dimension(920, 550));
        this.setMaximumSize(new Dimension(920, 550));
        this.setLayout(new BorderLayout());
        this.setBackground(Styles.greyBackground);
        
        //Adding the header - North
        this.add(new GUIHeader(), BorderLayout.NORTH);

        //Daily Quests - East
        dailyQuestsContainer = new DailyQuestsContainer();
        this.add(dailyQuestsContainer, BorderLayout.EAST);
        
        //Center Panel with an extra container for layout inheritance
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        centerPanel = new CenterPanel(this);
        panel.add(centerPanel);
        panel.setMaximumSize(new Dimension(595, 490));
        this.add(panel, BorderLayout.CENTER);
        
        //Some trimming - West
        JPanel west = new JPanel();
        west.setBackground(Styles.greyBackground);
        west.setPreferredSize(new Dimension(30, 550));
        this.add(west, BorderLayout.WEST);

        //Some trimming - South
        JPanel south = new JPanel();
        south.setBackground(Color.white);
        south.setPreferredSize(new Dimension(10, 10));
        this.add(south, BorderLayout.SOUTH);
        
        this.setVisible(true);

    }
    
    //Polymorphism example. Need this to allow the NETBEANS designer to show this panel in the design tab.
    public MainPanel() {
        
        this.setSize(new Dimension(920, 550));
        this.setLayout(new BorderLayout());
        
        this.add(new GUIHeader(), BorderLayout.NORTH);
        
        this.add(new DailyQuestsContainer(), BorderLayout.EAST);
        
        this.add(new CenterPanel(this), BorderLayout.CENTER);

        JPanel west = new JPanel();
        west.setBackground(Color.white);
        west.setPreferredSize(new Dimension(30, 550));
        this.add(west, BorderLayout.WEST);

        JPanel south = new JPanel();
        south.setBackground(Color.white);
        south.setPreferredSize(new Dimension(10, 10));
        this.add(south, BorderLayout.SOUTH);
        
        this.setVisible(true);
    }
    
    //Getters
    public CenterPanel getCenterPanel() {
        return this.centerPanel;
    }
    
    public DailyQuestsContainer getDailyQuestsContainer() {
        return this.dailyQuestsContainer;
    }

}

