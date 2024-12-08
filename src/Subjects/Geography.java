package Subjects;

import java.io.Serializable;

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
