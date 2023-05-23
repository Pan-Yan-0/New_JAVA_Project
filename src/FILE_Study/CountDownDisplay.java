package FILE_Study;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountDownDisplay extends JFrame {

    private JLabel countdownLabel;
    private int counter;
    private Timer timer;

    public CountDownDisplay(int seconds) {
        counter = seconds;

        setTitle("倒计时");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        countdownLabel = new JLabel(String.valueOf(counter));
        countdownLabel.setFont(new Font("Arial", Font.BOLD, 48));
        add(countdownLabel);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter--;
                countdownLabel.setText(String.valueOf(counter));

                if (counter <= 0) {
                    timer.stop();
                }
            }
        });

        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CountDownDisplay(10).setVisible(true);
            }
        });
    }
}
