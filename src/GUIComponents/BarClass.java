package GUIComponents;

import Main.Styles;
import javax.swing.*;
import java.awt.*;

//This class is the individual panels that represent each daily login in the last seven days. 
public class BarClass extends JPanel {
    
    //Variable Decleration
    private JLabel text;
    
    //Constructor.
    public BarClass() {
        
        //Configuring this panel
        this.setBackground(Styles.secondarySalmon);
        this.setPreferredSize(new Dimension(70, 70));
        this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        this.setLayout(new BorderLayout());
        
        //Label for date information
        text = new JLabel();
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setVerticalAlignment(SwingConstants.CENTER);
        
        //Adding component
        this.add(text, BorderLayout.CENTER);
    }
    
    //Updated in Account.UpdateUI. There are 7 instances of this class, each passed a different date as string - only passes the day section.
    public void setText(String date) {
        
        Character c = date.charAt(0);//Get the first digit of the date
        
        if (date.length() == 2) {//If the date has 2 digits for the day, get the second digit - so we can compare suffix.
            c = date.charAt(1);
        } 
        
        String suffix = "";
        
        if(Integer.parseInt(date) > 3 && 3 <= 20) {//All digits between 4 and 20 (inclusive) end with the th suffix.
            suffix = "th";
        } 
        else {//Otherwise, check the last digit and apply the correct suffix.
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
