package SYSTEM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.TimerTask;


public class GameJFrame extends JFrame implements MouseListener, KeyListener, ActionListener {
    File RecodeStatus;
    int[] inputAnswerArr = new int[10];
    int[] inputModuloArr = new int[10];
    //秒
    int seconds = 120;
    //显示是否正确数组
    JLabel[] right = new JLabel[10];
    //显示答案数组
    JLabel[] answerShow = new JLabel[10];
    //问题数组
    JLabel[] problemJLabel = new JLabel[10];
    //
    //输入答案框架
    JTextField[] inputAnswer = new JTextField[10];
    //输入余数框架
    JTextField[] inputModulo = new JTextField[10];
    //总分
    int count = 0;
    String[] countCase = {"满分", "优秀", "良好", "及格", "不及格"};
    //答题情况
    boolean[] ansRight = new boolean[10];
    //运算符的选择
    String[] operatorArr = {"混合运算", "加法", "减法", "乘法", "除法"};
    JComboBox chooseOperator = new JComboBox(operatorArr);
    /*
     * 运算符选择符号
     * 1 是混合运算的意思
     * 5 是除法运算
     * */
    int operator = 1;
    //位数的选择
    String[] TonnageArr = {"一位", "两位", "三位", "四位"};
    JComboBox chooseTonnage = new JComboBox(TonnageArr);
    //运算位数
    /*
     * 注意 1 是一位数的意思
     * 2 才是两位数的意思
     * */
    int tonnage = 1;
    //数据存储
    //第一个数字
    int[] num1Arr = new int[10];
    //第二个数字
    int[] num2Arr = new int[10];
    //运算符号
    char[] strArr = new char[10];
    //答案
    int[] ansArr = new int[10];
    //余数答案
    int[] ansModuloArr = new int[10];
    //用户的姓名
    Student User;
    //创建功能的项目栏
    JMenuItem restart = new JMenuItem("重做");
    JMenuItem Recode = new JMenuItem("查看历史记录");
    JMenuItem exit = new JMenuItem("退出系统");
    JMenuItem otherExam = new JMenuItem("另做一套");
    JMenu contactOur = new JMenu("关于我们");
    JButton submitButton = new JButton("提交答案");
    //定义好用户的历史记录的文件路径
    private String road = "D:\\JavaProject\\Little_Student_Math\\src\\SYSTEM\\RecodeStatus\\";
    //倒计时的数据
    private JLabel countdownLabel;
    private int counter;
    private Timer timer;

    public GameJFrame(Student User) {
        this.User = User;
        road += User.getUserName();
        initRecodeStatus();
        //初始框架
        initJrame();
        //初始菜单
        initMenu();
        //初始数据（即弄计算题）
        initData();
        //初始所有主界面
        initText();

        //显示应该放在最后
        this.setVisible(true);

    }

    public static boolean judge(int num1, int num2, int choose, int ans) {
        switch (choose) {
            case 0 -> {
                if (num1 + num2 == ans) {
                    return true;
                } else {
                    System.out.println("答案应为：" + (num1 + num2));
                    return false;
                }
            }
            case 1 -> {
                if (num1 - num2 == ans) {
                    return true;
                } else {
                    System.out.println("答案应为：" + (num1 - num2));
                    return false;
                }
            }
            case 2 -> {
                if (num1 * num2 == ans) {
                    return true;
                } else {
                    System.out.println("答案应为：" + (num1 * num2));
                    return false;
                }
            }
            case 3 -> {
                if (num1 / num2 == ans) {
                    return true;
                } else {
                    System.out.println("答案应为：" + (num1 / num2));
                    return false;
                }
            }
            default -> {
                break;
            }
        }
        return true;
    }

    private void initRecodeStatus() {
        RecodeStatus = new File(road);
        if (!RecodeStatus.exists()) {
            try {
                RecodeStatus.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void initText() {
        CountDownDisplay();
        //提交按钮
        submitButton.setBounds(1000, 10, 150, 34);
        submitButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
        submitButton.addActionListener(this);
        this.getContentPane().add(submitButton);

        //用户姓名显示器  运算位数
        JLabel userName = new JLabel("考生姓名：" + User.getUserName());
        userName.setBounds(250, 10, 200, 30);
        userName.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(userName);

        //运算类型
        JLabel operator = new JLabel("运算类型为：");
        operator.setBounds(450, 10, 200, 30);
        operator.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(operator);

        //运算类型选择
        chooseOperator.setBounds(570, 10, 100, 30);
        chooseOperator.setFont(new Font("微软雅黑", Font.BOLD, 16));
        chooseOperator.addActionListener(this);
        this.getContentPane().add(chooseOperator);

        //运算位数
        JLabel Tonnage = new JLabel("请选择运算位数：");
        Tonnage.setBounds(700, 10, 200, 30);
        Tonnage.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(Tonnage);

        //运算位数选择
        chooseTonnage.setBounds(860, 10, 100, 30);
        chooseTonnage.setFont(new Font("微软雅黑", Font.BOLD, 16));
        chooseTonnage.addActionListener(this);

        this.getContentPane().add(chooseTonnage);

        //输入答案 1
        JLabel answerJLabl1 = new JLabel("输入答案");
        answerJLabl1.setBounds(250, 70, 100, 30);
        answerJLabl1.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(answerJLabl1);
        //余数 1
        JLabel ModuloJLabel1 = new JLabel("余数");
        ModuloJLabel1.setBounds(420, 70, 100, 30);
        ModuloJLabel1.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(ModuloJLabel1);
        //输入答案 2
        JLabel answerJLabl2 = new JLabel("输入答案");
        answerJLabl2.setBounds(760, 70, 100, 30);
        answerJLabl2.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(answerJLabl2);
        //余数 2
        JLabel ModuloJLabel2 = new JLabel("余数");
        ModuloJLabel2.setBounds(930, 70, 100, 30);
        ModuloJLabel2.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(ModuloJLabel2);
        //显示题目

        //题目一
        for (int i = 0; i < 10; i++) {
            JLabel problem = new JLabel(num1Arr[i] + " " + strArr[i] + " " + num2Arr[i] + " = ", JLabel.RIGHT);
            problem.setBounds(20 + (i / 5) * 500, 100 + (i % 5) * 70, 230, 30);
            problem.setFont(new Font("微软雅黑", Font.BOLD, 20));
            problemJLabel[i] = problem;

            this.getContentPane().add(problem);
        }

        for (int i = 0; i < 10; i++) {
            JTextField inputanswer = new JTextField();
            inputanswer.setBounds(250 + (i / 5) * 500, 100 + (i % 5) * 70, 100, 30);
            inputanswer.setFont(new Font("微软雅黑", Font.BOLD, 20));
            inputanswer.addKeyListener(this);
            JTextField inputmodulo = new JTextField();
            inputmodulo.setBounds(400 + (i / 5) * 500, 100 + (i % 5) * 70, 100, 30);
            inputmodulo.setFont(new Font("微软雅黑", Font.BOLD, 20));
            inputmodulo.addKeyListener(this);
            inputModulo[i] = inputmodulo;
            inputAnswer[i] = inputanswer;
            this.getContentPane().add(inputanswer);
            this.getContentPane().add(inputmodulo);
        }

    }

    private void initData() {
        Random r = new Random();
        int count = 0;//记录总分
        char[] str = {'+', '-', '*', '/'};
        for (int i = 0; i < 10; i++) {
            if (operator == 1) {
                //生成运算符号
                int choose = r.nextInt(4);
                char ch = str[choose];
                strArr[i] = ch;
            } else {
                strArr[i] = str[operator - 2];
            }
        }

        for (int i = 0; i < 10; i++) {
            //生成数字
            //这里得注意，除法中的除数是不可以为 0 的
            int num1, num2;

            num1 = r.nextInt((int) Math.pow(10, tonnage));
            num2 = r.nextInt((int) Math.pow(10, tonnage));
            if (num1 < num2) {
                int temp = num1;
                num1 = num2;
                num2 = temp;
            }
            while (strArr[i] == '/' && (num2 == 0||num1==0)) {
                num2 = r.nextInt(9) + 1;
                num1 = r.nextInt(9) + 1;
            }
            if (num1 < num2) {
                int temp = num1;
                num1 = num2;
                num2 = temp;
            }
            num1Arr[i] = num1;
            num2Arr[i] = num2;

            switch (strArr[i]) {
                case '+' -> {
                    ansArr[i] = num1 + num2;
                    ansModuloArr[i] = 0;
                }
                case '-' -> {
                    ansArr[i] = num1 - num2;
                    ansModuloArr[i] = 0;
                }
                case '*' -> {
                    ansArr[i] = num1 * num2;
                    ansModuloArr[i] = 0;
                }
                case '/' -> {
                    ansArr[i] = num1 / num2;
                    ansModuloArr[i] = num1 - num2 * ansArr[i];
                }
                default -> {
                    break;
                }
            }

        }

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

    //初始化菜单
    private void initMenu() {
        //创建菜单栏
        JMenuBar jMenuBar = new JMenuBar();
        //创建菜单项目
        JMenu FunctionMenu = new JMenu("功能");


        //添加事件代码
        restart.addMouseListener(this);
        Recode.addMouseListener(this);
        exit.addMouseListener(this);
        otherExam.addMouseListener(this);

        //对功能区的项目进行添加
        FunctionMenu.add(restart);
        FunctionMenu.add(Recode);
        FunctionMenu.add(exit);
        FunctionMenu.add(otherExam);


        //将所有的导航栏添加到主栏当中
        jMenuBar.add(FunctionMenu);
        jMenuBar.add(contactOur);
        this.setJMenuBar(jMenuBar);
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
        if (source == restart) {
            this.getContentPane().removeAll();
            //执行重开代码
            submitButton.setEnabled(true);
            initText();
            repaint();
        } else if (source == otherExam) {
            this.getContentPane().removeAll();
            initData();
            initText();
            submitButton.setEnabled(true);
            repaint();
        } else if (source == Recode) {
            showRecodeStatus();
            System.out.println("执行到了点击事件");
        } else if (source == exit) {
            //执行直接退出系统
            System.exit(0);
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == submitButton) {
            //提交答案事件

            for (int i = 0; i < inputModuloArr.length; i++) {
                inputAnswerArr[i] = Integer.parseInt(inputAnswer[i].getText());
            }
            for (int i = 0; i < inputAnswerArr.length; i++) {
                String moduloTest = "";
                moduloTest = inputModulo[i].getText();
                if (moduloTest.isEmpty()) {
                    inputModuloArr[i] = 0;
                } else {
                    inputModuloArr[i] = Integer.parseInt(moduloTest);
                }
            }
            for (int i = 0; i < 10; i++) {
                boolean judge = inputAnswerArr[i] == ansArr[i];
                if (inputModuloArr[i] != ansModuloArr[i] && strArr[i] == '/') {
                    judge = false;
                }
                ansRight[i] = judge;
                if (judge) {
                    count += 10;
                }
            }
            for (int i = 0; i < 10; i++) {
                JLabel jLabel;
                if (strArr[i] == '/') {
                    jLabel = new JLabel("答案为： " + num1Arr[i] + " " + strArr[i] + " " + num2Arr[i] + " = " + ansArr[i] +
                            "余" + ansModuloArr[i], JLabel.LEFT);
                } else {
                    jLabel = new JLabel("答案为： " + num1Arr[i] + " " + strArr[i] + " " + num2Arr[i] + " = " + ansArr[i]
                            , JLabel.LEFT);
                }
                jLabel.setBounds(100 + (i / 5) * 500, 130 + (i % 5) * 70, 350, 18);
                jLabel.setFont(new Font("宋体", Font.ROMAN_BASELINE, 18));
                JLabel jLabel1;
                if (ansRight[i]) {
                    jLabel1 = new JLabel("回答正确");
                    jLabel1.setBounds(450 + (i / 5) * 500, 130 + (i % 5) * 70, 200, 18);
                    jLabel1.setFont(new Font("宋体", Font.ROMAN_BASELINE, 18));
                    jLabel.setForeground(Color.blue);
                    jLabel1.setForeground(Color.blue);
                } else {
                    jLabel1 = new JLabel("回答错误");
                    jLabel1.setBounds(450 + (i / 5) * 500, 130 + (i % 5) * 70, 200, 18);
                    jLabel1.setFont(new Font("宋体", Font.ROMAN_BASELINE, 18));
                    jLabel.setForeground(Color.red);
                    jLabel1.setForeground(Color.red);
                }
                jLabel1.setVisible(false);
                jLabel.setVisible(false);
                right[i] = jLabel1;
                answerShow[i] = jLabel;
                this.getContentPane().add(jLabel);
                this.getContentPane().add(jLabel1);
            }
            for (int i = 0; i < 10; i++) {
                JLabel jLabel = answerShow[i];
                jLabel.setVisible(true);
                right[i].setVisible(true);
            }

            //显示答题情况
            JLabel showAnswerCase = null;
            if (count >= 100) {
                showAnswerCase = new JLabel(countCase[0] + ",分数为：" + count + " 耗时：" + (120 - counter) + "秒");
            } else if (count < 100 && count >= 80) {
                showAnswerCase = new JLabel(countCase[1] + ",分数为：" + count + " 耗时：" + (120 - counter) + "秒");
            } else if (count < 80 && count >= 70) {
                showAnswerCase = new JLabel(countCase[2] + ",分数为：" + count + " 耗时：" + (120 - counter) + "秒");
            } else if (count < 70 && count >= 60) {
                showAnswerCase = new JLabel(countCase[3] + ",分数为：" + count + " 耗时：" + (120 - counter) + "秒");
            } else if (count < 60) {
                showAnswerCase = new JLabel(countCase[4] + ",分数为：" + count + " 耗时：" + (120 - counter) + "秒");
            }
            showAnswerCase.setBounds(100, 440, 500, 30);
            showAnswerCase.setFont(new Font("宋体", Font.BOLD, 20));
            showAnswerCase.setVisible(true);
            this.getContentPane().add(showAnswerCase);
            outputRecodeStatus();
            count = 0;
            //将提交按钮直接设置为不可操作
            submitButton.setEnabled(false);

            timer.stop();
            seconds = 120;
            repaint();
        } else if (source == chooseOperator) {
            Object selectedItem = chooseOperator.getSelectedItem();
            for (int i = 0; i < operatorArr.length; i++) {
                if (operatorArr[i].equals(selectedItem)) {
                    operator = i + 1;
                    break;
                }
            }
            this.getContentPane().removeAll();
            initData();
            initText();
            submitButton.setEnabled(true);
            count = 0;
            seconds = 120;
            repaint();
        } else if (source == chooseTonnage) {
            Object selectedItem = chooseTonnage.getSelectedItem();
            for (int i = 0; i < TonnageArr.length; i++) {
                if (TonnageArr[i].equals(selectedItem)) {
                    tonnage = i + 1;
                    break;
                }
            }
            this.getContentPane().removeAll();
            initData();
            initText();
            submitButton.setEnabled(true);
            count = 0;
            seconds = 120;
            repaint();
        } else if (source == timer) {
            counter--;
            countdownLabel.setText(String.valueOf(counter));

            if (counter == 0) {
                //提交答案事件
                for (int i = 0; i < inputModuloArr.length; i++) {
                    inputAnswerArr[i] = Integer.parseInt(inputAnswer[i].getText());
                }
                for (int i = 0; i < inputAnswerArr.length; i++) {
                    String moduloTest = "";
                    moduloTest = inputModulo[i].getText();
                    if (moduloTest.isEmpty()) {
                        inputModuloArr[i] = 0;
                    } else {
                        inputModuloArr[i] = Integer.parseInt(moduloTest);
                    }
                }
                for (int i = 0; i < 10; i++) {
                    boolean judge = inputAnswerArr[i] == ansArr[i];
                    if (inputModuloArr[i] != ansModuloArr[i] && strArr[i] == '/') {
                        judge = false;
                    }
                    ansRight[i] = judge;
                    if (judge) {
                        count += 10;
                    }
                }
                for (int i = 0; i < 10; i++) {
                    JLabel jLabel;
                    if (strArr[i] == '/') {
                        jLabel =
                                new JLabel("答案为： " + num1Arr[i] + " " + strArr[i] + " " + num2Arr[i] + " = " + ansArr[i] +
                                        "余" + ansModuloArr[i], JLabel.LEFT);
                    } else {
                        jLabel =
                                new JLabel("答案为： " + num1Arr[i] + " " + strArr[i] + " " + num2Arr[i] + " = " + ansArr[i]
                                        , JLabel.LEFT);
                    }
                    jLabel.setBounds(100 + (i / 5) * 500, 130 + (i % 5) * 70, 350, 18);
                    jLabel.setFont(new Font("宋体", Font.ROMAN_BASELINE, 18));
                    JLabel jLabel1;
                    if (ansRight[i]) {
                        jLabel1 = new JLabel("回答正确");
                        jLabel1.setBounds(450 + (i / 5) * 500, 130 + (i % 5) * 70, 200, 18);
                        jLabel1.setFont(new Font("宋体", Font.ROMAN_BASELINE, 18));
                        jLabel.setForeground(Color.blue);
                        jLabel1.setForeground(Color.blue);
                    } else {
                        jLabel1 = new JLabel("回答错误");
                        jLabel1.setBounds(450 + (i / 5) * 500, 130 + (i % 5) * 70, 200, 18);
                        jLabel1.setFont(new Font("宋体", Font.ROMAN_BASELINE, 18));
                        jLabel.setForeground(Color.red);
                        jLabel1.setForeground(Color.red);
                    }
                    jLabel1.setVisible(false);
                    jLabel.setVisible(false);
                    right[i] = jLabel1;
                    answerShow[i] = jLabel;
                    this.getContentPane().add(jLabel);
                    this.getContentPane().add(jLabel1);
                }
                for (int i = 0; i < 10; i++) {
                    JLabel jLabel = answerShow[i];
                    jLabel.setVisible(true);
                    right[i].setVisible(true);
                }

                //显示答题情况
                JLabel showAnswerCase = null;
                if (count >= 100) {
                    showAnswerCase = new JLabel(countCase[0] + ",分数为：" + count + " 耗时：" + (120 - counter) + "秒");
                } else if (count < 100 && count >= 80) {
                    showAnswerCase = new JLabel(countCase[1] + ",分数为：" + count + " 耗时：" + (120 - counter) + "秒");
                } else if (count < 80 && count >= 70) {
                    showAnswerCase = new JLabel(countCase[2] + ",分数为：" + count + " 耗时：" + (120 - counter) + "秒");
                } else if (count < 70 && count >= 60) {
                    showAnswerCase = new JLabel(countCase[3] + ",分数为：" + count + " 耗时：" + (120 - counter) + "秒");
                } else if (count < 60) {
                    showAnswerCase = new JLabel(countCase[4] + ",分数为：" + count + " 耗时：" + (120 - counter) + "秒");
                }
                showAnswerCase.setBounds(100, 440, 500, 30);
                showAnswerCase.setFont(new Font("宋体", Font.BOLD, 20));
                showAnswerCase.setVisible(true);
                this.getContentPane().add(showAnswerCase);
                count = 0;
                //将提交按钮直接设置为不可操作
                submitButton.setEnabled(false);
                timer.stop();
                seconds = 120;
            }
        }
    }

    private void outputRecodeStatus() {
        try {
            FileWriter fileWriter = new FileWriter(road, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < num1Arr.length; i++) {
                bufferedWriter.write("题目" + (i + 1) + ":");
                bufferedWriter.newLine();
                bufferedWriter.write(num1Arr[i] + " " + strArr[i] + " " + num2Arr[i]);
                bufferedWriter.newLine();
                bufferedWriter.write("您的答案为：" + inputAnswerArr[i] + "余" + inputModuloArr[i]);
                bufferedWriter.newLine();
                bufferedWriter.write("正确答案为：" + ansArr[i] + "余" + ansModuloArr[i]);
                bufferedWriter.newLine();
                if (ansRight[i]) {
                    bufferedWriter.write("您的回答是正确的");
                    bufferedWriter.newLine();
                } else {
                    bufferedWriter.write("您的回答是错误的");
                    bufferedWriter.newLine();
                }
            }
            if (count >= 100) {
                bufferedWriter.write(countCase[0] + ",分数为：" + count + " 耗时：" + (120 - counter) + "秒");
            } else if (count < 100 && count >= 80) {
                bufferedWriter.write(countCase[1] + ",分数为：" + count + " 耗时：" + (120 - counter) + "秒");
            } else if (count < 80 && count >= 70) {
                bufferedWriter.write(countCase[2] + ",分数为：" + count + " 耗时：" + (120 - counter) + "秒");
            } else if (count < 70 && count >= 60) {
                bufferedWriter.write(countCase[3] + ",分数为：" + count + " 耗时：" + (120 - counter) + "秒");
            } else if (count < 60) {
                bufferedWriter.write(countCase[4] + ",分数为：" + count + " 耗时：" + (120 - counter) + "秒");
            }
            bufferedWriter.newLine();
            bufferedWriter.flush();
            fileWriter.flush();
        } catch (IOException e) {
            System.err.println("文件写入失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showRecodeStatus() {
        try {
            ProcessBuilder pb = new ProcessBuilder("notepad.exe", road);
            pb.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void CountDownDisplay() {
        JLabel showTestOfCountDown = new JLabel("倒计时：");
        showTestOfCountDown.setBounds(30, 0, 200, 50);
        showTestOfCountDown.setFont(new Font("微软雅黑", Font.BOLD, 30));
        showTestOfCountDown.setVisible(true);
        this.getContentPane().add(showTestOfCountDown);

        counter = seconds;
        countdownLabel = new JLabel(String.valueOf(counter));
        countdownLabel.setBounds(30, 0, 200, 130);
        countdownLabel.setFont(new Font("Arial", Font.BOLD, 30));
        countdownLabel.setVisible(true);
        this.getContentPane().add(countdownLabel);

        timer = new Timer(1000, this);
        timer.start();
        this.getContentPane().setVisible(true);
    }

}