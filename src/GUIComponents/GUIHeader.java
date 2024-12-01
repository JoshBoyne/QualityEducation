package GUIComponents;

import Main.Styles;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import org.netbeans.lib.awtextra.AbsoluteLayout;

public class GUIHeader extends JPanel {

    Dimension dimensions = new Dimension(920, 50);

    public GUIHeader() {
        this.setBackground(new Color(51, 51, 51));
        this.setPreferredSize(dimensions);
        this.setMaximumSize(dimensions);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(new LineBorder(Color.black, 2));
        // Logo code
        JPanel backContainer = new JPanel();
        backContainer.setMaximumSize(new Dimension(50, 50));
        backContainer.setPreferredSize(new Dimension(38, 50));
       // backContainer.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        backContainer.setBackground(new Color(255, 102, 102));
        //backContainer.setLayout(new AbsoluteLayout());
        JLabel BACKtoStart = new JLabel();
        //
        BACKtoStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prototypetest/left-arrows (2).png"))); // NOI18N
        BACKtoStart.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BACKtoStartMouseClicked(evt);
            }
        });
        //
        backContainer.add(BACKtoStart);
        this.add(backContainer);
        this.add(Box.createRigidArea(new Dimension(70, 0)));
        this.add(new SunPanel());

        //Welcome Message
        JPanel welcomeMsg = new JPanel();
        welcomeMsg.setBackground(Styles.headerColor);
        welcomeMsg.setForeground(Color.white);
        welcomeMsg.setMaximumSize(new Dimension(400, 50));
        welcomeMsg.setLayout(new BorderLayout());
        JLabel welcomeMsgLbl = new JLabel("<html><span style='letter-spacing: 3px;'><u>Good Morning</u></span></html>");
        welcomeMsgLbl.setFont(new Font("Arial", Font.BOLD, 40));
        welcomeMsgLbl.setForeground(Color.white);
        welcomeMsgLbl.setHorizontalAlignment(SwingConstants.LEFT);
        welcomeMsgLbl.setVerticalAlignment(SwingConstants.CENTER);
        welcomeMsgLbl.setBorder(BorderFactory.createEmptyBorder(2, 2, 0, 0));
        welcomeMsg.add(welcomeMsgLbl);
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(welcomeMsg);

        //Streak Symbol

        this.add(Box.createRigidArea(new Dimension(30, 0)));
        this.add(new StreakPanel());

        //Streak Freezes

        this.add(Box.createRigidArea(new Dimension(30, 0)));
        this.add(new FreezePanel());
        this.add(Box.createRigidArea(new Dimension(5, 0)));
        this.add(new FreezePanel());

        //Daily Quest Header
        JPanel dailyQuestHead = new JPanel();
        dailyQuestHead.setBackground(Styles.headerColor);
        dailyQuestHead.setMaximumSize(new Dimension(180, 40));
        dailyQuestHead.setLayout(new BorderLayout());

        JLabel dailyQuestHeadLbl = new JLabel("Daily Quests");
        dailyQuestHeadLbl.setFont(new Font("Arial", Font.BOLD, 20));
        dailyQuestHeadLbl.setVerticalAlignment(SwingConstants.CENTER);
        dailyQuestHeadLbl.setHorizontalAlignment(SwingConstants.CENTER);
        dailyQuestHeadLbl.setForeground(Color.white);
        dailyQuestHead.add(dailyQuestHeadLbl, BorderLayout.CENTER);
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(dailyQuestHead);
    }
    
    private void BACKtoStartMouseClicked(java.awt.event.MouseEvent evt) { 
        MainFrame home = (MainFrame) SwingUtilities.getAncestorOfClass(MainFrame.class, this);
        home.restOfApp.HomePanel.setVisible(true);
        home.setVisible(false);
    }    
}
