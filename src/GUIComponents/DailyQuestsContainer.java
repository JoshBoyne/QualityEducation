package GUIComponents;

import Main.Styles;
import javax.swing.*;
import java.awt.*;

public class DailyQuestsContainer extends JPanel {

    Dimension dimensions = new Dimension(225, 540);
    private DailyQuestPanel maths, science, geography, planets; 
    private JPanel containerPanel;
    public DailyQuestsContainer() {

        this.setPreferredSize(dimensions);
        this.setBackground(Styles.greyBackground);
        this.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 20));
        containerPanel = new JPanel();
        containerPanel.setPreferredSize(dimensions);
        containerPanel.setBackground(Styles.greyBackground);
        BoxLayout thisLayout = new BoxLayout(containerPanel, BoxLayout.Y_AXIS);
        containerPanel.setLayout(thisLayout);
        containerPanel.add(Box.createVerticalStrut(15));

        maths = new DailyQuestPanel();
        maths.setAlignmentX(Component.LEFT_ALIGNMENT);
        containerPanel.add(maths);
        containerPanel.add(Box.createVerticalStrut(10));

        science = new DailyQuestPanel();
        science.setAlignmentX(Component.LEFT_ALIGNMENT);
        containerPanel.add(science);
        containerPanel.add(Box.createVerticalStrut(10));

        geography = new DailyQuestPanel();
        geography.setAlignmentX(Component.LEFT_ALIGNMENT);
        containerPanel.add(geography);
        containerPanel.add(Box.createVerticalStrut(10));

        planets = new DailyQuestPanel();
        planets.setAlignmentX(Component.LEFT_ALIGNMENT);
        containerPanel.add(planets);
        containerPanel.add(Box.createVerticalStrut(10));
     

        this.add(containerPanel);
    
    }    

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
