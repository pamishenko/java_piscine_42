package ex00;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        boolean isPrime;
        long    step;
        long    number;
        Scanner scanner = new Scanner(System.in);

            step = 1;
            isPrime =  true;
            number = scanner.nextInt();
            if (number <= 1) {
                System.err.println("IllegalArgument");
                scanner.close();
                System.exit(-1);
            }
            for (int i = 2; i <= mySqrt(number); i++) {
                if (number % i == 0) {
                    isPrime = false;
                    break;
                }
                step++;
            }
            System.out.println(isPrime + " " + (step));

        scanner.close();
    }

    static double myAbs(double number) {
        return number > 0 ? number : -number;
    }


    static long mySqrt(long value) {
        double left = 1;
        double right = value;
        double middle;
        double  approx;

        do {
            middle = left + (right - left) / 2;
            approx = middle * middle;
            if (approx > value)
                right = middle;
            else
                left = middle;
        }
        while (myAbs(approx - value) > 0.001);
        return (long)(middle + 0.001);
    }
}
