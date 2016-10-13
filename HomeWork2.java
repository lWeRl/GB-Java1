import java.util.Random;
import java.util.Scanner;

/**
 * Created by lWeRl on 08.10.2016.
 * Java 1 HomeWork 2 Nickolay Bobrov
 */
class HomeWork2 {
    public static void main(String[] args) {
        Random random = new Random();
// Problem 1
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(2);
            System.out.print(array[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) array[i] = 1;
            else array[i] = 0;
            System.out.print(array[i] + " ");
        }
        System.out.println("\n");
// Problem 2
        int[] arr = new int[8];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * 3 + 1;
            System.out.print(arr[i] + " ");
        }
        System.out.println("\n");
// Problem 3
        int[] mas = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < mas.length; i++) {
            if (mas[i] < 6) mas[i] *= 2;
            System.out.print(mas[i] + " ");
        }
        System.out.println("\n");
// Problem 4
        array = new int[20];
        array[0] = random.nextInt(101);
        int min = array[0];
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            array[i] = random.nextInt(101);
            if (array[i] < min) min = array[i];
            if (array[i] > max) max = array[i];
        }
        for (int i : array) {1
            System.out.print(i + " ");
        }
        System.out.println("\nMin: " + min + " Max: " + max + "\n");

        System.out.println(calculator());
    }

// Problem 5
    static double calculator() {
        Scanner scanner = new Scanner(System.in);
        double result = Double.parseDouble(scanner.next()); // как ни странно при scanner.nextDouble() вылезают ошибки обработки, при том только для чисел с точкой, при вводе целого все работает
        String operation = scanner.next();
        double value = Double.parseDouble(scanner.next());
        switch (operation) {
            case ("*"):
                result *= value;
                break;
            case ("/"):
                result /= value;
                break;
            case ("+"):
                result += value;
                break;
            case ("-"):
                result -= value;
                break;
            case ("^"):
                result = Math.pow(result, value);
                break;
            // функционал легко расширяется при помощи case
        }
        scanner.close();
        return result;
    }
}