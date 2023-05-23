package FILE_Study;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CountDownDisplay extends JFrame {
    private JPanel mainPanel;
    private JLabel timeLabel;
    private Timer timer;
    private int secondsLeft;

    public CountDownDisplay(int seconds) {
        super("倒计时器");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 150);
        mainPanel = new JPanel();
        timeLabel = new JLabel(seconds + "秒");
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        timeLabel.setForeground(Color.RED);
        mainPanel.add(timeLabel);
        add(mainPanel, BorderLayout.CENTER);
        timer = new Timer();
        secondsLeft = seconds;
    }

    public void start() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (secondsLeft > 0) {
                    secondsLeft--;
                    timeLabel.setText(secondsLeft + "秒");
                } else {
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }

    public static void main(String[] args) {
        CountDownDisplay display = new CountDownDisplay(60);
        display.setVisible(true);
        display.start();
    }
}