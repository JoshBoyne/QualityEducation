package GUIComponents;

import Main.Styles;
import javax.swing.*;
import java.awt.*;

//Container for Daily Quest panels
public class DailyQuestsContainer extends JPanel {

    //Variables
    private Dimension dimensions = new Dimension(225, 540);
    private DailyQuestPanel maths, science, geography, planets; 
    private JPanel containerPanel;
    
    //Constructor
    public DailyQuestsContainer() {

    //Configuring this panel
    this.setPreferredSize(dimensions);
    this.setBackground(Styles.greyBackground);
    this.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 20));
    
    //Sub container to get around layout inheritance
    containerPanel = new JPanel();
    containerPanel.setPreferredSize(dimensions);
    containerPanel.setBackground(Styles.greyBackground);
    BoxLayout thisLayout = new BoxLayout(containerPanel, BoxLayout.Y_AXIS);
    containerPanel.setLayout(thisLayout);
    containerPanel.add(Box.createVerticalStrut(15));

    //Initializing each Daily quest panel
    //Maths
    maths = new DailyQuestPanel();
    maths.setAlignmentX(Component.LEFT_ALIGNMENT);
    containerPanel.add(maths);
    containerPanel.add(Box.createVerticalStrut(10));

    //Science
    science = new DailyQuestPanel();
    science.setAlignmentX(Component.LEFT_ALIGNMENT);
    containerPanel.add(science);
    containerPanel.add(Box.createVerticalStrut(10));

    //Geography
    geography = new DailyQuestPanel();
    geography.setAlignmentX(Component.LEFT_ALIGNMENT);
    containerPanel.add(geography);
    containerPanel.add(Box.createVerticalStrut(10));

    //Planets
    planets = new DailyQuestPanel();
    planets.setAlignmentX(Component.LEFT_ALIGNMENT);
    containerPanel.add(planets);
    containerPanel.add(Box.createVerticalStrut(10));

    this.add(containerPanel);
    
    }    

    //Getters
    public DailyQuestPanel getMaths() {
        return maths;
    }

    public DailyQuestPanel getScience() {
        return science;
    }

    public DailyQuestPanel getGeography() {
        return geography;
    }

    public DailyQuestPanel getPlanets() {
        return planets;
    }
    
    public JPanel getContainerPanel() {
        return this.containerPanel;
    }
}
