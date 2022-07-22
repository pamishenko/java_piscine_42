package ex03;

import java.util.Scanner;

public class Program {
    private static final int MAX_SIZE = 18;
    static long    allMin = 0;
    static int     countWeek = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String  inputWeek;

        do {
            inputWeek = scanner.nextLine();
            if (inputWeek.equals("42")) {
                break;
            }
            countWeek++;
            if (!inputWeek.equals("Week " + countWeek)) {
                System.err.println("IllegalArgument");
                scanner.close();
                System.exit(-1);
            }
            setMinResult();
        }
        while (countWeek <= MAX_SIZE);
        printDiagramWeek();
        scanner.close();
    }

    static void setMinResult() {
        long tmp;
        long min = 9;

        for (int i = 0; i < 5; i++) {
            tmp = scanner.nextLong();
            if (tmp < min)
                min = tmp;
        }
        for (int i = 1; i < countWeek; i++)
            min *= 10;
        allMin += min;
        scanner.nextLine();
    }

    static void printDiagramWeek() {
        for (int i = 1; i <= countWeek; i++) {
            System.out.print("Week " + i + " ");
            for (int j = 0; j < allMin % 10; j++) {
                System.out.print("=");
            }
            allMin /= 10;
            System.out.println(">");
        }
    }
}
