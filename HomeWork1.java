import java.io.*;
import java.util.*;

/**
 * Created by lWeRl on 05.10.2016.
 */
public class HomeWork1 {
    public static void main(String[] args) throws Exception {
        String s1 = "It was so simple print only \"psvm\"! ", s2 = "Because I add this string =)";
        System.out.println(s1 + s2);
        System.out.print("\n");
        simpleVariable();
        System.out.println();
        System.out.println("Method for equation:");
        System.out.printf("2*(3+4/5)=%.1f\n\n", equation(2, 3, 4, 5));
        System.out.println(fromTenToTwenty());
        System.out.print(String.format("\n"));
        checkYear();
    }

    public static void simpleVariable() {
        byte b = Byte.MIN_VALUE + 100;
        short sh = Short.MAX_VALUE;
        int i = Integer.MIN_VALUE + sh;
        long l = Long.MAX_VALUE + i;
        float f = Float.MAX_VALUE;
        double d = Double.MIN_VALUE;
        char ch1 = 'H', ch2 = 'e', ch3 = 'l', ch4 = 'l', ch5 = 'o';
        boolean boo = true;
        System.out.println("Simple variable");
        System.out.println("Some from byte: " + b);
        System.out.println("Some from short: " + sh);
        System.out.println("Some from int: " + i);
        System.out.println("Some from long: " + l);
        System.out.println("Some from float :" + f);
        System.out.println("Some from double :" + d);
        System.out.println(ch1 + ch2 + ch3 + ch4 + ch5 + " is sum of chars value =) But if string was started before it sum is string: " + ch1 + ch2 + ch3 + ch4 + ch5);
        System.out.println("If boo=" + boo + " then !boo=" + !boo);

    }

    public static float equation(int a, int b, int c, int d) {
        return a * (b + (float) c / d);
    }

    public static boolean fromTenToTwenty() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter two number");
        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());
        if ((a + b) > 10 && ((a + b) < 20)) return true;
        else return false;
    }

    public static void checkYear() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter year: ");
        int y=0;
        if (scanner.hasNextInt()) y = scanner.nextInt();
        if ((y%4==0)&&((y%100!=0)||(y%400==0))) System.out.println("Year is leap.");
        else System.out.println("Year is not leap.");
    }
}