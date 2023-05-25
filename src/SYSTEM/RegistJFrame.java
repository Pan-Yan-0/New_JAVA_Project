package SYSTEM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class RegistJFrame extends JFrame implements ActionListener, KeyListener, MouseListener {
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

    Student newUser = new Student();

    public RegistJFrame() {
        initJrame();
        initText();
        initUser();
        //显示框架
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
        this.setTitle("注册");

        //设置关闭界面的方法
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //取消界面的居中方式
        this.setLayout(null);


    }

    private void outputStatus() {
        try {
            FileWriter fileWriter = new FileWriter("src/SYSTEM/Status", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            if (!students.isEmpty()) {
                bufferedWriter.newLine();
            }
            bufferedWriter.write(newUser.getUserName() + " " + newUser.getUserPassword() + " " + newUser.getPhone());
            bufferedWriter.close();
            //创建成功
            System.out.println("创建用户成功！！！");
            System.out.println("正在返回登录界面");
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
        JLabel usernameText = new JLabel("用户名: ", JLabel.RIGHT);
        usernameText.setBounds(150, 135, 80, 25);
        usernameText.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(usernameText);

        //输入user的框架构建
        username.setBounds(240, 134, 200, 30);
        username.setFont(new Font("微软雅黑", Font.ROMAN_BASELINE, 20));
        username.addKeyListener(this);
        this.getContentPane().add(username);

        //3.添加密码文字
        JLabel passwordText = new JLabel("密码: ", JLabel.RIGHT);
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

        //3.添加手机号码文字
        JLabel phoneText = new JLabel("手机号码: ", JLabel.RIGHT);
        phoneText.setBounds(90, 317, 140, 25);
        phoneText.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(phoneText);

        //输入手机号码的框架
        phone.setBounds(240, 317, 200, 30);
        phone.setFont(new Font("微软雅黑", Font.BOLD, 20));
        phone.addKeyListener(this);
        this.getContentPane().add(phone);

        //注册框架的构建
        register.setText("注册");
        register.setFont(new Font("微软雅黑", Font.BOLD, 20));
        register.setBounds(250, 400, 128, 34);
        register.addActionListener(this);
        this.getContentPane().add(register);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == register) {
            for (Student s : students) {
                if (s.getUserName().equals(username.getText()) || username.getText().isEmpty()) {
                    JOptionPane usernameError = new JOptionPane("用户名已存在或者为空");
                    JDialog dialog = usernameError.createDialog("The userName error");
                    dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    dialog.setAlwaysOnTop(true); // 将窗口置于其他窗口之前
                    dialog.setVisible(true);
                    username.setText("");
                }
            }

            if (!(password.getText().equals(passwordRepeat.getText())) || password.getText().isEmpty()) {
                JOptionPane passwordError = new JOptionPane("您输入的两次密码不一致或者为空");
                JDialog dialog = passwordError.createDialog("The password error");
                dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                dialog.setAlwaysOnTop(true); // 将窗口置于其他窗口之前
                dialog.setVisible(true);
                password.setText("");
                passwordRepeat.setText("");
            } else if (phone.getText().length() != 11) {
                JOptionPane phoneError = new JOptionPane("您输入的手机号码格式错误");
                JDialog dialog = phoneError.createDialog("The password error");
                dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                dialog.setAlwaysOnTop(true); // 将窗口置于其他窗口之前
                dialog.setVisible(true);
                phone.setText("");
            } else {
                newUser.setUserName(username.getText());
                newUser.setUserPassword(password.getText());
                newUser.setPhone(Long.parseLong(phone.getText()));
                JOptionPane passwordError = new JOptionPane("注册成功");
                JDialog dialog = passwordError.createDialog("Succeed");
                dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                dialog.setAlwaysOnTop(true); // 将窗口置于其他窗口之前
                dialog.setVisible(true);
                outputStatus();
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
