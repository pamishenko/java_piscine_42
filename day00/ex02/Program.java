package ex02;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        boolean isPrime;
        long    number;
        long    count = 0;
        Scanner scanner = new Scanner(System.in);

        while (true){
            isPrime =  true;
            number = scanner.nextInt();
            if (number == 42) {
                break;
            }
            number = sumDigit(number);
            for (int i = 2; i <= mySqrt(number); i++) {
                if (number % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime)
                count++;
        }
        System.out.println("Count of coffee-request - " + count);
    }

    static double myAbs(double number) {
        return number > 0 ? number : -number;
    }


    static long mySqrt(long value) {
        double  left = 1;
        double  right = value;
        double  middle;
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
    static long sumDigit(long number) {
        int result = 0;
        while (number > 0) {
            result += number % 10;
            number /= 10;
        }
        return result;
    }
}
