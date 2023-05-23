package FILE_Study;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
    private JComboBox<String> comboBox;

    public MyFrame() {
        setTitle("My Frame");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建JComboBox并添加到窗口中
        comboBox = new JComboBox<>();
        comboBox.setBounds(100, 100, 200, 30);
        comboBox.addItem("Option 1");
        comboBox.addItem("Option 2");
        comboBox.addItem("Option 3");
        add(comboBox);

        // 为JComboBox添加ActionListener监听器
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 选项已更改，执行相应的操作
                System.out.println("Selected item is " + comboBox.getSelectedItem());
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
    }
}