package GUIComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//Daily Quest Panel
public class DailyQuestPanel extends JPanel {

    //Variables
    private Dimension dimensions = new Dimension(200, 90);
    private JLabel content;
    private JLabel status;
    
    //Constructor
    public DailyQuestPanel() {
        
        //Configure this panel
        this.setPreferredSize(dimensions);
        this.setBackground(Color.white);
        this.setBorder(new RoundedBorder(10));
        this.setMaximumSize(dimensions);
        this.setMinimumSize(dimensions);
        this.setLayout(new GridLayout(2, 1)); // 2 rows, 1 column

        //Heading label
        content = new JLabel("Daily Quest", SwingConstants.CENTER);
        this.add(content);

        //Status label - if the quest is done
        status = new JLabel("0/1", SwingConstants.CENTER);
        this.add(status);
    }

    //Getters
    public JLabel getContent() {
        return this.content;
    }
    
    public JLabel getStatus() {
        return this.status;
    }
}
