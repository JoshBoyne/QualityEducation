package GUIComponents;

import javax.swing.*;
import java.awt.*;

public class DetailsPanel extends JPanel {

    public DetailsPanel() {
        this.setBackground(Color.yellow);
        this.setPreferredSize(new Dimension(600, 180));
        this.setLayout(new BorderLayout());
        JPanel avatar = new JPanel();
        avatar.setPreferredSize(new Dimension(50, 50));
        avatar.setBackground(Color.black);

        JPanel avatarContainer = new JPanel();
        avatarContainer.setLayout(new GridBagLayout());


        //avatarContainer.add(avatar);

        avatarContainer.setPreferredSize(new Dimension(50, 100));
        JPanel aviNameContainer = new JPanel();
        aviNameContainer.setLayout(new BorderLayout());
        aviNameContainer.add(avatar, BorderLayout.WEST);
        aviNameContainer.setPreferredSize(new Dimension(595, 50));
        avatarContainer.setBackground(Color.red);
        JPanel nameAndXp = new JPanel();
        BoxLayout layout = new BoxLayout(nameAndXp, BoxLayout.Y_AXIS);
        nameAndXp.setLayout(layout);
        JLabel yourName = new JLabel("Your Name");
        yourName.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));
        JLabel xp = new JLabel("Level 1");
        xp.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        nameAndXp.add(yourName);
        nameAndXp.add(xp);
        aviNameContainer.add(nameAndXp, BorderLayout.CENTER);
        this.add(aviNameContainer, BorderLayout.NORTH);

        //
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.blue);
        centerPanel.setPreferredSize(new Dimension(100, 50));

        //
        JPanel one = new JPanel();
        one.setBackground(Color.green);
        one.setPreferredSize(new Dimension(100, 100));
        JPanel two = new JPanel();
        two.setPreferredSize(new Dimension(100, 100));
        two.setBackground(Color.white);
        JPanel three = new JPanel();
        three.setPreferredSize(new Dimension(100, 100));
        three.setBackground(Color.red);
        //



        JPanel centerC = new JPanel();
        centerC.setLayout(new GridBagLayout());
        centerC.setBackground(Color.orange);
        centerC.setPreferredSize(new Dimension(10, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel newsHeading = new JPanel();
        newsHeading.setPreferredSize(new Dimension(100, 25));
        newsHeading.setBackground(Color.BLACK);
        newsHeading.setLayout(new GridBagLayout());
        JLabel news = new JLabel("Your News");
        news.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 20, 10, 10);
        newsHeading.add(news, gbc);
        this.add(newsHeading, BorderLayout.NORTH);
        gbc.insets = new Insets(10, 10, 10, 100);

        gbc.gridy = 1;
        gbc.gridx = 1;
        centerC.add(one, gbc);
        gbc.gridx = 2;
        centerC.add(two, gbc);
        gbc.gridx = 3;
        gbc.insets = new Insets(10, 0, 10, 10);
        centerC.add(three, gbc);
        centerPanel.add(centerC, BorderLayout.CENTER);

        this.add(centerPanel, BorderLayout.CENTER);



        //
        JPanel centerS = new JPanel();
        centerS.setBackground(Color.green);
        centerS.setPreferredSize(new Dimension(10, 30));
        centerS.setLayout(new GridBagLayout());

        JButton btn1 = new JButton();
        JButton btn2 = new JButton();
        JButton btn3 = new JButton();
        gbc.gridx = 1;
        centerS.add(btn1, gbc);
        gbc.gridx = 2;
        centerS.add(btn2, gbc);
        gbc.gridx = 3;
        centerS.add(btn3, gbc);

        //



        this.add(centerS, BorderLayout.SOUTH);
        }
}
