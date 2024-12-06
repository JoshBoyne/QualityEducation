/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package prototypetest;

import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import Quiz.Question;
import Quiz.QuizManager;
import Quiz.QuizTimer;
import Quiz.Result;
import java.io.IOException;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import java.util.logging.Level;
/**
 *
 * @author jason, Josh, Owen
 */
public class MainGUI extends javax.swing.JPanel {
        private QuizManager quizManager;
        //instance variables
        private List<Question> currentQuestions; // List of questions for the selected topic
        private int currentQuestionIndex;
        private Map<Integer, String> userAnswers;
        private Result quizResult;
        private int adminCurrentQuestionIndex;
        private List<Question> adminCurrentQuestions;
        private Timer quizTimer;
        private int elapsedSeconds = 0;
        private static final Logger LOGGER = Logger.getLogger(MainGUI.class.getName());
        private Category category; 
    
    
    
    public MainGUI() {
        initComponents();

    // initialising quizmanager before using it
    quizManager = new QuizManager(); 

    // gets questions from quizmanager
    currentQuestions = quizManager.getQuestions();
    adminCurrentQuestions = quizManager.getQuestions();

    // initialise other variables and components
    initializeButtonActions();
    currentQuestionIndex = 0;
    adminCurrentQuestionIndex = 0;
    userAnswers = new HashMap<>();
    quizResult = new Result();

    
    RManager rManager = new RManager();
    rManager.enableLinkLabel(LinkLabel, "https://www.globalgoals.org/goals/4-quality-education/");

    HomePanel.setVisible(false);
    StartPanel.setVisible(true);
    CreateAccPanel.setVisible(false);
    LearningPanel.setVisible(false);
    QuizPanel.setVisible(false);
    AdminPanel.setVisible(false);
    AdminQuizPanel.setVisible(false);

    quizTA1.setEditable(false);
    adminQuestionTA.setEditable(true);

    aBTN.setEnabled(false);
    bBTN.setEnabled(false);
    cBTN.setEnabled(false);
    dBTN.setEnabled(false);
    quizNextBTN.setEnabled(false);
    quizPrevBTN.setEnabled(false);
    quizSubmitBTN1.setEnabled(false);
    
    //for the FeedbackForm class
         new FeedbackForm(Emailfield, QtArea, CFconfirmButton);
         category = new Category("categories.txt");
           CRUDListeners();
    
    }
    
    private void CRUDListeners() {
        //action listener for create button
        CreateCRUDb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newCategory = JOptionPane.showInputDialog("Enter your new Note:"); //prompt user 
                if (newCategory != null && !newCategory.trim().isEmpty()) {
                    category.create(newCategory);
                    JOptionPane.showMessageDialog(null, "Note added successfully!");
                    updateTextArea();
                }
            }
        });

        // Read Button, action lsitener for (read button)
        ReadCRUDb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTextArea(); //call method to update text area 
            }
        });

        // Update Button
        UpdateCRUDb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //prompt for index to update
                String indexStr = JOptionPane.showInputDialog("Enter index of your note to update:");
                try {
                    int index = Integer.parseInt(indexStr); //parse inout string to int
                    String updatedCategory = JOptionPane.showInputDialog("Enter new notes value:");
                   
                    if (updatedCategory != null && !updatedCategory.trim().isEmpty()) { //inoput validation
                        category.update(index, updatedCategory); //update note at index
                        JOptionPane.showMessageDialog(null, "Notes updated successfully!"); //notify user 
                        updateTextArea();  //refresh text area 
                    }
                } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid index or input. Please try again.");
                }
            }
        });

        // Delete Button
        DeleteCRUDb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String indexStr = JOptionPane.showInputDialog("Enter index of your notes to delete:");
                try {
                    int index = Integer.parseInt(indexStr);
                    category.delete(index);
                    JOptionPane.showMessageDialog(null, "Note deleted successfully!");
                    updateTextArea();
                } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid index. Please try again.");
                }
            }
        });
    }
    // Update the jTextArea1 with the current list, make sure this works (self note)
    private void updateTextArea() {
        jTextArea1.setText(category.readAll());
    }
    
private void adminLoadQuestion() {
    //method that loads the questions in the admin page
    if (adminCurrentQuestions != null && !adminCurrentQuestions.isEmpty() && adminCurrentQuestionIndex >= 0) {
        // Get the current question
        Question currentQuestion = adminCurrentQuestions.get(adminCurrentQuestionIndex);

        // makes the question and the options
        StringBuilder questionContent = new StringBuilder();
        questionContent.append("Question: ").append(currentQuestion.getText()).append("\n\n")
                       .append("A) ").append(currentQuestion.getOptionA()).append("\n")
                       .append("B) ").append(currentQuestion.getOptionB()).append("\n")
                       .append("C) ").append(currentQuestion.getOptionC()).append("\n")
                       .append("D) ").append(currentQuestion.getOptionD()).append("\n");

        // Set question and options to adminQuestionTA 
        adminQuestionTA.setText(questionContent.toString());

        // Display correct answer in adminAnswerTA
        String correctAnswer = currentQuestion.getCorrectAnswer();
        if (correctAnswer != null && !correctAnswer.trim().isEmpty()) {
            adminAnswerTA.setText("Correct Answer: " + correctAnswer);
        } else {
            adminAnswerTA.setText("Correct Answer: Not set");
        }

        // Clear and set the correct radio button
        adminQuizBG.clearSelection();
        if (correctAnswer != null) {
            switch (correctAnswer.toUpperCase()) { // Ensure case consistency
                case "A": adminABTN.setSelected(true); break;
                case "B": adminBBTN.setSelected(true); break;
                case "C": adminCBTN.setSelected(true); break;
                case "D": adminDBTN.setSelected(true); break;
                default: break;
            }
        }
    } else {
        // No questions found 
        adminQuestionTA.setText("");
        adminAnswerTA.setText("Correct Answer: Not set");
        adminQuizBG.clearSelection();
    }
}
 private void handleAdminSubmitTopic() {
    adminSubmitTopicBTN.addActionListener(e -> {
        String selectedTopic = (String) adminQuizCB.getSelectedItem();  // Get the selected topic
        adminCurrentQuestions = Question.getQuestionsByTopic(selectedTopic); 
        adminCurrentQuestionIndex = 0;  // Start from the first question
        adminLoadQuestion();  // Load the first question into the admin GUI
        enableAdminControls();  // Enable controls for navigation and editing
    });
}

 //method that handles logic for next button
private void handleAdminNextButton() {
    adminNextBTN.addActionListener(e -> {
        if (adminCurrentQuestionIndex < adminCurrentQuestions.size() - 1) {
            adminCurrentQuestionIndex++;  // Move to the next question
            adminLoadQuestion();  // Load the next question
        } else {
            JOptionPane.showMessageDialog(this, "You are on the last question.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    });
}
//method that handles logic for previous button
private void handleAdminPreviousButton() {
    adminPrevBTN.addActionListener(e -> {
        if (adminCurrentQuestionIndex > 0) {
            adminCurrentQuestionIndex--;  // Go to the previous question
            adminLoadQuestion();  // Load the previous question
        } else {
            JOptionPane.showMessageDialog(this, "You are on the first question.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    });
}

 //enables the controls in the admin page - buttons, radio buttons 
private void enableAdminControls() {
    adminABTN.setEnabled(true);
    adminBBTN.setEnabled(true);
    adminCBTN.setEnabled(true);
    adminDBTN.setEnabled(true);

    adminNextBTN.setEnabled(true);
    adminPrevBTN.setEnabled(true);

    adminQuizConfirmBTN.setEnabled(true);
    adminCreateBTN.setEnabled(true);
    adminUpdateBTN.setEnabled(true);
    adminDeleteBTN.setEnabled(true);
    adminClearBTN.setEnabled(true);
    adminSubmitAnswerBTN.setEnabled(true);
}




// method to load the question into the gui
private void loadQuestion() {
      try {
        if (currentQuestions != null && !currentQuestions.isEmpty()) {
            Question currentQuestion = currentQuestions.get(currentQuestionIndex);

            quizTA1.setText(currentQuestion.getText() + "\n\n" +
                            "A: " + currentQuestion.getOptionA() + "\n" +
                            "B: " + currentQuestion.getOptionB() + "\n" +
                            "C: " + currentQuestion.getOptionC() + "\n" +
                            "D: " + currentQuestion.getOptionD());

            quizBG.clearSelection(); // Clear previous answers

            String savedAnswer = userAnswers.get(currentQuestionIndex);
            if (savedAnswer != null) {
                switch (savedAnswer) {
                    case "A": aBTN.setSelected(true); break;
                    case "B": bBTN.setSelected(true); break;
                    case "C": cBTN.setSelected(true); break;
                    case "D": dBTN.setSelected(true); break;
                }
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error loading question: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

//method that enables the controls of the buttons and radio buttons in the quiz page
private void enableQuizControls() {
    aBTN.setEnabled(true);
    bBTN.setEnabled(true);
    cBTN.setEnabled(true);
    dBTN.setEnabled(true);

    quizNextBTN.setEnabled(true);
    quizPrevBTN.setEnabled(true);

    quizSubmitBTN1.setEnabled(true);
}

// Method to save the selected answer
private void saveAnswer() {
    if (aBTN.isSelected()) {
        userAnswers.put(currentQuestionIndex, "A");
    } else if (bBTN.isSelected()) {
        userAnswers.put(currentQuestionIndex, "B");
    } else if (cBTN.isSelected()) {
        userAnswers.put(currentQuestionIndex, "C");
    } else if (dBTN.isSelected()) {
        userAnswers.put(currentQuestionIndex, "D");
    }
}

// logic for submit topic buttons
private void handleSubmitTopic() {
    submitTopicBTN.addActionListener(e -> {
        String selectedTopic = (String) topicCB.getSelectedItem();  // Get the selected topic
        currentQuestions = Question.getQuestionsByTopic(selectedTopic);  
        currentQuestionIndex = 0;  // Start from the first question
        userAnswers.clear();  // Clear any previous answers
        loadQuestion();  // Load the first question into the GUI
        enableQuizControls(); 
    });
}

// logic for next button
private void handleNextButton() {
    quizNextBTN.addActionListener(e -> {
        saveAnswer();  // Save the current answer

        if (currentQuestionIndex < currentQuestions.size() - 1) {
            currentQuestionIndex++;  // Move to the next question
            loadQuestion();  // Load the next question
        } else {
            JOptionPane.showMessageDialog(this, "You are on the last question.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    });
}

// logic for the previous button
private void handlePreviousButton() {
    quizPrevBTN.addActionListener(e -> {
        saveAnswer();  // Save the current answer

        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;  // Go to the previous question
            loadQuestion();  // Load the previous question
        } else {
            JOptionPane.showMessageDialog(this, "You are on the first question.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    });
}

// logic for the submit answers button
private void handleSubmitAnswers() {
    quizSubmitBTN1.addActionListener(e -> {
        saveAnswer();  // Save the current answer
       
    });
}

// method that initialises the buttons
private void initializeButtonActions() {
    handleSubmitTopic();
    handleNextButton();
    handlePreviousButton();
    handleSubmitAnswers();
    
    handleAdminSubmitTopic();
    handleAdminNextButton();
    handleAdminPreviousButton();
}
    private void displayResults() {
    String summary = quizResult.getSummary();
    quizTA1.setText(summary); // Display summary in the text area
}
    
    private void startQuizTimer() {
    // Reset time
    elapsedSeconds = 0;

    // Update the timer display
    updateTimerDisplay();

    // Initialise the timer
    quizTimer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Increment elapsed time
            elapsedSeconds++;

            // Update the timer display
            updateTimerDisplay();
        }
    });

    // Start the timer
    quizTimer.start();

    // Enable the Stop Timer button
    stopTimerBTN.setEnabled(true);
}
    //method for timer display
    private void updateTimerDisplay() {
    int minutes = elapsedSeconds / 60;
    int seconds = elapsedSeconds % 60;
    String timeFormatted = String.format("%02d:%02d", minutes, seconds);//time format
    quizTimerLBL.setText(timeFormatted);
}
    
   private void stopQuizTimer() {
    if (quizTimer != null && quizTimer.isRunning()) {
        quizTimer.stop();
    }
}
    //finish quiz method
    private void finishQuiz() {
    // Calculate the score
    quizResult.calculateScore(userAnswers, currentQuestions);
    String summary = quizResult.getSummary();

    // Stop the timer
    stopQuizTimer();

    // Get the time in seconds
    int timeTakenSeconds = elapsedSeconds;

    //display the total time taken
    JOptionPane.showMessageDialog(this, "You completed the quiz in " + quizTimerLBL.getText() + ".\n" + summary, "Quiz Completed", JOptionPane.INFORMATION_MESSAGE);

    // disable quiz controls
    disableQuizControls();

    // display the summary in the text area
    quizTA1.setText(summary + "\nTime Taken: " + quizTimerLBL.getText());

    // Re-enable the "Submit Topic" button for future quizzes
    submitTopicBTN.setEnabled(true);

    // Log quiz completion
    LOGGER.log(Level.INFO, "finishQuiz() called. Quiz completed.");

    // Save the quiz time to quizTimes.dat
    String selectedTopic = (String) topicCB.getSelectedItem(); // Get the selected topic
    if (selectedTopic != null) {
        // Create a QuizTimer object
        QuizTimer quizTimer = new QuizTimer(timeTakenSeconds, selectedTopic);

        // Save the QuizTimer object
        QuizTimer.saveQuizTimerToFile(quizTimer);
        LOGGER.log(Level.INFO, "QuizTimer saved: " + quizTimer.toString());
    } else {
        LOGGER.log(Level.WARNING, "No topic selected when saving quiz time.");
    }
}
    //method to disable quiz controls
    private void disableQuizControls() {
    aBTN.setEnabled(false);
    bBTN.setEnabled(false);
    cBTN.setEnabled(false);
    dBTN.setEnabled(false);
    quizNextBTN.setEnabled(false);
    quizPrevBTN.setEnabled(false);
    quizSubmitBTN1.setEnabled(false);
}
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        quizBG = new javax.swing.ButtonGroup();
        adminQuizBG = new javax.swing.ButtonGroup();
        mainFrame1 = new GUIComponents.MainFrame(this);
        StartPanel = new javax.swing.JPanel();
        IconP = new javax.swing.JPanel();
        Bicon = new javax.swing.JLabel();
        topBar = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        StartButton = new javax.swing.JButton();
        HomePanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        BACKtoStart = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        LearningPanel = new javax.swing.JPanel();
        LearnPanel = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        TabbedResource = new javax.swing.JTabbedPane();
        ResourceP = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        LinkLabel = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        CategoryP = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        CreateCRUDb = new javax.swing.JButton();
        ReadCRUDb = new javax.swing.JButton();
        UpdateCRUDb = new javax.swing.JButton();
        DeleteCRUDb = new javax.swing.JButton();
        ContactPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        QtArea = new javax.swing.JTextArea();
        Emailfield = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        CFconfirmButton = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        LBback = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        CreateAccPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        AccountIDInput = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        AccountNameInput = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        EmailInput = new javax.swing.JTextField();
        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel14 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        QuizPanel = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        Qback = new javax.swing.JLabel();
        QuizPanel1 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        quizTA1 = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        aBTN = new javax.swing.JRadioButton();
        bBTN = new javax.swing.JRadioButton();
        cBTN = new javax.swing.JRadioButton();
        dBTN = new javax.swing.JRadioButton();
        submitTopicBTN = new javax.swing.JButton();
        quizPrevBTN = new javax.swing.JButton();
        quizNextBTN = new javax.swing.JButton();
        topicCB = new javax.swing.JComboBox<>();
        quizSubmitBTN1 = new javax.swing.JButton();
        timerPanel = new javax.swing.JPanel();
        quizTimerLBL = new javax.swing.JLabel();
        stopTimerBTN = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        AdminPanel = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        BACKtoStart1 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        AdminQuizPanel = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        Qback1 = new javax.swing.JLabel();
        TabbedResource1 = new javax.swing.JTabbedPane();
        QuestionsPanel = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        adminQuestionTA = new javax.swing.JTextArea();
        adminQuizConfirmBTN = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        adminNextBTN = new javax.swing.JButton();
        adminDeleteBTN = new javax.swing.JButton();
        adminPrevBTN = new javax.swing.JButton();
        adminCreateBTN = new javax.swing.JButton();
        adminUpdateBTN = new javax.swing.JButton();
        adminQuizCB = new javax.swing.JComboBox<>();
        adminSubmitTopicBTN = new javax.swing.JButton();
        adminClearBTN = new javax.swing.JButton();
        AnswerPanel = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        adminABTN = new javax.swing.JRadioButton();
        adminBBTN = new javax.swing.JRadioButton();
        adminCBTN = new javax.swing.JRadioButton();
        adminDBTN = new javax.swing.JRadioButton();
        adminSubmitAnswerBTN = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        adminAnswerTA = new javax.swing.JTextArea();
        QuizPanel3 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(204, 204, 204));
        setPreferredSize(new java.awt.Dimension(920, 550));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainFrame1.setVisible(false);
        add(mainFrame1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        StartPanel.setBackground(new java.awt.Color(204, 204, 204));
        StartPanel.setForeground(new java.awt.Color(255, 204, 153));
        StartPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        IconP.setBackground(new java.awt.Color(252, 248, 248));
        IconP.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        IconP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Bicon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prototypetest/Bopen.png"))); // NOI18N
        IconP.add(Bicon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        StartPanel.add(IconP, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 350, 350));

        topBar.setBackground(new java.awt.Color(51, 51, 51));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Quality Education");

        javax.swing.GroupLayout topBarLayout = new javax.swing.GroupLayout(topBar);
        topBar.setLayout(topBarLayout);
        topBarLayout.setHorizontalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topBarLayout.createSequentialGroup()
                .addContainerGap(372, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(349, 349, 349))
        );
        topBarLayout.setVerticalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topBarLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addContainerGap())
        );

        StartPanel.add(topBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 50));

        jPanel15.setBackground(new java.awt.Color(255, 102, 102));

        StartButton.setText("Start Up Now");
        StartButton.setToolTipText("Click Here To Start!");
        StartButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        StartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(294, Short.MAX_VALUE)
                .addComponent(StartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(270, 270, 270))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(94, Short.MAX_VALUE)
                .addComponent(StartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        StartPanel.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 920, 180));

        add(StartPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 550));

        HomePanel.setBackground(new java.awt.Color(241, 240, 240));
        HomePanel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        HomePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 102, 102));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BACKtoStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prototypetest/left-arrows (2).png"))); // NOI18N
        BACKtoStart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BACKtoStartMouseClicked(evt);
            }
        });
        jPanel2.add(BACKtoStart, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        HomePanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 50));

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        HomePanel.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 50));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setText("CHOOSE YOUR OPTION:");
        HomePanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 300, 50));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prototypetest/book.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Learning Resources");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel9))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel1)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addContainerGap())
        );

        HomePanel.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, -1, -1));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prototypetest/quiz.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Quizzes And Tests");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(45, 45, 45))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addContainerGap())
        );

        HomePanel.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, -1, -1));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prototypetest/progress.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Progress Tracker");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10))
                .addGap(32, 32, 32))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addContainerGap())
        );

        HomePanel.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 210, -1, -1));

        jPanel11.setBackground(new java.awt.Color(255, 102, 102));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        HomePanel.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 920, 180));

        add(HomePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 550));
        HomePanel.getAccessibleContext().setAccessibleName("");

        LearningPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LearnPanel.setBackground(new java.awt.Color(153, 153, 153));
        LearnPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(0, 0, 0), null));
        LearnPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 880, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        LearnPanel.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 880, -1));

        TabbedResource.setBackground(new java.awt.Color(255, 255, 255));

        jLabel16.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel16.setText("Resources:");

        jPanel4.setBackground(new java.awt.Color(210, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 102, 102));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel15.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel15.setText("Sustainable Development Goals");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(296, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 660, 41));

        LinkLabel.setBackground(new java.awt.Color(255, 255, 255));
        LinkLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prototypetest/book.png"))); // NOI18N
        LinkLabel.setToolTipText("CLICK THIS LINK!");
        jPanel4.add(LinkLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(528, 48, -1, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel17.setText("If you want to learn more click the Book in the corner.");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 530, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 70, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel20.setText("Goal 4: Quality Eductaion");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 340, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel21.setText("Browse and take Notes via the Category Pane");

        jLabel22.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel22.setText("Get in touch with us via the Contact Form!");

        javax.swing.GroupLayout ResourcePLayout = new javax.swing.GroupLayout(ResourceP);
        ResourceP.setLayout(ResourcePLayout);
        ResourcePLayout.setHorizontalGroup(
            ResourcePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ResourcePLayout.createSequentialGroup()
                .addGroup(ResourcePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ResourcePLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ResourcePLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(ResourcePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(ResourcePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ResourcePLayout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(530, Short.MAX_VALUE)))
        );
        ResourcePLayout.setVerticalGroup(
            ResourcePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ResourcePLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel21)
                .addGap(1, 1, 1)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(ResourcePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ResourcePLayout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(jLabel16)
                    .addContainerGap(273, Short.MAX_VALUE)))
        );

        TabbedResource.addTab("Resources", ResourceP);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        CreateCRUDb.setText("Create");

        ReadCRUDb.setText("Read");
        ReadCRUDb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReadCRUDbActionPerformed(evt);
            }
        });

        UpdateCRUDb.setText("Update");

        DeleteCRUDb.setText("Delete");

        javax.swing.GroupLayout CategoryPLayout = new javax.swing.GroupLayout(CategoryP);
        CategoryP.setLayout(CategoryPLayout);
        CategoryPLayout.setHorizontalGroup(
            CategoryPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CategoryPLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(CategoryPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CategoryPLayout.createSequentialGroup()
                        .addComponent(CreateCRUDb)
                        .addGap(18, 18, 18)
                        .addComponent(ReadCRUDb))
                    .addGroup(CategoryPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, CategoryPLayout.createSequentialGroup()
                            .addComponent(DeleteCRUDb)
                            .addGap(18, 18, 18)
                            .addComponent(UpdateCRUDb))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, CategoryPLayout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        CategoryPLayout.setVerticalGroup(
            CategoryPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CategoryPLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(CategoryPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CreateCRUDb)
                    .addComponent(ReadCRUDb))
                .addGap(18, 18, 18)
                .addGroup(CategoryPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DeleteCRUDb)
                    .addComponent(UpdateCRUDb))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        TabbedResource.addTab("Category", CategoryP);

        ContactPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ContactPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        QtArea.setBackground(new java.awt.Color(205, 249, 248));
        QtArea.setColumns(20);
        QtArea.setRows(5);
        jScrollPane2.setViewportView(QtArea);

        ContactPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 72, 380, 170));

        Emailfield.setBackground(new java.awt.Color(205, 249, 248));
        Emailfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailfieldActionPerformed(evt);
            }
        });
        ContactPanel.add(Emailfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 380, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel2.setText("Question or Query:");
        ContactPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 210, 50));

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel3.setText("Email:");
        ContactPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 70, -1));

        CFconfirmButton.setText("Confirm");
        CFconfirmButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CFconfirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CFconfirmButtonActionPerformed(evt);
            }
        });
        ContactPanel.add(CFconfirmButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 260, 100, 20));

        TabbedResource.addTab("Contact Form", ContactPanel);

        LearnPanel.add(TabbedResource, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 690, 350));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prototypetest/dragon (1).png"))); // NOI18N
        LearnPanel.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        LearningPanel.add(LearnPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 880, 440));

        jPanel1.setBackground(new java.awt.Color(255, 102, 102));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LBback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prototypetest/left-arrows (2).png"))); // NOI18N
        LBback.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LBbackMouseClicked(evt);
            }
        });
        jPanel1.add(LBback, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        LearningPanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 50));

        jPanel8.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        LearningPanel.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 50));

        jPanel13.setBackground(new java.awt.Color(255, 102, 102));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        LearningPanel.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 920, 180));

        add(LearningPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 550));

        CreateAccPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(0, 0, 0), null));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AccountIDInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccountIDInputActionPerformed(evt);
            }
        });
        jPanel3.add(AccountIDInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 338, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel5.setText("Account ID:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel13.setText("Account Name:");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, -1, -1));

        AccountNameInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccountNameInputActionPerformed(evt);
            }
        });
        jPanel3.add(AccountNameInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, 338, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel12.setText("Email:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, -1, -1));

        EmailInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailInputActionPerformed(evt);
            }
        });
        jPanel3.add(EmailInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 200, 338, -1));

        jToggleButton1.setText("Confirm");
        jToggleButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 250, 120, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prototypetest/dragon (1).png"))); // NOI18N
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        jButton5.setText("Admin Page ");
        jButton5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 250, 110, -1));

        CreateAccPanel.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 810, 290));

        jPanel17.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        CreateAccPanel.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel14.setBackground(new java.awt.Color(255, 102, 102));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        CreateAccPanel.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 920, 180));

        jPanel16.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        CreateAccPanel.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 50));

        add(CreateAccPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        QuizPanel.setBackground(new java.awt.Color(241, 240, 240));
        QuizPanel.setPreferredSize(new java.awt.Dimension(920, 550));
        QuizPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel18.setBackground(new java.awt.Color(51, 51, 51));
        jPanel18.setPreferredSize(new java.awt.Dimension(920, 50));

        jPanel20.setBackground(new java.awt.Color(255, 102, 102));
        jPanel20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel20.setMinimumSize(new java.awt.Dimension(42, 42));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Qback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prototypetest/left-arrows (2).png"))); // NOI18N
        Qback.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QbackMouseClicked(evt);
            }
        });
        jPanel20.add(Qback, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 870, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        QuizPanel.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        QuizPanel1.setBackground(new java.awt.Color(153, 153, 153));
        QuizPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(0, 0, 0), null));
        QuizPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel21.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        QuizPanel1.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 880, -1));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prototypetest/dragon (1).png"))); // NOI18N
        QuizPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        quizTA1.setColumns(20);
        quizTA1.setRows(5);
        quizTA1.setText("Welcome!\n- To start the quiz please select the \"Submit Topic\" button.\n- To answer the questions please choose one of the radio buttons below (A, B, C, D).\n- Please then press \"Submit\" to confirm your answer, then go onto the next Question.\n- The Timer will start when you select the \"Submit Topic\" button, to stop the timer press the \n  \"Stop Time\" button in the top right corner.\n\nThank You!");
        jScrollPane3.setViewportView(quizTA1);

        QuizPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 540, 150));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel18.setText("Quiz");
        QuizPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 90, 50));

        aBTN.setBackground(new java.awt.Color(153, 153, 153));
        quizBG.add(aBTN);
        aBTN.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        aBTN.setText("A");
        QuizPanel1.add(aBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, -1, -1));

        bBTN.setBackground(new java.awt.Color(153, 153, 153));
        quizBG.add(bBTN);
        bBTN.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bBTN.setText("B");
        QuizPanel1.add(bBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, -1, -1));

        cBTN.setBackground(new java.awt.Color(153, 153, 153));
        quizBG.add(cBTN);
        cBTN.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cBTN.setText("C");
        cBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cBTNActionPerformed(evt);
            }
        });
        QuizPanel1.add(cBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 340, -1, -1));

        dBTN.setBackground(new java.awt.Color(153, 153, 153));
        quizBG.add(dBTN);
        dBTN.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        dBTN.setText("D");
        QuizPanel1.add(dBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, -1, -1));

        submitTopicBTN.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        submitTopicBTN.setText("SUBMIT TOPIC");
        submitTopicBTN.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        submitTopicBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitTopicBTNActionPerformed(evt);
            }
        });
        QuizPanel1.add(submitTopicBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 280, 90, 30));

        quizPrevBTN.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        quizPrevBTN.setText("PREVIOUS");
        quizPrevBTN.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        quizPrevBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quizPrevBTNActionPerformed(evt);
            }
        });
        QuizPanel1.add(quizPrevBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 370, 90, 30));

        quizNextBTN.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        quizNextBTN.setText("NEXT");
        quizNextBTN.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        quizNextBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quizNextBTNActionPerformed(evt);
            }
        });
        QuizPanel1.add(quizNextBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 370, 90, 30));

        topicCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Maths", "Geography", "Space", "Science" }));
        topicCB.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        QuizPanel1.add(topicCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 280, 190, 30));

        quizSubmitBTN1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        quizSubmitBTN1.setText("SUBMIT");
        quizSubmitBTN1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        quizSubmitBTN1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quizSubmitBTN1ActionPerformed(evt);
            }
        });
        QuizPanel1.add(quizSubmitBTN1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 370, 90, 30));

        quizTimerLBL.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N

        javax.swing.GroupLayout timerPanelLayout = new javax.swing.GroupLayout(timerPanel);
        timerPanel.setLayout(timerPanelLayout);
        timerPanelLayout.setHorizontalGroup(
            timerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, timerPanelLayout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addComponent(quizTimerLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        timerPanelLayout.setVerticalGroup(
            timerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timerPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(quizTimerLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        QuizPanel1.add(timerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, 250, 70));

        stopTimerBTN.setText("STOP TIME");
        stopTimerBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopTimerBTNActionPerformed(evt);
            }
        });
        QuizPanel1.add(stopTimerBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 110, -1, -1));

        QuizPanel.add(QuizPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 880, 440));

        jPanel19.setBackground(new java.awt.Color(255, 102, 102));
        jPanel19.setPreferredSize(new java.awt.Dimension(920, 180));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        QuizPanel.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, -1, -1));

        add(QuizPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 550));

        AdminPanel.setBackground(new java.awt.Color(241, 240, 240));
        AdminPanel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        AdminPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel22.setBackground(new java.awt.Color(255, 102, 102));
        jPanel22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BACKtoStart1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prototypetest/left-arrows (2).png"))); // NOI18N
        BACKtoStart1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BACKtoStart1MouseClicked(evt);
            }
        });
        jPanel22.add(BACKtoStart1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        AdminPanel.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 50));

        jPanel23.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        AdminPanel.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 50));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel24.setText("CHOOSE YOUR OPTION:");
        AdminPanel.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 300, 50));

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prototypetest/book.png"))); // NOI18N
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel25MouseClicked(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel26.setText("Learning Resources");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel26))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel25)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel25)
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addContainerGap())
        );

        AdminPanel.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, -1, -1));

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prototypetest/quiz.png"))); // NOI18N
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel28.setText("Quizzes And Tests");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addGap(45, 45, 45))))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel27)
                .addGap(18, 18, 18)
                .addComponent(jLabel28)
                .addContainerGap())
        );

        AdminPanel.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, -1, -1));

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prototypetest/progress.png"))); // NOI18N

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel31.setText("Progress Tracker");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(jLabel31))
                .addGap(32, 32, 32))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addComponent(jLabel31)
                .addContainerGap())
        );

        AdminPanel.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 210, -1, -1));

        jPanel27.setBackground(new java.awt.Color(255, 102, 102));

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        AdminPanel.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 920, 180));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel32.setText("ADMIN");
        AdminPanel.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, 130, 30));

        add(AdminPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 550));

        AdminQuizPanel.setBackground(new java.awt.Color(241, 240, 240));
        AdminQuizPanel.setPreferredSize(new java.awt.Dimension(920, 550));
        AdminQuizPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel28.setBackground(new java.awt.Color(51, 51, 51));
        jPanel28.setPreferredSize(new java.awt.Dimension(920, 50));

        jPanel29.setBackground(new java.awt.Color(255, 102, 102));
        jPanel29.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel29.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Qback1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prototypetest/left-arrows (2).png"))); // NOI18N
        Qback1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Qback1MouseClicked(evt);
            }
        });
        jPanel29.add(Qback1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 870, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        AdminQuizPanel.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        TabbedResource1.setBackground(new java.awt.Color(255, 255, 255));

        QuestionsPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        QuestionsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        adminQuestionTA.setColumns(20);
        adminQuestionTA.setRows(5);
        adminQuestionTA.setText("Select a topic from the combo box, then click on the submit topic to see the questions \nappear. To change the answers go to the answers tab ");
        jScrollPane6.setViewportView(adminQuestionTA);

        QuestionsPanel.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 500, 120));

        adminQuizConfirmBTN.setText("Confirm");
        adminQuizConfirmBTN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        adminQuizConfirmBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminQuizConfirmBTNActionPerformed(evt);
            }
        });
        QuestionsPanel.add(adminQuizConfirmBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 260, 100, 20));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel36.setText("QUESTIONS");
        QuestionsPanel.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, -1));

        adminNextBTN.setText("NEXT");
        adminNextBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminNextBTNActionPerformed(evt);
            }
        });
        QuestionsPanel.add(adminNextBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 80, -1));

        adminDeleteBTN.setText("DELETE");
        adminDeleteBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminDeleteBTNActionPerformed(evt);
            }
        });
        QuestionsPanel.add(adminDeleteBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 230, -1, -1));

        adminPrevBTN.setText("PREVIOUS");
        adminPrevBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminPrevBTNActionPerformed(evt);
            }
        });
        QuestionsPanel.add(adminPrevBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, -1, -1));

        adminCreateBTN.setText("CREATE");
        adminCreateBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminCreateBTNActionPerformed(evt);
            }
        });
        QuestionsPanel.add(adminCreateBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, -1, -1));

        adminUpdateBTN.setText("UPDATE");
        adminUpdateBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminUpdateBTNActionPerformed(evt);
            }
        });
        QuestionsPanel.add(adminUpdateBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 230, -1, -1));

        adminQuizCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Maths", "Geography", "Space", "Science" }));
        QuestionsPanel.add(adminQuizCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, -1, -1));

        adminSubmitTopicBTN.setText("SUBMIT TOPIC");
        adminSubmitTopicBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminSubmitTopicBTNActionPerformed(evt);
            }
        });
        QuestionsPanel.add(adminSubmitTopicBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 180, 130, -1));

        adminClearBTN.setText("CLEAR");
        adminClearBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminClearBTNActionPerformed(evt);
            }
        });
        QuestionsPanel.add(adminClearBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, -1, -1));

        TabbedResource1.addTab("Questions", QuestionsPanel);

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel35.setText("ANSWERS");

        adminQuizBG.add(adminABTN);
        adminABTN.setText("A");

        adminQuizBG.add(adminBBTN);
        adminBBTN.setText("B");
        adminBBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminBBTNActionPerformed(evt);
            }
        });

        adminQuizBG.add(adminCBTN);
        adminCBTN.setText("C");

        adminQuizBG.add(adminDBTN);
        adminDBTN.setText("D");

        adminSubmitAnswerBTN.setText("SUBMIT ANSWER");
        adminSubmitAnswerBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminSubmitAnswerBTNActionPerformed(evt);
            }
        });

        adminAnswerTA.setColumns(20);
        adminAnswerTA.setRows(5);
        adminAnswerTA.setText("Question will appear and admin will be able to change the answer using the radio buttons below\n");
        jScrollPane4.setViewportView(adminAnswerTA);

        javax.swing.GroupLayout AnswerPanelLayout = new javax.swing.GroupLayout(AnswerPanel);
        AnswerPanel.setLayout(AnswerPanelLayout);
        AnswerPanelLayout.setHorizontalGroup(
            AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AnswerPanelLayout.createSequentialGroup()
                .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AnswerPanelLayout.createSequentialGroup()
                        .addGap(262, 262, 262)
                        .addComponent(jLabel35))
                    .addGroup(AnswerPanelLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(AnswerPanelLayout.createSequentialGroup()
                                .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(adminCBTN)
                                        .addComponent(adminDBTN)
                                        .addComponent(adminBBTN, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(adminABTN))
                                .addGap(245, 245, 245)
                                .addComponent(adminSubmitAnswerBTN)))))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        AnswerPanelLayout.setVerticalGroup(
            AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AnswerPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adminSubmitAnswerBTN)
                    .addComponent(adminABTN))
                .addGap(9, 9, 9)
                .addComponent(adminBBTN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adminCBTN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adminDBTN)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        TabbedResource1.addTab("Answers", AnswerPanel);

        AdminQuizPanel.add(TabbedResource1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 720, 360));

        QuizPanel3.setBackground(new java.awt.Color(153, 153, 153));
        QuizPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(0, 0, 0), null));
        QuizPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel30.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        QuizPanel3.add(jPanel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 880, -1));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prototypetest/dragon (1).png"))); // NOI18N
        QuizPanel3.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel34.setText("Quiz Admin");
        QuizPanel3.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 210, 50));

        AdminQuizPanel.add(QuizPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 880, 440));

        jPanel31.setBackground(new java.awt.Color(255, 102, 102));
        jPanel31.setPreferredSize(new java.awt.Dimension(920, 180));

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        AdminQuizPanel.add(jPanel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, -1, -1));

        add(AdminQuizPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 550));
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
       LearningPanel.setVisible(true);
       HomePanel.setVisible(false);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void StartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartButtonActionPerformed
    HomePanel.setVisible(false);
       StartPanel.setVisible(false);
             LearningPanel.setVisible(false);
               CreateAccPanel.setVisible(true);
    }//GEN-LAST:event_StartButtonActionPerformed

    private void LBbackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBbackMouseClicked
         HomePanel.setVisible(true);
         LearningPanel.setVisible(false);
         
    }//GEN-LAST:event_LBbackMouseClicked

    private void BACKtoStartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BACKtoStartMouseClicked
       StartPanel.setVisible(true);
    }//GEN-LAST:event_BACKtoStartMouseClicked

    private void AccountNameInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccountNameInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AccountNameInputActionPerformed

    private void AccountIDInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccountIDInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AccountIDInputActionPerformed

    private void EmailInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailInputActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here://Main button after creating account
        CreateAccPanel.setVisible(false);
        HomePanel.setVisible(true);
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void EmailfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailfieldActionPerformed

    private void CFconfirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CFconfirmButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CFconfirmButtonActionPerformed

    private void ReadCRUDbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReadCRUDbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ReadCRUDbActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
       QuizPanel.setVisible(true);
       HomePanel.setVisible(false);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void QbackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QbackMouseClicked
        // TODO add your handling code here:
        HomePanel.setVisible(true);
        QuizPanel.setVisible(false);
    }//GEN-LAST:event_QbackMouseClicked

    private void cBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cBTNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cBTNActionPerformed

    private void BACKtoStart1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BACKtoStart1MouseClicked
        // TODO add your handling code here:
        CreateAccPanel.setVisible(true);
        AdminPanel.setVisible(false);
    }//GEN-LAST:event_BACKtoStart1MouseClicked

    private void jLabel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel25MouseClicked

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked
        // TODO add your handling code here:
        HomePanel.setVisible(false);
        StartPanel.setVisible(false);
        CreateAccPanel.setVisible(false);
        LearningPanel.setVisible(false);
        QuizPanel.setVisible(false);
        AdminPanel.setVisible(false);
        AdminQuizPanel.setVisible(true);
    }//GEN-LAST:event_jLabel27MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        HomePanel.setVisible(false);
        StartPanel.setVisible(false);
        CreateAccPanel.setVisible(false);
        LearningPanel.setVisible(false);
        QuizPanel.setVisible(false);
        AdminPanel.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void Qback1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Qback1MouseClicked
        // TODO add your handling code here:
        AdminPanel.setVisible(true);
        AdminQuizPanel.setVisible(false);
    }//GEN-LAST:event_Qback1MouseClicked

    private void adminQuizConfirmBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminQuizConfirmBTNActionPerformed
        // TODO add your handling code here:
        //method for admin quiz confirm button
         try {
        
        String correctOption = null;
        if (adminABTN.isSelected()) correctOption = "A";
        else if (adminBBTN.isSelected()) correctOption = "B";
        else if (adminCBTN.isSelected()) correctOption = "C";
        else if (adminDBTN.isSelected()) correctOption = "D";

        if (correctOption == null) {
            JOptionPane.showMessageDialog(this, "Please select the correct answer.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Question currentQuestion = adminCurrentQuestions.get(adminCurrentQuestionIndex);
        currentQuestion.setCorrectAnswer(correctOption);

        JOptionPane.showMessageDialog(this, "Correct answer updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_adminQuizConfirmBTNActionPerformed

    private void adminBBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminBBTNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adminBBTNActionPerformed

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
        HomePanel.setVisible(false);
        mainFrame1.setVisible(true);
        
    }//GEN-LAST:event_jPanel10MouseClicked

    private void quizPrevBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quizPrevBTNActionPerformed
        // TODO add your handling code here:
        //quiz previous button method
        saveAnswer();

    if (currentQuestionIndex > 0) {
        currentQuestionIndex--; 
        loadQuestion(); 
    } else {
        JOptionPane.showMessageDialog(this, "You are on the first question.", "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    }//GEN-LAST:event_quizPrevBTNActionPerformed

    private void quizNextBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quizNextBTNActionPerformed
        // TODO add your handling code here:
        //quiz next button method
        
        saveAnswer();

   
    if (currentQuestionIndex < currentQuestions.size() - 1) {
        currentQuestionIndex++; 
        loadQuestion(); 
    } else {
        JOptionPane.showMessageDialog(this, "You are on the last question.", "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    }//GEN-LAST:event_quizNextBTNActionPerformed

    private void quizSubmitBTN1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quizSubmitBTN1ActionPerformed
        //quiz submit button method
        if (currentQuestions == null || currentQuestions.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No questions loaded. Please select a topic.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Save the current answer
    saveAnswer();

    // Check if its the last question
    if (currentQuestionIndex == currentQuestions.size() - 1) {
        // Finish the quiz
        finishQuiz();
    } else {
        // Move to the next question
        currentQuestionIndex++;
        loadQuestion();
    }
    }//GEN-LAST:event_quizSubmitBTN1ActionPerformed

    private void submitTopicBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitTopicBTNActionPerformed
     
        //submit topic button method
        String selectedTopic = (String) topicCB.getSelectedItem();

    if (selectedTopic == null || selectedTopic.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please select a valid topic.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Load questions from the .dat file
    currentQuestions = Question.getQuestionsByTopic(selectedTopic);

    if (currentQuestions == null || currentQuestions.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No questions available for the selected topic.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Reset the question index
    currentQuestionIndex = 0;

    // Clear previous user answers
    userAnswers.clear();

    // Load the first question
    loadQuestion();

    // Enable quiz controls
    enableQuizControls();

    // Start the quiz timer
    startQuizTimer();
    }//GEN-LAST:event_submitTopicBTNActionPerformed

    private void adminNextBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminNextBTNActionPerformed
         //admin next button method
        if (adminCurrentQuestionIndex < adminCurrentQuestions.size() - 1) {
        adminCurrentQuestionIndex++;
        adminLoadQuestion();
    } else {
        JOptionPane.showMessageDialog(this, "You are on the last question.", "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    }//GEN-LAST:event_adminNextBTNActionPerformed

    private void adminPrevBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminPrevBTNActionPerformed
       //admin previous button method
        if (adminCurrentQuestionIndex > 0) {
        adminCurrentQuestionIndex--;
        adminLoadQuestion();
    } else {
        JOptionPane.showMessageDialog(this, "You are on the first question.", "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    }//GEN-LAST:event_adminPrevBTNActionPerformed

    private void adminUpdateBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminUpdateBTNActionPerformed
        //admin update button method   
        if (adminCurrentQuestionIndex >= 0 && adminCurrentQuestionIndex < adminCurrentQuestions.size()) {
        try {
            // Get the updated question text
            String updatedQuestionText = adminQuestionTA.getText().trim();

            if (updatedQuestionText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter the question text.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Get the current question object
            Question currentQuestion = adminCurrentQuestions.get(adminCurrentQuestionIndex);

            // Update the question text
            currentQuestion.setText(updatedQuestionText);

            // gets the user to input answers for each radio button
            String optionA = JOptionPane.showInputDialog(this, "Enter option A:", currentQuestion.getOptionA());
            String optionB = JOptionPane.showInputDialog(this, "Enter option B:", currentQuestion.getOptionB());
            String optionC = JOptionPane.showInputDialog(this, "Enter option C:", currentQuestion.getOptionC());
            String optionD = JOptionPane.showInputDialog(this, "Enter option D:", currentQuestion.getOptionD());

            // validate the inputs
            if (optionA == null || optionA.trim().isEmpty() ||
                optionB == null || optionB.trim().isEmpty() ||
                optionC == null || optionC.trim().isEmpty() ||
                optionD == null || optionD.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please provide all options.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update the options
            currentQuestion.setOptionA(optionA.trim());
            currentQuestion.setOptionB(optionB.trim());
            currentQuestion.setOptionC(optionC.trim());
            currentQuestion.setOptionD(optionD.trim());

            // Save the updated questions to the .dat file
            String selectedTopic = (String) adminQuizCB.getSelectedItem();
            Question.saveQuestionsToFile(selectedTopic, adminCurrentQuestions);

            // Refresh the admin UI 
            adminLoadQuestion();

            // success message
            JOptionPane.showMessageDialog(this, "Question updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "No question selected to update.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_adminUpdateBTNActionPerformed

    private void adminCreateBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminCreateBTNActionPerformed
        try {
        // Get the question text 
        String questionText = adminQuestionTA.getText().trim();

        if (questionText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the question text.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // gets the admin to put answers for the radio buttons
        String optionA = JOptionPane.showInputDialog(this, "Enter option A:");
        String optionB = JOptionPane.showInputDialog(this, "Enter option B:");
        String optionC = JOptionPane.showInputDialog(this, "Enter option C:");
        String optionD = JOptionPane.showInputDialog(this, "Enter option D:");

        // vlidate the inputs
        if (optionA == null || optionA.trim().isEmpty() ||
            optionB == null || optionB.trim().isEmpty() ||
            optionC == null || optionC.trim().isEmpty() ||
            optionD == null || optionD.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please provide all options.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create the new question with no correct answer set yet
        Question newQuestion = new Question(questionText, optionA.trim(), optionB.trim(), optionC.trim(), optionD.trim(), null);

        // Add the new question to the list
        adminCurrentQuestions.add(newQuestion);

        // Update the current question index to the new question
        adminCurrentQuestionIndex = adminCurrentQuestions.size() - 1;

        // Save the updated list to the .dat file
        String selectedTopic = (String) adminQuizCB.getSelectedItem();
        Question.saveQuestionsToFile(selectedTopic, adminCurrentQuestions);

        // Load the newly created question in the UI
        adminLoadQuestion();

        // Show success message
        JOptionPane.showMessageDialog(this, "New question created successfully. Please set the correct answer in the Answers tab.", "Success", JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_adminCreateBTNActionPerformed

    private void adminDeleteBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminDeleteBTNActionPerformed
       //admin delete button method
        if (adminCurrentQuestionIndex >= 0 && adminCurrentQuestionIndex < adminCurrentQuestions.size()) {
        try {
            // Confirm deletion 
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this question?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) {
                return; // User chose not to delete
            }

            // Remove the question from the list
            adminCurrentQuestions.remove(adminCurrentQuestionIndex);

            // Save the updated list to the file
            String selectedTopic = (String) adminQuizCB.getSelectedItem();
            Question.saveQuestionsToFile(selectedTopic, adminCurrentQuestions);

            // Adjust the currentQuestionIndex
            if (adminCurrentQuestions.isEmpty()) {
                adminCurrentQuestionIndex = -1;
            } else if (adminCurrentQuestionIndex >= adminCurrentQuestions.size()) {
                adminCurrentQuestionIndex = adminCurrentQuestions.size() - 1;
            }

            // Refresh the admin gui to show the next available question
            adminLoadQuestion();

            // delete success message
            JOptionPane.showMessageDialog(this, "Question deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occurred while deleting the question: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "No question selected to delete.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_adminDeleteBTNActionPerformed

    private void adminSubmitTopicBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminSubmitTopicBTNActionPerformed
       //admin submit topic method
        String selectedTopic = (String) adminQuizCB.getSelectedItem();

        System.out.println("Admin selected topic: " + selectedTopic);

        adminCurrentQuestions = Question.getQuestionsByTopic(selectedTopic);
        //error message if not questions are available
        if (adminCurrentQuestions == null || adminCurrentQuestions.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No questions available for the selected topic.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        System.out.println("Loaded " + adminCurrentQuestions.size() + " questions for topic: " + selectedTopic);

        // Reset index
        adminCurrentQuestionIndex = 0;

        // Load the first question
        adminLoadQuestion();

        // Enable the admin controls
        enableAdminControls();
    }//GEN-LAST:event_adminSubmitTopicBTNActionPerformed

    private void adminSubmitAnswerBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminSubmitAnswerBTNActionPerformed
        //admin submit answers buttons method
        if (adminCurrentQuestionIndex >= 0 && adminCurrentQuestionIndex < adminCurrentQuestions.size()) {
        try {
            String selectedAnswer = null;
            if (adminABTN.isSelected()) {
                selectedAnswer = "A";
            } else if (adminBBTN.isSelected()) {
                selectedAnswer = "B";
            } else if (adminCBTN.isSelected()) {
                selectedAnswer = "C";
            } else if (adminDBTN.isSelected()) {
                selectedAnswer = "D";
            } else {
                JOptionPane.showMessageDialog(this, "Please select the correct answer.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Set the correct answer in the Question object
            Question currentQuestion = adminCurrentQuestions.get(adminCurrentQuestionIndex);
            currentQuestion.setCorrectAnswer(selectedAnswer);

            // Save the updated questions to the .dat file
            String selectedTopic = (String) adminQuizCB.getSelectedItem();
            Question.saveQuestionsToFile(selectedTopic, adminCurrentQuestions);

            // Refresh the admin gui to show the correct answer
            adminLoadQuestion();

            // success message
            JOptionPane.showMessageDialog(this, "Correct answer set successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "No question selected to set the correct answer.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_adminSubmitAnswerBTNActionPerformed

    private void adminClearBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminClearBTNActionPerformed
        adminQuestionTA.setText("");
        adminQuizBG.clearSelection();
        adminABTN.setSelected(false);
        adminBBTN.setSelected(false);
        adminCBTN.setSelected(false);
        adminDBTN.setSelected(false);
    }//GEN-LAST:event_adminClearBTNActionPerformed

    private void stopTimerBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopTimerBTNActionPerformed
        // TODO add your handling code here:
         stopQuizTimer();
    JOptionPane.showMessageDialog(this, "The timer has been stopped at " + quizTimerLBL.getText(), "Timer Stopped", JOptionPane.INFORMATION_MESSAGE);
     stopTimerBTN.setEnabled(false);
    }//GEN-LAST:event_stopTimerBTNActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AccountIDInput;
    private javax.swing.JTextField AccountNameInput;
    private javax.swing.JPanel AdminPanel;
    private javax.swing.JPanel AdminQuizPanel;
    private javax.swing.JPanel AnswerPanel;
    public javax.swing.JLabel BACKtoStart;
    private javax.swing.JLabel BACKtoStart1;
    private javax.swing.JLabel Bicon;
    private javax.swing.JButton CFconfirmButton;
    private javax.swing.JPanel CategoryP;
    private javax.swing.JPanel ContactPanel;
    private javax.swing.JPanel CreateAccPanel;
    private javax.swing.JButton CreateCRUDb;
    private javax.swing.JButton DeleteCRUDb;
    private javax.swing.JTextField EmailInput;
    private javax.swing.JTextField Emailfield;
    public javax.swing.JPanel HomePanel;
    private javax.swing.JPanel IconP;
    private javax.swing.JLabel LBback;
    private javax.swing.JPanel LearnPanel;
    private javax.swing.JPanel LearningPanel;
    private javax.swing.JLabel LinkLabel;
    private javax.swing.JLabel Qback;
    private javax.swing.JLabel Qback1;
    private javax.swing.JTextArea QtArea;
    private javax.swing.JPanel QuestionsPanel;
    private javax.swing.JPanel QuizPanel;
    private javax.swing.JPanel QuizPanel1;
    private javax.swing.JPanel QuizPanel3;
    private javax.swing.JButton ReadCRUDb;
    private javax.swing.JPanel ResourceP;
    private javax.swing.JButton StartButton;
    private javax.swing.JPanel StartPanel;
    private javax.swing.JTabbedPane TabbedResource;
    private javax.swing.JTabbedPane TabbedResource1;
    private javax.swing.JButton UpdateCRUDb;
    private javax.swing.JRadioButton aBTN;
    private javax.swing.JRadioButton adminABTN;
    private javax.swing.JTextArea adminAnswerTA;
    private javax.swing.JRadioButton adminBBTN;
    private javax.swing.JRadioButton adminCBTN;
    private javax.swing.JButton adminClearBTN;
    private javax.swing.JButton adminCreateBTN;
    private javax.swing.JRadioButton adminDBTN;
    private javax.swing.JButton adminDeleteBTN;
    private javax.swing.JButton adminNextBTN;
    private javax.swing.JButton adminPrevBTN;
    private javax.swing.JTextArea adminQuestionTA;
    private javax.swing.ButtonGroup adminQuizBG;
    private javax.swing.JComboBox<String> adminQuizCB;
    private javax.swing.JButton adminQuizConfirmBTN;
    private javax.swing.JButton adminSubmitAnswerBTN;
    private javax.swing.JButton adminSubmitTopicBTN;
    private javax.swing.JButton adminUpdateBTN;
    private javax.swing.JRadioButton bBTN;
    private javax.swing.JRadioButton cBTN;
    private javax.swing.JRadioButton dBTN;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    public javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JToggleButton jToggleButton1;
    private GUIComponents.MainFrame mainFrame1;
    private javax.swing.ButtonGroup quizBG;
    private javax.swing.JButton quizNextBTN;
    private javax.swing.JButton quizPrevBTN;
    private javax.swing.JButton quizSubmitBTN1;
    private javax.swing.JTextArea quizTA1;
    private javax.swing.JLabel quizTimerLBL;
    private javax.swing.JButton stopTimerBTN;
    private javax.swing.JButton submitTopicBTN;
    private javax.swing.JPanel timerPanel;
    private javax.swing.JPanel topBar;
    private javax.swing.JComboBox<String> topicCB;
    // End of variables declaration//GEN-END:variables
}