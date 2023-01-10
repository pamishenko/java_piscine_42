package edu.school21.numbers;

public class NumberWorker
{
    public static void main( String[] args )
    {
        NumberWorker nw = new NumberWorker();
        System.out.println(nw.digitSum(111));
        System.out.println(nw.isPrime(223));
    }

    public int digitSum(int number) {
        int result = 0;
        while (number != 0) {
            result += number % 10;
            number /= 10;
        }
        return result;
    }

    public  boolean isPrime(int number) {
        boolean isPrime;

        isPrime =  true;
        for (int i = 2; i <= mySqrt(number); i++) {
            if (number % i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    static double myAbs(double number) {
        return number > 0 ? number : -number;
    }

    public static int mySqrt(int input) {
        long start = 1;
        long end = input;
        long ret = 0;
        long mid;

        while (start <= end) {
            mid = (start + end) / 2;
            if (mid * mid == input) {
                return ((int) mid);
            } else if (mid * mid < input) {
                start = mid + 1;
                ret = mid;
            } else {
                end = mid - 1;
            }
        }
        return (int) ret;
    }

}
