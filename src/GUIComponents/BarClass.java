package GUIComponents;

import Main.Styles;
import javax.swing.*;
import java.awt.*;

public class BarClass extends JPanel {
    private JLabel text;
    public BarClass() {
        this.setBackground(Styles.secondarySalmon);
        this.setPreferredSize(new Dimension(70, 70));
        this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        this.setLayout(new BorderLayout());
        text = new JLabel();
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setVerticalAlignment(SwingConstants.CENTER);
        this.add(text, BorderLayout.CENTER);
    }
    
    public void setText(String date) {
        Character c = date.charAt(0);
        if (date.length() == 2) {
            c = date.charAt(1);
        } 
        System.out.println("character: "+c);
        String suffix = "";
        if(Integer.parseInt(date) > 3 && 3 <= 20) {
            suffix = "th";
        } 
        else {
            switch(c) {
                case '1':
                    suffix = "st";
                    break;
                 case '2':
                    suffix = "nd";
                    break;       
                case '3':
                    suffix = "rd";
                    break;
                default:
                    suffix = "th";
                    break;
            }
        }
        text.setText(date+suffix);
    }
}
