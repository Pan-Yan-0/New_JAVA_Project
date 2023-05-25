package SYSTEM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ForgetPasswordJFrame extends JFrame implements KeyListener, MouseListener, ActionListener {

    //6.添加注册按钮
    JButton register = new JButton();
    //存储用户的信息
    ArrayList<Student> students = new ArrayList<Student>();
    //显示验证码的框框
    JLabel rightCode = new JLabel("", JLabel.CENTER);
    String codeStr;
    //2.添加用户名输入框
    JTextField username = new JTextField();
    //4.密码输入框
    JPasswordField password = new JPasswordField();
    //再次输入密码
    JPasswordField passwordRepeat = new JPasswordField();
    //5.手机号码输入框
    JTextField phone = new JTextField();
    Student forget;
    public ForgetPasswordJFrame(Student forget) {
        this.forget = forget;
        initJrame();

        initText();
        initUser();
        this.setVisible(true);
    }
    private void initUser() {
        File f = new File("src/SYSTEM/Status");
        try {
            Scanner scanner = new Scanner(f);

            while (scanner.hasNextLine()) {
                Student s = new Student(scanner.next(), scanner.next(), scanner.nextLong());
                students.add(s);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在！！");
        }
        System.out.println("数据处理完成");
    }
    private void initJrame() {
        //设置主界面的参数
        this.setSize(680, 580);

        //设置页面居中
        this.setLocationRelativeTo(null);

        //设置界面置顶
        this.setAlwaysOnTop(true);

        //设置主界面的标题
        this.setTitle("忘记密码");

        //设置关闭界面的方法
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //取消界面的居中方式
        this.setLayout(null);


    }
    private void outputStatus() {
        try {
            FileWriter fileWriter = new FileWriter("src/SYSTEM/Status");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            int length = students.size();
            for (int i = 0; i < length - 1; i++) {
                bufferedWriter.write(students.get(i).getUserName() + " " + students.get(i).getUserPassword() + " "
                        + students.get(i).getPhone());
                bufferedWriter.newLine();
            }
            if (length!=0){
                bufferedWriter.write(students.get(length-1).getUserName() + " " + students.get(length-1).getUserPassword() + " "
                        + students.get(length-1).getPhone());
                bufferedWriter.close();
            }
            System.out.println("文件已写入成功！");
        } catch (IOException e) {
            System.err.println("文件写入失败：" + e.getMessage());
        }

    }
    private void initText() {
        //学生四则运算训练系统
        JLabel titleJLabel = new JLabel("学生四则运算训练系统", JLabel.CENTER);
        titleJLabel.setBounds(200, 50, 300, 40);
        titleJLabel.setFont(new Font("微软雅黑", Font.BOLD, 30));
        this.getContentPane().add(titleJLabel);
        //1. 添加用户名文字
        JLabel phoneText = new JLabel("注册时的手机号码: ", JLabel.RIGHT);
        phoneText.setBounds(40, 135, 190, 25);
        phoneText.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(phoneText);

        //输入user的框架构建
        phone.setBounds(240, 134, 200, 30);
        phone.setFont(new Font("微软雅黑", Font.ROMAN_BASELINE, 20));
        phone.addKeyListener(this);
        this.getContentPane().add(phone);

        //3.添加密码文字
        JLabel passwordText = new JLabel("新密码: ", JLabel.RIGHT);
        passwordText.setBounds(150, 195, 80, 25);
        passwordText.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(passwordText);

        //输入密码的框架
        password.setBounds(240, 195, 200, 30);
        password.setFont(new Font("微软雅黑", Font.BOLD, 20));
        password.addKeyListener(this);
        this.getContentPane().add(password);

        //3.添加重复密码文字
        JLabel passwordRepeatText = new JLabel("再次输入密码: ", JLabel.RIGHT);
        passwordRepeatText.setBounds(90, 256, 140, 25);
        passwordRepeatText.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(passwordRepeatText);

        //输入重复密码的框架
        passwordRepeat.setBounds(240, 256, 200, 30);
        passwordRepeat.setFont(new Font("微软雅黑", Font.BOLD, 20));
        passwordRepeat.addKeyListener(this);
        this.getContentPane().add(passwordRepeat);


        //注册框架的构建
        register.setText("确定");
        register.setFont(new Font("微软雅黑", Font.BOLD, 20));
        register.setBounds(250, 320, 128, 34);
        register.addActionListener(this);
        this.getContentPane().add(register);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source==register){
            if (Long.parseLong(phone.getText()) != forget.getPhone()){
                JOptionPane phoneError = new JOptionPane("您输入的电话号码不匹配");
                JDialog dialog = phoneError.createDialog("The phone error");
                dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                dialog.setAlwaysOnTop(true); // 将窗口置于其他窗口之前
                dialog.setVisible(true);
                phone.setText("");
            } else if (!(password.getText().equals(passwordRepeat.getText()))) {
                JOptionPane passwordError = new JOptionPane("您输入的两次密码不一致");
                JDialog dialog = passwordError.createDialog("The password error");
                dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                dialog.setAlwaysOnTop(true); // 将窗口置于其他窗口之前
                dialog.setVisible(true);
                password.setText("");
                passwordRepeat.setText("");
            }else {
                outputStatus();
                JOptionPane succeed = new JOptionPane("已经重置密码成功");
                JDialog dialog = succeed.createDialog("success！！");
                dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                dialog.setAlwaysOnTop(true); // 将窗口置于其他窗口之前
                dialog.setVisible(true);
                new LoginJFrame();
                dispose();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
