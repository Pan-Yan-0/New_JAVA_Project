package FILE_Study;


import java.util.Random;

public class Test5 {
    static int tonnage  = 2;
    public static void main(String[] args) {
        Random r = new Random();
        int num1, num2;

        num1 = r.nextInt((int)Math.pow(10,tonnage));
        num2 = r.nextInt((int)Math.pow(10,tonnage));
        System.out.println(num1);
        System.out.println(num2);
    }
}
