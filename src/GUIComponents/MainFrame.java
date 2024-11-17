package GUIComponents;

import javax.swing.*;
import java.awt.*;
import prototypetest.Home;

public class MainFrame extends JPanel {
    public Home restOfApp;
    public MainFrame(Home home) {
        this.restOfApp = home;
        this.setSize(new Dimension(920, 550));
        this.setLayout(new BorderLayout());

        this.add(new GUIHeader(), BorderLayout.NORTH);

        this.add(new DailyQuestsContainer(), BorderLayout.EAST);
        this.add(new CenterPanel(), BorderLayout.CENTER);

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
    
    public void test() {
        
    }

}

