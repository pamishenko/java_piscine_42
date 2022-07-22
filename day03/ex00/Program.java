package ex00;

import java.util.Scanner;

public class Program {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";


    public static void main(String[] args) throws InterruptedException {
        int         count = parseArgs(args);
        MyThread    threadEgg = new MyThread("Egg", count);
        MyThread    threadHen = new MyThread("Hen", count);

        threadEgg.start();
        threadHen.start();

        threadEgg.join();
        threadHen.join();
        for (int i = 0; i < count; i++) {
            System.out.println("Human");
        }
    }

    static int parseArgs(String[] args) {
        int     count = 3;
        try {
            Scanner scanner = new Scanner(args[0]).useDelimiter("=");
            if (scanner.next().equals("--count")) {
                if (scanner.hasNextInt()) {
                    count = scanner.nextInt();
                } else {
                    System.out.println(ANSI_CYAN + "Setup default 3 count" + ANSI_RESET);
                }
            }
            else {
                System.out.println("error args");
                System.exit(-1);
            }
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println(ANSI_CYAN + "Setup default 3 count" + ANSI_RESET);
        }
        return count;
    }
}
