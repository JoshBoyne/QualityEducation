package GUIComponents;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GUIHeader extends JPanel {

    Dimension dimensions = new Dimension(920, 70);

    public GUIHeader() {
        this.setBackground(Color.white);
        this.setPreferredSize(dimensions);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(new LineBorder(Color.black, 2));
        // Logo code

        this.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(new SunPanel());

        //Welcome Message
        JPanel welcomeMsg = new JPanel();
        welcomeMsg.setBackground(Color.white);
        welcomeMsg.setMaximumSize(new Dimension(400, 50));
        welcomeMsg.setLayout(new BorderLayout());
        JLabel welcomeMsgLbl = new JLabel("<html><span style='letter-spacing: 3px;'><u>Good Morning</u></span></html>");
        welcomeMsgLbl.setFont(new Font("Arial", Font.BOLD, 40));
        welcomeMsgLbl.setHorizontalAlignment(SwingConstants.LEFT);
        welcomeMsgLbl.setVerticalAlignment(SwingConstants.CENTER);
        welcomeMsgLbl.setBorder(BorderFactory.createEmptyBorder(2, 2, 0, 0));
        welcomeMsg.add(welcomeMsgLbl);
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(welcomeMsg);

        //Streak Symbol

        this.add(Box.createRigidArea(new Dimension(50, 0)));
        this.add(new StreakPanel());

        //Streak Freezes

        this.add(Box.createRigidArea(new Dimension(30, 0)));
        this.add(new FreezePanel());
        this.add(Box.createRigidArea(new Dimension(5, 0)));
        this.add(new FreezePanel());

        //Daily Quest Header
        JPanel dailyQuestHead = new JPanel();
        dailyQuestHead.setBackground(Color.white);
        dailyQuestHead.setMaximumSize(new Dimension(180, 40));
        dailyQuestHead.setLayout(new BorderLayout());

        JLabel dailyQuestHeadLbl = new JLabel("Daily Quests");
        dailyQuestHeadLbl.setFont(new Font("Arial", Font.BOLD, 20));
        dailyQuestHeadLbl.setVerticalAlignment(SwingConstants.CENTER);
        dailyQuestHeadLbl.setHorizontalAlignment(SwingConstants.CENTER);

        dailyQuestHead.add(dailyQuestHeadLbl, BorderLayout.CENTER);
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(dailyQuestHead);
    }

}
