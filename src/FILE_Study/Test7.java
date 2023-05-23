package FILE_Study;

import javax.swing.*;

public class Test7 extends JFrame{
    public Test7() {
        initJrame();
        for (int i = 0; i < 10; i++) {
            JLabel j = new JLabel("try");
            j.setBounds(20+i*10,30+i*10,50,10);
            this.getContentPane().add(j);
        }

        //
        this.setVisible(true);
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
        //设置界面


    }
}
