package GUIComponents;

import Main.Styles;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.time.LocalTime;


//Header at the top of the GUI
public class GUIHeader extends JPanel {

    //Variables
    private Dimension dimensions = new Dimension(920, 50);

    //Constructor
    public GUIHeader() {
        
        //Configuring this panel
        this.setBackground(new Color(51, 51, 51));
        this.setPreferredSize(dimensions);
        this.setMaximumSize(dimensions);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(new LineBorder(Color.black, 2));
        
        //Container for back button label
        JPanel backContainer = new JPanel();
        backContainer.setMaximumSize(new Dimension(50, 50));
        backContainer.setPreferredSize(new Dimension(38, 50));
        backContainer.setBackground(new Color(255, 102, 102));
   
        //Back Button label
        JLabel BACKtoStart = new JLabel();
        BACKtoStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prototypetest/left-arrows (2).png"))); // NOI18N
        BACKtoStart.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BACKtoStartMouseClicked(evt);
            }
        });
        
        //Adding button to container
        backContainer.add(BACKtoStart);
        
        //Adding to this panel and spacing.
        this.add(backContainer);
        this.add(Box.createRigidArea(new Dimension(70, 0)));
        this.add(new SunPanel());

        //Welcome Message Container
        JPanel welcomeMsg = new JPanel();
        welcomeMsg.setBackground(Styles.headerColor);
        welcomeMsg.setForeground(Color.white);
        welcomeMsg.setMaximumSize(new Dimension(400, 50));
        welcomeMsg.setLayout(new BorderLayout());
        
        //Generate the greeting based on the time of day
        LocalTime currentTime = LocalTime.now();
        int currentHour = currentTime.getHour();
        String greeting;
        
        if(currentHour > 0 && currentHour < 12) {
            greeting = "Good Morning";
        } 
        else if (currentHour >= 12 && currentHour < 17) {
            greeting = "Good Afternoon";
        } 
        else if (currentHour >= 17 && currentHour <= 20) {
            greeting = "Good Evening";
        } 
        else if (currentHour > 20 && currentHour <= 24 ) {
            greeting = "Good Night";
        }
        else {
            greeting = "Welcome";
        }
        
        //Welcome message label
        JLabel welcomeMsgLbl = new JLabel("<html><span style='letter-spacing: 3px;'>"+greeting+"</span></html>");
        welcomeMsgLbl.setFont(new Font("Arial", Font.BOLD, 40));
        welcomeMsgLbl.setForeground(Color.white);
        welcomeMsgLbl.setHorizontalAlignment(SwingConstants.LEFT);
        welcomeMsgLbl.setVerticalAlignment(SwingConstants.CENTER);
        welcomeMsgLbl.setBorder(BorderFactory.createEmptyBorder(2, 2, 0, 0));
        
        //Adding label to container / container to parent panel
        welcomeMsg.add(welcomeMsgLbl);
        this.add(Box.createRigidArea(new Dimension(40, 0)));
        this.add(welcomeMsg);

        //Daily Quest Container
        JPanel dailyQuestHead = new JPanel();
        dailyQuestHead.setBackground(Styles.headerColor);
        dailyQuestHead.setMaximumSize(new Dimension(180, 40));
        dailyQuestHead.setLayout(new BorderLayout());

        //Daily Quest label
        JLabel dailyQuestHeadLbl = new JLabel("Daily Quests");
        dailyQuestHeadLbl.setFont(new Font("Arial", Font.BOLD, 20));
        dailyQuestHeadLbl.setVerticalAlignment(SwingConstants.CENTER);
        dailyQuestHeadLbl.setHorizontalAlignment(SwingConstants.CENTER);
        dailyQuestHeadLbl.setForeground(Color.white);
        dailyQuestHead.add(dailyQuestHeadLbl, BorderLayout.CENTER);
        this.add(Box.createRigidArea(new Dimension(110, 0)));
        this.add(dailyQuestHead);
    }
    
    private void BACKtoStartMouseClicked(java.awt.event.MouseEvent evt) { 
        MainPanel home = (MainPanel) SwingUtilities.getAncestorOfClass(MainPanel.class, this);
        home.restOfApp.HomePanel.setVisible(true);
        home.setVisible(false);
    }    
}
