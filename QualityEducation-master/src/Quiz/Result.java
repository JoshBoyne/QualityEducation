/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quiz;

/**
 *
 * @author Josh
 */
public class Result extends QuizComponent{
    private int score;
    
    public Result(int score){
        this.score = score;
    }
    
    @Override
    public void displayComponent(){
        System.out.println("Your Score: "+score);
    }
    public int getScore(){
        return score;
    }
    public void setScore(int Score){
        this.score = score;
    }
    
}
