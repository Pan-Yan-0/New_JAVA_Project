package FILE_Study;

import java.io.File;

public class Test3 {
    public static void main(String[] args) {
        File f1 = new File("D:\\JavaProject\\Little_Student_Math\\src\\FILE_Study\\a.txt");
        System.out.println(f1.length());
        File f2 = new File("D:\\JavaProject\\Little_Student_Math\\src\\FILE_Study");
        String path1 = f2.getAbsolutePath();
        System.out.println(path1);
        File f3 = new File("src\\FILE_Study\\a.txt");
        String path2 = f3.getAbsolutePath();
        System.out.println(f3.length());
        System.out.println(path2);
    }
}
