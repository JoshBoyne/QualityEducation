package GUIComponents;

import Main.Styles;
import javax.swing.*;
import java.awt.*;

//Panel for question of the day - centerPanel North. 
public class QotdPanel extends JPanel {
    
    //Variables
    private Dimension dimensions = new Dimension(400, 220);
    private JPanel buttonsContainer;
    private  JLabel question;
    
    //Constructor
    public QotdPanel() {

        //Configuring this panel
        this.setPreferredSize(dimensions);
        this.setMaximumSize(dimensions);
        this.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(30), BorderFactory.createEmptyBorder(10, 10, 0, 0)));
        
        //Question of the Day label
        JLabel qotd = new JLabel("Question of the Day");
        qotd.setHorizontalAlignment(SwingConstants.CENTER);
        qotd.setVerticalAlignment(SwingConstants.TOP);
        qotd.setFont(new Font("Arial", Font.BOLD, 28));
        this.add(qotd, BorderLayout.NORTH);
        
        //Question label with placeholder default values
        question = new JLabel("<html><b>Which of the following correctly describes the differences<br> between full, incremental, and differential backups?" +
                "<br>A: Lorem Ipsum.    |    B: lorem Ipsum.<br>C: Lorem Ipsum.    |    D: Lorem Ipsum.</b></html>");
        question.setPreferredSize(new Dimension(300, 100));
        question.setHorizontalAlignment(SwingConstants.CENTER);
        question.setVerticalAlignment(SwingConstants.CENTER);
        question.setFont(new Font("Arial", Font.BOLD, 14));
        question.setBackground(Styles.secondarySalmon);
        question.setOpaque(true);
        this.add(question, BorderLayout.CENTER);

        
        //Button Constainer for Awnsers
        buttonsContainer = new JPanel();
        buttonsContainer.setBackground(Styles.secondarySalmon);
        buttonsContainer.setPreferredSize(new Dimension(600, 50));
        buttonsContainer.setLayout(new GridBagLayout());
        
        //Constraints for gridbag layout
        GridBagConstraints buttonConst = new GridBagConstraints();
        buttonConst.insets = new Insets(10, 50, 10, 10);
        buttonConst.fill = GridBagConstraints.NONE;
        buttonConst.anchor = GridBagConstraints.CENTER;
        buttonConst.weightx = 1.0; 
        buttonConst.weighty = 0;  


        //Default values for button text
        for(int i = 0; i <4; i++) {
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
            buttonsContainer.add(new CustomButton(letter), buttonConst);
        }
        this.add(buttonsContainer, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension corners = new Dimension(30,30);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        //Draws the rounded panel with borders.
        graphics.setColor(Styles.secondarySalmon);
        graphics.fillRoundRect(0, 0, width-1, height-1, corners.width, corners.height);
        graphics.setColor(getForeground());
        graphics.drawRoundRect(0, 0, width-1, height-1, corners.width, corners.height);
    }
    
    //Getters
    public JLabel getQuestion() {
        return this.question;
    }
    
    public JPanel getButtons() {
        return this.buttonsContainer;
    }
}

