import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Random r = new Random();
        System.out.println(r.nextInt(4)+1);
        int top = 9,bottom = 0;
        int num1 = r.nextInt(top) + bottom + 1, num2 = r.nextInt(top) + bottom + 1;
        System.out.println(num1+"------"+num2);
    }
}
