package GUIComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DailyQuestPanel extends JPanel implements MouseListener {

    Dimension dimensions = new Dimension(200, 90);
    private JLabel content;
    private JLabel status;
    
    public DailyQuestPanel() {
        this.setPreferredSize(dimensions);
        this.setBackground(Color.white);
        this.setBorder(new RoundedBorder(10));
        this.setMaximumSize(dimensions);
        this.setMinimumSize(dimensions);
        this.addMouseListener(this);

        // Set GridLayout with 1 column and 2 rows
        this.setLayout(new GridLayout(2, 1)); // 2 rows, 1 column

        // Create the first label (centered in its cell)
        content = new JLabel("Daily Quest", SwingConstants.CENTER); // Center text inside the label
        this.add(content); // Add the label to the first row

        // Create the second label (centered in its cell)
        status = new JLabel("0/1", SwingConstants.CENTER); // Center text inside the label
        this.add(status); // Add the second label to the second row
    }




    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setBackground(Color.blue);
        System.out.println("enetered");
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        this.setBackground(Color.white);
    }
    
    public JLabel getContent() {
        return this.content;
    }
    
    public JLabel getStatus() {
        return this.status;
    }
}
