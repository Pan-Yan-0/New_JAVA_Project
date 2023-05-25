package SYSTEM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class LoginJFrame extends JFrame implements KeyListener, ActionListener, MouseListener {
    //存储已经登录用户的信息
    static ArrayList<Student> LoginUser = new ArrayList<Student>();
    //存储用户的信息
    ArrayList<Student> students = new ArrayList<Student>();
    //显示验证码的框框
    JLabel rightCode = new JLabel("", JLabel.CENTER);
    String codeStr;
    //2.添加用户名输入框
    JTextField username = new JTextField();
    //4.密码输入框
    JPasswordField password = new JPasswordField();

    //验证码的输入框
    JTextField code = new JTextField();
    //添加登录的按钮
    JButton login = new JButton();
    //6.添加注册按钮
    JButton register = new JButton();
    JButton fullLoginJButton = new JButton();

    public LoginJFrame() {
        initJrame();
        initUser();
        initText();
        JOptionPane padomError = new JOptionPane("如果您忘记了密码，请先尝试登录一遍");
        JDialog dialog = padomError.createDialog("The Remain of ForgetPassword ");
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setAlwaysOnTop(true); // 将窗口置于其他窗口之前
        dialog.setVisible(true);
        //显示界面
        this.setVisible(true);

    }

    private void outputStatus() {
        try {
            FileWriter fileWriter = new FileWriter("src/SYSTEM/Status");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            int length = students.size();
            for (int i = 0; i < length - 1; i++) {
                bufferedWriter.write(students.get(i).getUserName() + " " + students.get(i).getUserPassword() + " " + students.get(i).getPhone());
                bufferedWriter.newLine();
            }
            bufferedWriter.write(students.get(length - 1).getUserName() + " " + students.get(length - 1).getUserPassword() + " " + students.get(length - 1).getPhone());
            bufferedWriter.close();

            System.out.println("文件已写入成功！");
        } catch (IOException e) {
            System.err.println("文件写入失败：" + e.getMessage());
        }
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

    private void forgetPassword(String userName) {
        long userPhone = 0;
        int index = 0;
        for (int i = 0; i < students.size(); i++) {
            if (userName.equals(students.get(i).getUserName())) {
                userPhone = students.get(i).getPhone();
                index = i;
            }
        }
        Scanner sc = new Scanner(System.in);
        long phone;
        while (true) {
            System.out.println("请输入您注册时的手机号码");
            phone = sc.nextLong();
            if (phone == userPhone) {
                //开始修改密码
                String password;
                while (true) {
                    System.out.println("请输入您的密码");
                    password = sc.next();
                    System.out.println("请再次输入您的密码");
                    String repeatmPassword = sc.next();
                    if (password.equals(repeatmPassword)) {
                        students.get(index).setUserPassword(password);
                        System.out.println("修改密码成功，请牢记您的密码哦");
                        break;
                    } else {
                        System.out.println("第一次输入的密码和第二次输入的密码不一致，请重新再次输入");
                    }
                }
                students.get(index).setUserPassword(password);
                break;
            } else {
                System.out.println("电话号码不匹配");
            }
        }

    }

    //注册界面
    private void Register() {
        //开始读取文件数据，创建Student类数组
        Scanner sc = new Scanner(System.in);
        Student newStudent = new Student();
        String userName;
        while (true) {
            System.out.println("请输入您的用户名");
            userName = sc.next();
            boolean judge = false;
            for (Student student : students) {
                if (student.getUserName().equals(userName)) {
                    System.out.println("已经有该用户");
                    judge = true;
                }
            }
            if (!judge) {
                break;
            }
        }
        newStudent.setUserName(userName);
        String password;
        while (true) {
            System.out.println("请输入您的密码");
            password = sc.next();
            System.out.println("请再次输入您的密码");
            String repeatmPassword = sc.next();
            if (password.equals(repeatmPassword)) {
                break;
            } else {
                System.out.println("第一次输入的密码和第二次输入的密码不一致，请重新再次输入");
            }
        }
        newStudent.setUserPassword(password);
        long phone;
        while (true) {
            System.out.println("请输入您的手机号码");
            phone = sc.nextLong();
            if ((long) (Math.log10(phone)) != 10) {
                System.out.println("手机号码应为11位，请您重新输入一遍");
            } else {
                break;
            }
        }
        newStudent.setPhone(phone);
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
        this.getContentPane().add(username);
        username.addKeyListener(this);
        //3.添加密码文字
        JLabel passwordText = new JLabel("密码: ", JLabel.RIGHT);
        passwordText.setBounds(150, 195, 80, 25);
        passwordText.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(passwordText);

        //输入密码的框架
        password.setBounds(240, 195, 200, 30);
        password.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(password);

        //验证码提示
        JLabel codeText = new JLabel("验证码: ", JLabel.RIGHT);
        codeText.setBounds(150, 256, 80, 30);
        codeText.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(codeText);

        //输入验证码的输入框架
        code.setBounds(240, 256, 100, 30);
        code.setFont(new Font("微软雅黑", Font.ROMAN_BASELINE, 20));
        this.getContentPane().add(code);

        extracted();
        //添加点击即切换验证码界面
        rightCode.addMouseListener(this);
        //5.添加登录按钮
        login.setText("登录");
        login.setBounds(123, 310, 128, 34);

        login.setFont(new Font("微软雅黑", Font.BOLD, 20));
        login.addActionListener(this);
//        //去除按钮的默认边框
//        login.setBorderPainted(false);
//        //去除按钮的默认背景
//        login.setContentAreaFilled(false);
        this.getContentPane().add(login);

        //注册框架的构建
        register.setText("注册");
        register.setFont(new Font("微软雅黑", Font.BOLD, 20));
        register.setBounds(350, 310, 128, 34);
        register.addActionListener(this);
        this.getContentPane().add(register);

        //登录所有用户的构建
        fullLoginJButton.setText("登录所有用户");
        fullLoginJButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
        fullLoginJButton.setBounds(215, 360, 180, 34);
        fullLoginJButton.addActionListener(this);
        this.getContentPane().add(fullLoginJButton);
    }

    //重新显示一次验证码，并且改变验证码
    private void extracted() {
        //构建验证码
        StringBuilder stringBuilder = new StringBuilder();
        Random r = new Random();
        String arr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String arr1 = "1234567890";
        for (int i = 0; i < 4; i++) {
            int num = r.nextInt(arr.length());
            stringBuilder.append(arr.charAt(num));
        }
        stringBuilder.append(arr1.charAt(r.nextInt(arr1.length())));
        codeStr = new String(stringBuilder);

        //设置内容
        rightCode.setText(codeStr);
        //位置和宽高
        rightCode.setBounds(350, 256, 100, 30);
        //设置字体
        rightCode.setFont(new Font("微软雅黑", Font.ROMAN_BASELINE, 20));
        //设置框框
        rightCode.setBackground(Color.WHITE);
        rightCode.setOpaque(true);
        //添加到界面
        this.getContentPane().add(rightCode);
    }

    private void initJrame() {
        //设置主界面的参数
        this.setSize(680, 580);

        //设置页面居中
        this.setLocationRelativeTo(null);

        //设置界面置顶
        this.setAlwaysOnTop(true);

        //设置主界面的标题
        this.setTitle("登录");

        //设置关闭界面的方法
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //取消界面的居中方式
        this.setLayout(null);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == login) {
            LoginJudge();
            new LoginJFrame();
        } else if (source == register) {
            new RegistJFrame();
            dispose();
        } else if (source == fullLoginJButton) {
            if (students.size()<3){
                System.out.println("不足三人");
                JOptionPane fullLoginError = new JOptionPane("当前注册的用户注册不足人数，无法执行");
                JDialog dialog = fullLoginError.createDialog("The fullLogin error");
                dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                dialog.setAlwaysOnTop(true); // 将窗口置于其他窗口之前
                dialog.setVisible(true);
                return;
            }
            for (Student student : students) {
                Thread t = new Thread(() -> {
                    System.out.println(student.getUserName() + "申请登录！！");
                    if (newBegin(student)) {
                        new GameJFrame(student);
                    }

                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                t.start();
            }
        }
    }

    private void LoginJudge() {
        int index = -1;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getUserName().equals(username.getText())) {
                index = i;
            }
        }
        if (index == -1) {
            JOptionPane userNameError = new JOptionPane("没有这个用户名");
            JDialog dialog = userNameError.createDialog("The code error");
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setAlwaysOnTop(true); // 将窗口置于其他窗口之前
            dialog.setVisible(true);
            username.setText("");
            password.setText("");
        } else {
            if (!newBegin(students.get(index))) {
                return;
            }
            if (!(password.getText().equals(students.get(index).getUserPassword()))) {
                int choice = JOptionPane.showOptionDialog(JOptionPane.getRootFrame(), "密码错误，需要找回密码吗？", "密码错误",
                        JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"重新输入", "找回密码"},
                        "重新输入");
                if (choice == JOptionPane.NO_OPTION) {
                    new ForgetPasswordJFrame(students.get(index));
                    dispose();
                }
                password.setText("");
            } else if (!(code.getText().equalsIgnoreCase(codeStr))) {
                JOptionPane codeError = new JOptionPane("验证码错误");
                JDialog dialog = codeError.createDialog("The code error");
                dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                dialog.setAlwaysOnTop(true); // 将窗口置于其他窗口之前
                dialog.setVisible(true);
                code.setText("");
            } else {
                JOptionPane codeError = new JOptionPane("登录成功");
                JDialog dialog = codeError.createDialog("Succeed");
                dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                dialog.setAlwaysOnTop(true); // 将窗口置于其他窗口之前
                dialog.setVisible(true);
                code.setText("");
                dispose();
                GameJFrame gameJFrame = new GameJFrame(students.get(index));
            }
        }
    }

    private boolean newBegin(Student student) {
        synchronized (LoginUser) {
            for (Student s : LoginUser) {
                if (s.getUserName().equals(student.getUserName())) {
                    System.out.println("12312312312");
                    JOptionPane padomError = new JOptionPane(student.getUserName() + "已经登录！！");
                    JDialog dialog = padomError.createDialog("The Login error");
                    dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    dialog.setAlwaysOnTop(true); // 将窗口置于其他窗口之前
                    dialog.setVisible(true);
                    username.setText("");
                    password.setText("");
                    return false;
                }
            }
            if (LoginUser.size() < 3) {
                LoginUser.add(student); // 将学生数据添加到列表
                System.out.println(student.getUserName() + "登录成功！！");
            } else {
                JOptionPane loginError = new JOptionPane(student.getUserName() + "登录人数已满！！");
                JDialog dialog = loginError.createDialog("The Login error");
                dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                dialog.setAlwaysOnTop(true); // 将窗口置于其他窗口之前
                dialog.setVisible(true);
                System.out.println(student.getUserName() + "登录人数已满！");
                return false;
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return true;
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
        Object source = e.getSource();
        if (source == rightCode) {
            extracted();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
