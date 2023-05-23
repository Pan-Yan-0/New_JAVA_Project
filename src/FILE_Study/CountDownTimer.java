package FILE_Study;

import java.util.Timer;
import java.util.TimerTask;

public class CountDownTimer {
    private Timer timer;
    private int secondsLeft;

    public CountDownTimer(int seconds) {
        timer = new Timer();
        secondsLeft = seconds;
    }

    public void start() {
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                if(secondsLeft > 0) {
                    secondsLeft--;
                    System.out.println(secondsLeft);
                } else {
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }

    public static void main(String[] args) {
        CountDownTimer timer = new CountDownTimer(60);
        timer.start();
    }
}