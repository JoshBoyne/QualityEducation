package Main;

public class Goal {

    int streakGoal;
    int currentStreak;
    int currentTotal;

    public int getStreakGoal() {
        return streakGoal;
    }

    public int getCurrentStreak() {
        return currentStreak;
    }

    public int getCurrentTotal() {
        return currentTotal;
    }

    public void setStreakGoal(int goal) {
        this.streakGoal = goal;
    }

    public void update() {
        //Code here to update the streaks on login/quiz completion
    }
}