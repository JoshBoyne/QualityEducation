package GUIComponents;

import Main.Styles;
import javax.swing.*;
import java.awt.*;
import prototypetest.Home;

//what im doing - figuring out how to access teh daily quests panel from an instance of this

public class MainFrame extends JPanel {
    public Home restOfApp;
    private CenterPanel centerPanel;
    private DailyQuestsContainer dailyQuestsContainer;
    public MainFrame(Home home) {
        this.restOfApp = home;
        this.setSize(new Dimension(920, 550));
        this.setMaximumSize(new Dimension(920, 550));
        this.setLayout(new BorderLayout());
        this.setBackground(Styles.greyBackground);
        this.add(new GUIHeader(), BorderLayout.NORTH);

        dailyQuestsContainer = new DailyQuestsContainer();
        this.add(dailyQuestsContainer, BorderLayout.EAST);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        centerPanel = new CenterPanel(this);
        panel.add(centerPanel);
        panel.setMaximumSize(new Dimension(595, 490));
        this.add(panel, BorderLayout.CENTER);

        // Make the frame visible
        this.setVisible(true);

        JPanel west = new JPanel();
        west.setBackground(Styles.greyBackground);
        west.setPreferredSize(new Dimension(30, 550));
        this.add(west, BorderLayout.WEST);

        JPanel south = new JPanel();
        south.setBackground(Color.white);
        south.setPreferredSize(new Dimension(10, 10));
        this.add(south, BorderLayout.SOUTH);
        System.out.println(this.getSize());
    }
    
    public MainFrame() {
                this.setSize(new Dimension(920, 550));
        this.setLayout(new BorderLayout());

        this.add(new GUIHeader(), BorderLayout.NORTH);

        this.add(new DailyQuestsContainer(), BorderLayout.EAST);
        this.add(new CenterPanel(this), BorderLayout.CENTER);

        // Make the frame visible
        this.setVisible(true);

        JPanel west = new JPanel();
        west.setBackground(Color.white);
        west.setPreferredSize(new Dimension(30, 550));
        this.add(west, BorderLayout.WEST);

        JPanel south = new JPanel();
        south.setBackground(Color.white);
        south.setPreferredSize(new Dimension(10, 10));
        this.add(south, BorderLayout.SOUTH);
    }
    
    public CenterPanel getCenterPanel() {
        return this.centerPanel;
    }
    
    public DailyQuestsContainer getDailyQuestsContainer() {
        return this.dailyQuestsContainer;
    }

}

