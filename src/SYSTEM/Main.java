package SYSTEM;

import java.io.*;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //选择登录还是注册
        while (true){
            System.out.println("请输入 0. 登录     1. 注册    ");
            int howStart = sc.nextInt();
            if (howStart==0){
                if (!login()){
                    continue;
                }
                break;
            }else if (howStart==1){
                Register();
            }else {
                System.out.println("您的选择有错误，请重新选择");
            }
        }


        char[] str = {'+', '-', '*', '/'};
        Random r = new Random();
        while(true){
            System.out.println("欢迎来到小学生四则运算测试系统");
            int bottom = 0, top = 0;
            while (true) {
                System.out.println("请输入两个整数的之间的最小值,请输入0~9的数字");
                bottom = sc.nextInt();
                if (bottom <= 9 && bottom >= 0) {
                    break;
                } else {
                    System.out.println("输入错误，请重新输入");
                }
            }
            while (true) {
                System.out.println("请输入两个整数的之间的最大值，请输入0~9的数字");
                top = sc.nextInt();
                if (top <= 9 && top >= 0 && top > bottom) {
                    break;
                } else if (top == bottom) {
                    System.out.println("最大值和最小值最好不要相等");
                } else {
                    System.out.println("输入错误，请重新输入");
                }
            }
            int count = 0;
            for (int i = 0; i < 10; i++) {
                int num1, num2;
                while (true) {
                    num1 = r.nextInt(top) + 1;
                    num2 = r.nextInt(top) + 1;
                    if (num1 >= bottom && num2 >= bottom) {
                        break;
                    }
                }
                if (num1 < num2) {
                    int temp = num1;
                    num1 = num2;
                    num2 = temp;
                }
                int choose = r.nextInt(4);
                char ch = str[choose];
                System.out.println("第" + (i + 1) + "题: " + num1 + ch + num2);
                System.out.println("请输入你的答案");
                int num = sc.nextInt();
                if (judge(num1, num2, choose, num)) {
                    count += 10;
                    System.out.println("回答正确！！恭喜你获得10分");
                } else {
                    System.out.println("很遗憾，回答错误");
                }
            }
            System.out.println("你的总分是：" + count);
            if (count == 100) {
                System.out.println("满分，非常的好");
            } else if (count <= 100 && count >= 80) {
                System.out.println("优秀，已经很棒棒了");
            } else if (count < 80 && count >= 70) {
                System.out.println("良好，继续往更高分进发");
            } else if (count < 70 && count >= 60) {
                System.out.println("及格，请继续加油");
            } else {
                System.out.println("还请多加努力");
            }
            System.out.println("如果想继续考试请输入 1");
            System.out.println("退出请输入其他");
            if(sc.nextInt()!=1){
                break;
            }
        }

    }

    public static boolean login() {
        //用户输入中
        while (true) {
            //开始读取文件数据，创建Student类数组
            ArrayList<Student> students = new ArrayList<>();
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
            Scanner sc = new Scanner(System.in);
            boolean judge=true;
            String userName;
            String password;
            String userPassword="";
            while (true){
                System.out.println("请输入您的用户名");
                userName = sc.next();
                System.out.println("请输入您的密码");
                password = sc.next();
                //进行判断用户密码是否正确

                for (int i = 0; i < students.size(); i++) {
                    if (userName.equals(students.get(i).getUserName())){
                        userPassword=students.get(i).getUserPassword();
                    }
                }
                if (userPassword.equals("")){
                    System.out.println("没有该用户，请重新输入用户名");
                }else {
                    break;
                }
            }
            //如果没有，让用户选择忘记密码或者重新来过
            if (userPassword.equals(password)) {
                break;
            } else {
                System.out.println("忘记密码，请输入 1 ");
                System.out.println("重新输入，请输入 0 ");

                int choose = sc.nextInt();
                if (choose == 0) {
                    continue;
                } else {
                    //开始忘记密码函数
                    forgetPassword(userName, students);
                }

            }
        }
        return true;

    }

    public static void forgetPassword(String userName, ArrayList<Student> students) {
        long userPhone = 0;
        int index = 0;
        for (int i = 0; i < students.size(); i++) {
            if (userName.equals(students.get(i).getUserName())) {
                userPhone = students.get(i).getPhone();
                index=i;
            }
        }
        Scanner sc = new Scanner(System.in);
        long phone;
        while (true){
            System.out.println("请输入您注册时的手机号码");
            phone= sc.nextLong();
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
                //开始修改数组数据，重新开始上传
                try {
                    FileWriter fileWriter = new FileWriter("src/SYSTEM/Status");
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    int length = students.size();
                    for (int i = 0; i < length - 1; i++) {
                        bufferedWriter.write(students.get(i).getUserName() + " " + students.get(i).getUserPassword() + " "
                                + students.get(i).getPhone());
                        bufferedWriter.newLine();
                    }
                    bufferedWriter.write(students.get(length-1).getUserName() + " " + students.get(length-1).getUserPassword() + " "
                            + students.get(length-1).getPhone());
                    bufferedWriter.close();

                    System.out.println("文件已写入成功！");
                } catch (IOException e) {
                    System.err.println("文件写入失败：" + e.getMessage());
                }
                break;
            }else{
                System.out.println("电话号码不匹配");
            }
        }

    }

    public static void Register() {
        //开始读取文件数据，创建Student类数组
        ArrayList<Student> students = new ArrayList<Student>();
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

        Scanner sc = new Scanner(System.in);
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
        //进行传进文件之中
        try {
            FileWriter fileWriter = new FileWriter("src/SYSTEM/Status",true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            if (!students.isEmpty()){
                bufferedWriter.newLine();
            }
            bufferedWriter.write(userName+" "+password+" "+Long.toString(phone));
            bufferedWriter.close();
            //创建成功
            System.out.println("创建用户成功！！！");
            System.out.println("正在返回登录界面");
        } catch (IOException e) {
            System.err.println("文件写入失败：" + e.getMessage());
        }
    }



    public static boolean judge(int num1, int num2, int choose, int ans) {
        switch (choose) {
            case 0 -> {
                if (num1 + num2 == ans) {
                    return true;
                } else {
                    return false;
                }
            }
            case 1 -> {
                if (num1 - num2 == ans) {
                    return true;
                } else {
                    return false;
                }
            }
            case 2 -> {
                if (num1 * num2 == ans) {
                    return true;
                } else {
                    return false;
                }
            }
            case 3 -> {
                if (num1 / num2 == ans) {
                    return true;
                } else {
                    return false;
                }
            }
            default -> {
                break;
            }
        }
        return true;
    }
}
