package FILE_Study;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        String str = "D:\\JavaProject\\Little_Student_Math\\src\\FILE_Study\\a.txt";
        File f1 = new File(str);
        System.out.println(f1);

        String parent = "D:\\JavaProject\\Little_Student_Math\\src\\FILE_Study";
        String child = "a.txt";
        File f2 = new File(parent,child);
        System.out.println(f2);
        System.out.println(parent+"\\"+child);
    }

}
