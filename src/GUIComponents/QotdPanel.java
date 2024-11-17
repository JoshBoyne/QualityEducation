package GUIComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QotdPanel extends JPanel {
    Dimension dimensions = new Dimension(400, 200);
    public QotdPanel() {
     //   this.setBackground(Color.red);
        this.setPreferredSize(dimensions);
        this.setMaximumSize(dimensions);
        this.setAlignmentX(Component.RIGHT_ALIGNMENT);

        this.setLayout(new BorderLayout());

        this.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(30), BorderFactory.createEmptyBorder(10, 10, 0, 0)));
        //Question of the Day
        JLabel qotd = new JLabel("Question of the Day");
        //Formatting here
        qotd.setHorizontalAlignment(SwingConstants.CENTER);
        qotd.setVerticalAlignment(SwingConstants.TOP);
        qotd.setFont(new Font("Arial", Font.BOLD, 28));
        this.add(qotd, BorderLayout.NORTH);
        //
        JLabel question = new JLabel("<html><b>Which of the following correctly describes the differences<br> between full, incremental, and differential backups?" +
                "<br>A: Lorem Ipsum.    |    B: lorem Ipsum.<br>C: Lorem Ipsum.    |    D: Lorem Ipsum.</b></html>");
        question.setPreferredSize(new Dimension(300, 100));
        question.setHorizontalAlignment(SwingConstants.CENTER);
        question.setVerticalAlignment(SwingConstants.CENTER);
        question.setFont(new Font("Arial", Font.BOLD, 14));
        question.setBackground(Color.red);
        question.setOpaque(true);
        this.add(question, BorderLayout.CENTER);

        //
        //BUttons for Awnsers
        JPanel buttonsPlaceholder = new JPanel();
        buttonsPlaceholder.setBackground(Color.red);
        buttonsPlaceholder.setPreferredSize(new Dimension(600, 50));
        buttonsPlaceholder.setLayout(new GridBagLayout());
        GridBagConstraints buttonConst = new GridBagConstraints();
        buttonConst.insets = new Insets(10, 50, 10, 10);
        buttonConst.fill = GridBagConstraints.NONE;
        buttonConst.anchor = GridBagConstraints.CENTER;
        buttonConst.weightx = 1.0; // Allow horizontal growth
        buttonConst.weighty = 0;   // No vertical growth



        for(int i = 0; i < 3; i++) {

            buttonConst.gridx = i;
            buttonConst.gridy = 0;
            String letter;
            switch(i) {
                case 0:
                    letter = "A";
                break;
                case 1:
                    letter = "B";
                break;
                case 2:
                    letter = "C";
                break;
                case 3:
                    letter = "D";
                break;
                default:
                    letter = "Z";
                break;
            }
            buttonsPlaceholder.add(new CustomButton(letter), buttonConst);

        }
            buttonConst.gridx = 3;
            JButton tempBack = new JButton("BACK");
            tempBack.setPreferredSize(new Dimension(100, 100));
            tempBack.setBackground(Color.red);
            tempBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                MainFrame home = (MainFrame) SwingUtilities.getAncestorOfClass(MainFrame.class, buttonsPlaceholder);
                System.out.println(home.getClass().getName());
                home.restOfApp.HomePanel.setVisible(true);
                home.setVisible(false);
            }
        });
            buttonsPlaceholder.add(tempBack, buttonConst);


        this.add(buttonsPlaceholder, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(30,30); //Border corners arcs {width,height}, change this to whatever you want
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        //Draws the rounded panel with borders.
        graphics.setColor(Color.red);
        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
        graphics.setColor(getForeground());
        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
    }
}

