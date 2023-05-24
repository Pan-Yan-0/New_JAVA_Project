package FILE_Study;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test8 extends JFrame {
    private JLabel timeJLabel;
    private Timer timer;
    private int second;
    private int count;
    public static void main(String[] args) {
        Test8 test8 = new Test8(1);
        test8.setVisible(true);
    }

    public Test8(int second) throws HeadlessException {
        initJrame();
        this.second = second;
        count = second;
        timeJLabel = new JLabel(String.valueOf(count));
        timeJLabel.setBounds(10,10,200,200);
        timeJLabel.setFont(new Font("Arial", Font.BOLD, 48));
        timeJLabel.setVisible(true);
        this.getContentPane().add(timeJLabel);
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count--;
                timeJLabel.setText(String.valueOf(count));

                if (count==0){
                    timer.stop();
                }
            }
        });
        timer.start();

        this.getContentPane().setVisible(true);
    }

    private void initJrame() {
        //设置主界面的参数
        this.setSize(1200, 580);

        //设置页面居中
        this.setLocationRelativeTo(null);

        //设置界面置顶
        this.setAlwaysOnTop(true);

        //设置主界面的标题
        this.setTitle("训练系统  v1.0");

        //设置关闭界面的方法
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消界面的居中方式
        this.setLayout(null);

    }
}
