package GUIComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DailyQuestPanel extends JPanel implements MouseListener {

    Dimension dimensions = new Dimension(200, 90);

    public DailyQuestPanel() {

        this.setPreferredSize(dimensions);
        this.setBackground(Color.white);
        this.setBorder(new RoundedBorder(10));
        this.setMaximumSize(dimensions);
        this.setMinimumSize(dimensions);
        this.addMouseListener(this);
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
}
