package ex02;

import java.util.Arrays;
import java.util.Scanner;

public class Program {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";

    static int arrayCapacity;
    static int   threadsCount;

    public static void main(String[] args) throws InterruptedException {
        parseArgs(args);
        if ((arrayCapacity > 2000000 || threadsCount > 2000000) || threadsCount > arrayCapacity)
            System.exit(-1);
        Short[]     arr = new Short[arrayCapacity];
        int         numThread = 0;
        int         countElement = arrayCapacity / threadsCount;
        Integer[]   arraySum = new Integer[threadsCount];
        MyThread[]  threads = new MyThread[threadsCount];

        initArr(arr, arraySum);

        printOneThread(arr);

        int from = 0;
        int to = -1;
        for (int i = 0; i < threadsCount; i++) {
                from = ++to;
                to = i * countElement + (i == 0 ? countElement : countElement + 1);
            if (arrayCapacity <= threadsCount)
            {
                from = i;
                to = i;
            }
            if (numThread < threadsCount - 1) {
               threads[i] = new MyThread(arr,
                       arraySum,
                       numThread,
                       from,
                       to
               );
            }
            else {
               threads[i] = new MyThread(arr,
                        arraySum,
                        numThread,
                        from,
                        arrayCapacity - 1);
            }
            numThread++;
        }
        for (MyThread thread : threads) {
            thread.start();
            thread.join();
        }
        printResult(arraySum);

    }

    static void initArr(Short[] arr, Integer[] arraySum) {
        for (int i = 0; i < arr.length; i++) {
            int min = -1000;
            int max = 1000;
            max -= min;
            arr[i] = (short) ((Math.random() * ++max) + min);
        }
        Arrays.fill(arraySum, 0);
    }

    static void parseArgs(String[] args) {
        arrayCapacity = parseIntToArg(args, "arraySize", 0);
        threadsCount = parseIntToArg(args, "threadsCount", 1);

    }
    static void printResult(Integer[]  arraySum) {
        int result = 0;
        for (int i : arraySum) {
            result += i;
        }
        System.out.println(ANSI_GREEN + "Sum by threads: " + result);
    }

    static void printOneThread(Short[] array) {
        int result = 0;
        for (int i : array)
            result += i;
        System.out.println(ANSI_YELLOW + "Sum: " + result + ANSI_CYAN);
    }

    static int  parseIntToArg(String[] args, String name, int i) {
        int result = 3;
        try {
            Scanner scanner = new Scanner(args[i]).useDelimiter("=");
            if (scanner.next().equals("--" + name)) {
                if (scanner.hasNextInt()) {
                    result = scanner.nextInt();
                } else {
                    System.out.println(ANSI_CYAN + "Set default "+ name +" (3)" + ANSI_RESET);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println(ANSI_CYAN + "Set default  "+ name +" (3)" + ANSI_RESET);
        }
        return result;
    }
}
