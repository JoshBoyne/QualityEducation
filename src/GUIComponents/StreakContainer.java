package GUIComponents;

import Main.Styles;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//Container panel for the streak panels
public class StreakContainer extends JPanel {
    
    //Variables
    private ArrayList<BarClass> streakBars = new ArrayList<>();
    
    //Constructor
    public StreakContainer() {
        
        //Configuring this panel
        this.setBackground(Styles.greyBackground);
        this.setLayout(new GridBagLayout());
        this.setMaximumSize(new Dimension(595, 130));
        this.setPreferredSize(new Dimension(595, 130));
        this.setMinimumSize(new Dimension(595, 130));
        
        //Gridbag Constaints
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(1, 5, 1, 5);
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weighty = 0.0;

        //Adding 7 Streak Bar panels
        for(int i = 0; i<7; i++) {
            int y = 0;
            constraints.gridx = i;
            constraints.gridy = y;
            BarClass bar = new BarClass();
            this.add(bar, constraints);
            streakBars.add(bar);
        }
    }
    
    //Sets background of bar to green/red depending on login. Called from Account.UpdateUI
    public void setColors(int[] logins) {
        for(int i = 0; i<7; i++) {
            if(logins[i] == 1) {
                streakBars.get(i).setBackground(Color.green);
            }
        }
    }
    
    //Getters
    public ArrayList<BarClass> getStreakBars() {
        return this.streakBars;
    }
}
