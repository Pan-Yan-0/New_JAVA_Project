package FILE_Study;

import java.io.File;

public class Test2 {
    public static void main(String[] args) {
        File f1 = new File("D:\\JavaProject\\Little_Student_Math\\src\\FILE_Stud");
        if (f1.isDirectory()) {
            System.out.println("是文件夹");
        } else {
            System.out.println("不是文件夹");
        }
        if (f1.isFile()){
            System.out.println("是文件");
        } else{
            System.out.println("不是文件");
        }
        if (f1.exists()){
            System.out.println("存在");
        }else {
            System.out.println("不存在");
        }
    }
}
