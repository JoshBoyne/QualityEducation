package GUIComponents;

import javax.swing.*;
import java.awt.*;

public class DailyQuestsContainer extends JPanel {

    Dimension dimensions = new Dimension(225, 430);

    public DailyQuestsContainer() {

        this.setPreferredSize(dimensions);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 20));
        JPanel containerPanel = new JPanel();
        containerPanel.setPreferredSize(dimensions);
        //containerPanel.setBackground(Color.BLACK);
        BoxLayout thisLayout = new BoxLayout(containerPanel, BoxLayout.Y_AXIS);
        containerPanel.setLayout(thisLayout);
        containerPanel.add(Box.createVerticalStrut(15));

        DailyQuestPanel quest1 = new DailyQuestPanel();
        quest1.setAlignmentX(Component.LEFT_ALIGNMENT);
        containerPanel.add(quest1);
        containerPanel.add(Box.createVerticalStrut(10));

        DailyQuestPanel quest2 = new DailyQuestPanel();
        quest2.setAlignmentX(Component.LEFT_ALIGNMENT);
        containerPanel.add(quest2);
        containerPanel.add(Box.createVerticalStrut(10));

        DailyQuestPanel quest3 = new DailyQuestPanel();
        quest3.setAlignmentX(Component.LEFT_ALIGNMENT);
        containerPanel.add(quest3);
        containerPanel.add(Box.createVerticalStrut(10));

        DailyQuestPanel quest4 = new DailyQuestPanel();
        quest4.setAlignmentX(Component.LEFT_ALIGNMENT);
        containerPanel.add(quest4);
        containerPanel.add(Box.createVerticalStrut(10));
        containerPanel.setBackground(Color.white);


        this.add(containerPanel);
    }
}
