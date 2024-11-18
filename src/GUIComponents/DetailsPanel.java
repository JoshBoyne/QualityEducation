package GUIComponents;

import javax.swing.*;
import java.awt.*;

public class DetailsPanel extends JPanel {

    public DetailsPanel() {
        this.setBackground(Color.yellow);
        this.setPreferredSize(new Dimension(600, 130));
        this.setLayout(new BorderLayout());

        // Avatar section
        JPanel avatar = new JPanel();
        avatar.setPreferredSize(new Dimension(50, 50));
        avatar.setBackground(Color.black);

        JPanel avatarContainer = new JPanel();
        avatarContainer.setLayout(new BorderLayout());
        avatarContainer.setPreferredSize(new Dimension(50, 100));
        avatarContainer.setBackground(Color.red);

        JPanel aviNameContainer = new JPanel();
        aviNameContainer.setLayout(new BorderLayout());
        aviNameContainer.setPreferredSize(new Dimension(595, 50));
        aviNameContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Removed padding

        // Name and XP labels
        JPanel nameAndXp = new JPanel();
        nameAndXp.setLayout(new BoxLayout(nameAndXp, BoxLayout.Y_AXIS));
        nameAndXp.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Removed padding
        JLabel yourName = new JLabel("Your Name");
        yourName.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // Reduced padding
        JLabel xp = new JLabel("Level 1");
        xp.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // Reduced padding
        nameAndXp.add(yourName);
        nameAndXp.add(xp);

        aviNameContainer.add(avatar, BorderLayout.WEST);
        aviNameContainer.add(nameAndXp, BorderLayout.CENTER);
        this.add(aviNameContainer, BorderLayout.NORTH);

        // Center Panel (with GridBagLayout)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.blue);
        centerPanel.setPreferredSize(new Dimension(100, 25));

        // Creating GridBagLayout container
        JPanel centerC = new JPanel();
        centerC.setLayout(new GridBagLayout());
        centerC.setBackground(Color.orange);
        centerC.setPreferredSize(new Dimension(10, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1d;
        gbc.weighty = 1d;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5); // Reduced padding between grid items

        // Panels for centerC
        JPanel one = new JPanel();
        one.setBackground(Color.green);
        one.setPreferredSize(new Dimension(100, 25));

        JPanel two = new JPanel();
        two.setPreferredSize(new Dimension(100, 25));
        two.setBackground(Color.white);

        JPanel three = new JPanel();
        three.setPreferredSize(new Dimension(100, 25));
        three.setBackground(Color.red);

        JPanel four = new JPanel();
        four.setPreferredSize(new Dimension(100, 25));
        four.setBackground(Color.blue);

        // Add components to centerC with GridBagConstraints
        gbc.gridx = 0;
        centerC.add(one, gbc);

        gbc.gridx = 1;
        centerC.add(two, gbc);

        gbc.gridx = 2;
        centerC.add(three, gbc);

        gbc.gridx = 3;
        centerC.add(four, gbc);

        centerPanel.add(centerC, BorderLayout.CENTER);
        this.add(centerPanel, BorderLayout.CENTER);

        // Bottom Panel (centerS)
        JPanel centerS = new JPanel();
        centerS.setBackground(Color.green);
        centerS.setPreferredSize(new Dimension(595, 30));
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

        this.add(centerS, BorderLayout.SOUTH);
    }
}
