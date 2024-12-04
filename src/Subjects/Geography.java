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
public class Geography implements Serializable{
    private static final long serialVersionUID = 1465436389L;
    private int quizGoal;
    
    public Geography(int quizGoal) {
     this.quizGoal = quizGoal;   
    }
    
    public int getQuizGoal() {
        return this.quizGoal;
    }
    
    public void setQuizGoal(int goal) {
            this.quizGoal = goal;
    }
}
