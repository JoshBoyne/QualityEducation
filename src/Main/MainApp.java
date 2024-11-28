package Main;

import GUIComponents.MainFrame;

import javax.swing.*;
import java.awt.*;

public class MainApp {

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(new Dimension(920, 550));
        //frame.add(new MainFrame());
    }
}
