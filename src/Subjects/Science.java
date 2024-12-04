/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Subjects;

import java.io.Serializable;

/**
 *
 * @author Owen
 */
public class Science implements Serializable{
    
    private static final long serialVersionUID = 12345456789L;
    private int quizGoal;
    
    public Science(int quizGoal) {
     this.quizGoal = quizGoal;   
    }
    
    public int getQuizGoal() {
        return this.quizGoal;
    }
    
    public void setQuizGoal(int goal) {
            this.quizGoal = goal;
    }
}
