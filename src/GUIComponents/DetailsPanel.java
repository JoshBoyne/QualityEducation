package GUIComponents;


import Main.Styles;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;



//Panel containing account details - South section of center/
public class DetailsPanel extends JPanel {
    
    //Variables
    private JLabel yourNameLabel;
    private JPanel centerC;

    //Constructor.
    public DetailsPanel() {
        
        //Setting up this panel. 
        this.setBackground(Color.yellow);
        this.setPreferredSize(new Dimension(600, 130));
        this.setLayout(new BorderLayout());

        //Avatar Section
        //Container for avater and name/xp.
        JPanel aviNameContainer = new JPanel();
        aviNameContainer.setLayout(new BorderLayout());
        aviNameContainer.setPreferredSize(new Dimension(595, 50));
      
         //Avatar panel
        JPanel avatar = new JPanel();
        avatar.setPreferredSize(new Dimension(50, 50));
        avatar.setBackground(Color.black);

        //Name and XP Container
        JPanel nameAndXp = new JPanel();
        nameAndXp.setLayout(new BoxLayout(nameAndXp, BoxLayout.Y_AXIS));//Vertical to stack name and level

        //Name label
        yourNameLabel = new JLabel("Your Name");
        yourNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        
        //XP Label
        JLabel xp = new JLabel("Level 1");
        xp.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        
        //Adding labels to container.
        nameAndXp.add(yourNameLabel);
        nameAndXp.add(xp);

        //Adding name/xp and avatar to container.
        aviNameContainer.add(avatar, BorderLayout.WEST);
        aviNameContainer.add(nameAndXp, BorderLayout.CENTER);
        
        //Adding name/xp/avatar container to this panel. 
        aviNameContainer.add(avatar, BorderLayout.WEST);
        this.add(aviNameContainer, BorderLayout.NORTH);

        //Container for four subject quiz streak panels.
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.blue);
        centerPanel.setPreferredSize(new Dimension(100, 25));

        //Child container of four subject quiz container. 
        centerC = new JPanel();
        centerC.setLayout(new GridBagLayout());
        centerC.setPreferredSize(new Dimension(10, 25));

        //Constraints for container holding four different subject details panels.
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;//Fill up availible space
        gbc.weightx = 1;//Make components take up their share of X space.
        gbc.weighty = 1;//Make components take up their share of Y space.
        gbc.anchor = GridBagConstraints.CENTER;//Ceneter the content in their cells - shouldnt be needed with the fill. 
        gbc.insets = new Insets(5, 5, 5, 5);

        //Adding the panels for each subject. Using helper methods rather than seperate classes for practice.
        addPanelWithText(centerC, gbc, 0, "Math", "Quiz Goal:", "Quiz Count:");
        addPanelWithText(centerC, gbc, 1, "Science", "Quiz Goal:", "Quiz Count:");
        addPanelWithText(centerC, gbc, 2, "Geography", "Quiz Goal:", "Quiz Count:");
        addPanelWithText(centerC, gbc, 3, "Planets", "Quiz Goal:", "Quiz Count:");

        //Adding container for 4 subjects to higher container. 
        centerPanel.add(centerC, BorderLayout.CENTER);
        
        //Adding higher container to this panel.
        this.add(centerPanel, BorderLayout.CENTER);

        //South panel of details section - buttons.
        JPanel centerS = new JPanel();
        centerS.setPreferredSize(new Dimension(595, 30));
        centerS.setLayout(new GridBagLayout());

        //Customize goals button.
        JButton btn1 = new JButton("Customize Goals");
        btn1.addActionListener((ActionEvent e) -> {
            GoalsFrame goalFrame = new GoalsFrame(this);
        });
        
        //Login history button.
        JButton btn2 = new JButton("Logins");
        btn2.addActionListener((ActionEvent e) -> {
            LoginFrame frame = new LoginFrame("Logins");
        });


        //Cannot for the life of me get these buttons to be the same size. 
        gbc.gridx = 1;
        gbc.weightx = 1;
    
        //Adding btn1.
        centerS.add(btn1, gbc);

        //Adding btn2.
        gbc.gridx = 2;
        centerS.add(btn2, gbc);

        //Adding container to this panel.
        this.add(centerS, BorderLayout.SOUTH);
    }
    
    //Helper method for generating and adding the four diffenret subject panels. 
    //TODO MODIFY WITH INTEGRATION WITH QUIZ SECTION. ADD COUNTER TO SUBJECT THAT INCREMENTS ON EACH COMPLETION
    private void addPanelWithText(JPanel container, GridBagConstraints gbc, int gridX, String subject, String quizGoal, String quizCount) {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(3, 1)); //3 rows.
    panel.setBackground(Styles.secondarySalmon);
    panel.setPreferredSize(new Dimension(100, 75)); //
    panel.setBorder(BorderFactory.createLineBorder(Color.black, 2));

    //Adding labels.
    JLabel label1 = new JLabel(subject, SwingConstants.CENTER);
    JLabel label2 = new JLabel("Quiz Goal: ");
    label2.setHorizontalAlignment(SwingConstants.CENTER);
    if(Styles.user != null) {
        switch(subject) {
                case "Maths":
                    label2 = new JLabel(quizGoal+" "+Styles.user.getMaths().getQuizGoal(), SwingConstants.CENTER);

                    break;
                case "Science":
                    label2 = new JLabel(quizGoal+" "+Styles.user.getScience().getQuizGoal(), SwingConstants.CENTER);
                    break;  
                case "Geography":
                    label2 = new JLabel(quizGoal+" "+Styles.user.getGeography().getQuizGoal(), SwingConstants.CENTER);
                    break;
                case "Planets":
                    label2 = new JLabel(quizGoal+" "+Styles.user.getPlanets().getQuizGoal(), SwingConstants.CENTER);
                    break;
                default:
                    label2 = new JLabel(quizGoal, SwingConstants.CENTER);
                    break;
        }
    } 
    JLabel label3 = new JLabel(quizCount, SwingConstants.CENTER);

    panel.add(label1);
    panel.add(label2);
    panel.add(label3);

    // Add the panel to the container
    gbc.gridx = gridX;
    container.add(panel, gbc);
}

    //Initializing goal values.
    public void initializeGoals() {
        String[] subjects = {"Math", "Science", "Geography", "Planets"};
        for (String subject : subjects) {
            setGoals(subject); //Helper method for populating goals.
        }
    }

    // Existing setGoals method updated for single-subject updates
    public void setGoals(String subject) {

        //Getting array of the components in the container for the subject details.
        Component[] components = centerC.getComponents();
        
        //Itterating through components.
        for (Component comp : components) {
            if (comp instanceof JPanel) {//Ensuring components is a JPanel.
                JPanel panel = (JPanel) comp;//Getting a workable instance of the component to modify.

                //Getting the subject this panel is for. index 0 is always the subject.
                JLabel subjectLabel = (JLabel) panel.getComponent(0);

                if (subjectLabel.getText().equals(subject)) {//If the panel matches the subject passed to this method. If not - for loop will move to next component.
                    JLabel label = (JLabel) panel.getComponent(1);
                    String quizGoal = "Quiz Goal: ";//Universal text.
                    
                    //Retrieving goal from user for each subject, and updating relevant label.
                    switch (subject) {
                        case "Math":
                            label.setText(quizGoal + Styles.user.getMaths().getQuizGoal());
                            break;
                        case "Science":
                            label.setText(quizGoal + Styles.user.getScience().getQuizGoal());
                            break;
                        case "Geography":
                            label.setText(quizGoal + Styles.user.getGeography().getQuizGoal());
                            break;
                        case "Planets":
                            label.setText(quizGoal + Styles.user.getPlanets().getQuizGoal());
                            break;
                        default:
                            break;
                    }
                    break; //Exit iteration once this subject has had its details updated.
                }
            }
        }
        //Ensuring changes show up in GUI.
        centerC.revalidate();
        centerC.repaint();
    }

    public JLabel getYourNameLabel() {
        return this.yourNameLabel;
    }
}
