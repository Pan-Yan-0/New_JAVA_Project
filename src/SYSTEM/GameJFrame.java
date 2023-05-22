package SYSTEM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class GameJFrame extends JFrame implements MouseListener, KeyListener, ActionListener {
    //总分
    int count = 0;

    String[] countCase = {"满分","优秀","良好","及格","不及格"};
    //答题情况
    boolean[] ansRight = new boolean[10];
    //运算符的选择
    String[] operatorArr = {"加法", "减法", "乘法", "除法", "混合运算"};
    JComboBox chooseOperator = new JComboBox(operatorArr);
    int operator = 5;
    //位数的选择
    String[] TonnageArr = {"一位", "两位", "三位", "四位", "五位"};
    JComboBox chooseTonnage = new JComboBox(TonnageArr);
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


    //运算位数
    /*
     * 注意 1 是一位数的意思
     * 2 才是两位数的意思
     * */
    int tonnage = 5;

    /*
    * 题目的答案输入框*/
    JTextField inputAnswer1 = new JTextField();
    JTextField inputModulo1 = new JTextField();
    JTextField inputAnswer2 = new JTextField();
    JTextField inputModulo2 = new JTextField();
    JTextField inputAnswer3 = new JTextField();
    JTextField inputModulo3 = new JTextField();
    JTextField inputAnswer4 = new JTextField();
    JTextField inputModulo4 = new JTextField();
    JTextField inputAnswer5 = new JTextField();
    JTextField inputModulo5 = new JTextField();
    JTextField inputAnswer6 = new JTextField();
    JTextField inputModulo6 = new JTextField();
    JTextField inputAnswer7 = new JTextField();

    JTextField inputModulo7 = new JTextField();
    JTextField inputAnswer8 = new JTextField();
    JTextField inputModulo8 = new JTextField();
    JTextField inputAnswer9 = new JTextField();
    JTextField inputModulo9 = new JTextField();
    JTextField inputAnswer10 = new JTextField();
    JTextField inputModulo10 = new JTextField();

    //用户的姓名
    String username = "张三";
    //创建功能的项目栏
    JMenuItem restart = new JMenuItem("重做");

    JMenuItem Recode = new JMenuItem("查看历史记录");

    JMenuItem exit = new JMenuItem("退出系统");

    JMenu otherExam = new JMenu("另做一套");

    JMenu contactOur = new JMenu("关于我们");
    JButton submitButton = new JButton("提交答案");
    public GameJFrame() {
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

    private void initText() {
        //显示答案

        //提交按钮
        submitButton.setBounds(1000,10,150,34);
        submitButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
        submitButton.addActionListener(this);
        this.getContentPane().add(submitButton);
        //用户姓名显示器  运算位数
        JLabel userName = new JLabel("考生姓名：" + username);
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
        this.getContentPane().add(chooseOperator);
        //运算位数
        JLabel Tonnage = new JLabel("请选择运算位数：");
        Tonnage.setBounds(700, 10, 200, 30);
        Tonnage.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(Tonnage);
        //运算位数选择
        chooseTonnage.setBounds(860, 10, 100, 30);
        chooseTonnage.setFont(new Font("微软雅黑", Font.BOLD, 16));
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
        //余数 1
        JLabel ModuloJLabel2 = new JLabel("余数");
        ModuloJLabel2.setBounds(930, 70, 100, 30);
        ModuloJLabel2.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(ModuloJLabel2);
        //显示题目
        //题目一
        JLabel problemJLabel1 = new JLabel(num1Arr[0] + " " + strArr[0] + " " + num2Arr[0] + " = ", JLabel.RIGHT);
        problemJLabel1.setBounds(20, 100, 230, 30);
        problemJLabel1.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(problemJLabel1);

        inputAnswer1.setBounds(250, 100, 100, 30);
        inputAnswer1.setFont(new Font("微软雅黑", Font.BOLD, 20));
        inputAnswer1.addKeyListener(this);
        this.getContentPane().add(inputAnswer1);

        inputModulo1.setBounds(400, 100, 100, 30);
        inputModulo1.setFont(new Font("微软雅黑", Font.BOLD, 20));
        inputModulo1.addKeyListener(this);
        this.getContentPane().add(inputModulo1);

        //题目二
        JLabel problemJLabel2 = new JLabel(num1Arr[1] + " " + strArr[1] + " " + num2Arr[1] + " = ", JLabel.RIGHT);
        problemJLabel2.setBounds(20, 170, 230, 30);
        problemJLabel2.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(problemJLabel2);

        inputAnswer2.setBounds(250, 170, 100, 30);
        inputAnswer2.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(inputAnswer2);

        inputModulo2.setBounds(400, 170, 100, 30);
        inputModulo2.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(inputModulo2);
        //题目三
        JLabel problemJLabel3 = new JLabel(num1Arr[2] + " " + strArr[2] + " " + num2Arr[2] + " = ", JLabel.RIGHT);
        problemJLabel3.setBounds(20, 240, 230, 30);
        problemJLabel3.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(problemJLabel3);

        inputAnswer3.setBounds(250, 240, 100, 30);
        inputAnswer3.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(inputAnswer3);

        inputModulo3.setBounds(400, 240, 100, 30);
        inputModulo3.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(inputModulo3);

        //题目四
        JLabel problemJLabel4 = new JLabel(num1Arr[3] + " " + strArr[3] + " " + num2Arr[3] + " = ", JLabel.RIGHT);
        problemJLabel4.setBounds(20, 310, 230, 30);
        problemJLabel4.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(problemJLabel4);

        inputAnswer4.setBounds(250, 310, 100, 30);
        inputAnswer4.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(inputAnswer4);

        inputModulo4.setBounds(400, 310, 100, 30);
        inputModulo4.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(inputModulo4);

        //题目五
        JLabel problemJLabel5 = new JLabel(num1Arr[4] + " " + strArr[4] + " " + num2Arr[4] + " = ", JLabel.RIGHT);
        problemJLabel5.setBounds(20, 380, 230, 30);
        problemJLabel5.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(problemJLabel5);

        inputAnswer5.setBounds(250, 380, 100, 30);
        inputAnswer5.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(inputAnswer5);

        inputModulo5.setBounds(400, 380, 100, 30);
        inputModulo5.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(inputModulo5);

        //题目六
        JLabel problemJLabel6 = new JLabel(num1Arr[5] + " " + strArr[5] + " " + num2Arr[5] + " = ", JLabel.RIGHT);
        problemJLabel6.setBounds(520, 100, 230, 30);
        problemJLabel6.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(problemJLabel6);

        inputAnswer6.setBounds(760, 100, 100, 30);
        inputAnswer6.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(inputAnswer6);

        inputModulo6.setBounds(900, 100, 100, 30);
        inputModulo6.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(inputModulo6);

        //题目七
        JLabel problemJLabel7 = new JLabel(num1Arr[6] + " " + strArr[6] + " " + num2Arr[6] + " = ", JLabel.RIGHT);
        problemJLabel7.setBounds(520, 170, 230, 30);
        problemJLabel7.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(problemJLabel7);

        inputAnswer7.setBounds(760, 170, 100, 30);
        inputAnswer7.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(inputAnswer7);

        inputModulo7.setBounds(900, 170, 100, 30);
        inputModulo7.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(inputModulo7);
        //题目八
        JLabel problemJLabel8 = new JLabel(num1Arr[7] + " " + strArr[7] + " " + num2Arr[7] + " = ", JLabel.RIGHT);
        problemJLabel8.setBounds(520, 240, 230, 30);
        problemJLabel8.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(problemJLabel8);

        inputAnswer8.setBounds(760, 240, 100, 30);
        inputAnswer8.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(inputAnswer8);

        inputModulo8.setBounds(900, 240, 100, 30);
        inputModulo8.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(inputModulo8);

        //题目九
        JLabel problemJLabel9 = new JLabel(num1Arr[8] + " " + strArr[8] + " " + num2Arr[8] + " = ", JLabel.RIGHT);
        problemJLabel9.setBounds(520, 310, 230, 30);
        problemJLabel9.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(problemJLabel9);

        inputAnswer9.setBounds(760, 310, 100, 30);
        inputAnswer9.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(inputAnswer9);

        inputModulo9.setBounds(900, 310, 100, 30);
        inputModulo9.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(inputModulo9);

        //题目十
        JLabel problemJLabel10 = new JLabel(num1Arr[9] + " " + strArr[9] + " " + num2Arr[9] + " = ", JLabel.RIGHT);
        problemJLabel10.setBounds(520, 380, 230, 30);
        problemJLabel10.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(problemJLabel10);

        inputAnswer10.setBounds(760, 380, 100, 30);
        inputAnswer10.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(inputAnswer10);

        inputModulo10.setBounds(900, 380, 100, 30);
        inputModulo10.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.getContentPane().add(inputModulo10);
    }

    private void initData() {
        Random r = new Random();
        int count = 0;//记录总分
        char[] str = {'+', '-', '*', '/'};
        for (int i = 0; i < 10; i++) {
            if (operator == 5) {
                //生成运算符号
                int choose = r.nextInt(4);
                char ch = str[choose];
                strArr[i] = ch;
            } else {
                strArr[i] = str[i];
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
            while (strArr[i] == '/' && num2 == 0) {
                num2 = r.nextInt(9) + 1;
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
        //设置界面


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
            //执行重开代码

        } else if (source==Recode) {

        } else if (source==exit) {
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
        if (source==submitButton){
            //提交答案事件
            int[] inputAnswer = new int[10];
            int[] inputModulo = new int[10];
            inputAnswer[0] = Integer.parseInt(inputAnswer1.getText());
            inputAnswer[1] = Integer.parseInt(inputAnswer2.getText());
            inputAnswer[2] = Integer.parseInt(inputAnswer3.getText());
            inputAnswer[3] = Integer.parseInt(inputAnswer4.getText());
            inputAnswer[4] = Integer.parseInt(inputAnswer5.getText());
            inputAnswer[5] = Integer.parseInt(inputAnswer6.getText());
            inputAnswer[6] = Integer.parseInt(inputAnswer7.getText());
            inputAnswer[7] = Integer.parseInt(inputAnswer8.getText());
            inputAnswer[8] = Integer.parseInt(inputAnswer9.getText());
            inputAnswer[9] = Integer.parseInt(inputAnswer10.getText());

            inputModulo[0] = Integer.parseInt(inputModulo1.getText());

            inputModulo[1] = Integer.parseInt(inputModulo2.getText());

            inputModulo[2] = Integer.parseInt(inputModulo3.getText());

            inputModulo[3] = Integer.parseInt(inputModulo4.getText());

            inputModulo[4] = Integer.parseInt(inputModulo5.getText());

            inputModulo[5] = Integer.parseInt(inputModulo6.getText());

            inputModulo[6] = Integer.parseInt(inputModulo7.getText());

            inputModulo[7] = Integer.parseInt(inputModulo8.getText());

            inputModulo[8] = Integer.parseInt(inputModulo9.getText());

            inputModulo[9] = Integer.parseInt(inputModulo10.getText());

            for (int i = 0; i < 10; i++) {
                boolean judge = true;
                if (inputAnswer[i]!=ansArr[i]){
                    judge = false;
                }
                if (inputModulo[i]!=ansModuloArr[i]&&strArr[i]=='/'){
                    judge = false;
                }
                ansRight[i] = judge;
                if (judge){
                    count+=10;
                }
            }
        }
    }
}
