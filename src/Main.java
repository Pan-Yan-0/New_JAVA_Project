import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //选择登录还是注册



        char[] str = {'+', '-', '*', '/'};
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
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
            if (top <= 9 && top >= 0 && top >= bottom) {
                break;
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
    }

    public static boolean login() {
        //开始读取文件数据，创建Student类数组

        //用户输入中
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入您的用户名");
            String userName = sc.next();
            String password;
            System.out.println("请输入您的密码");
            password = sc.next();
            //进行判断用户密码是否正确
            boolean judge = false;

            //如果没有，让用户选择忘记密码或者重新来过
            if (judge) {
                break;
            } else {
                System.out.println("忘记密码，请输入 1 ");
                System.out.println("重新输入，请输入 0 ");

                int choose = sc.nextInt();
                if (choose == 0) {
                    continue;
                } else {
                    //开始忘记密码函数
//                    forgetPassword(userName,);
                }

            }
        }
        return true;

    }

    public static void forgetPassword(String userName, Student[] students) {
        int userPhone = 0;
        for (int i = 0; i < students.length; i++) {
            if (userName.equals(students[i].getUserName())) {
                userPhone = students[i].getPhone();
            }
        }
        Scanner sc = new Scanner(System.in);
        int phone = sc.nextInt();
        if (phone == userPhone) {
            //开始修改密码
            String password;
            while (true) {
                System.out.println("请输入您的密码");
                password = sc.next();
                System.out.println("请再次输入您的密码");
                String repeatmPassword = sc.next();
                if (password.equals(repeatmPassword)) {
                    System.out.println("修改密码成功，请牢记您的密码哦");
                    break;
                } else {
                    System.out.println("第一次输入的密码和第二次输入的密码不一致，请重新再次输入");
                }
            }
            //开始修改数组数据，重新开始上传

        }
    }

    public static void Register() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您的用户名");
        String userName = sc.next();
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
        int phone;
        while (true) {
            System.out.println("请输入您的手机号码");
            phone = sc.nextInt();
            if ((int) (Math.log10(phone)) != 10) {
                System.out.println("手机号码应为11位，请您重新输入一遍");
            } else {
                break;
            }
        }
        Student student = new Student(userName, password, phone);
        //进行传进文件之中

        //创建成功
        System.out.println("创建用户成功！！！");
        System.out.println("正在返回登录界面");

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
