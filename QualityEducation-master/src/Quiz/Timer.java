/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quiz;

/**
 *
 * @author Josh
 */
public class Timer {
    
    private int timeLeft;
    
    public Timer(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void decrementTime() {
        if (timeLeft > 0) {
            timeLeft--;
        }
    }

    public void resetTime(int time) {
        this.timeLeft = time;
    }
    
}
