import java.util.Scanner;

public class DigitSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int num = sc.nextInt();

        System.out.print("Enter option (1-Even digits, 2-Odd digits): ");
        int opt = sc.nextInt();

        int sum = 0;

        while (num > 0) {
            int digit = num % 10;

            if (opt == 1 && digit % 2 == 0) {
                sum += digit;
            } else if (opt == 2 && digit % 2 != 0) {
                sum += digit;
            }

            num = num / 10;
        }

        if (opt == 1)
            System.out.println("Sum of even digits = " + sum);
        else if (opt == 2)
            System.out.println("Sum of odd digits = " + sum);
        else
            System.out.println("Invalid option!");

        sc.close();
    }
}