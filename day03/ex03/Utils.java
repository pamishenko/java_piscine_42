package ex03;

import java.util.Scanner;

public class Utils {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static int parseArgs(String[] args) {
        int     count = 3;
        try {
            Scanner scanner = new Scanner(args[0]).useDelimiter("=");
            if (scanner.next().equals("--threadsCount")) {
                if (scanner.hasNextInt()) {
                    count = scanner.nextInt();
                } else {
                    System.out.println(ANSI_CYAN + "Setup default 3 count" + ANSI_RESET);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println(ANSI_CYAN + "Setup default 3 count" + ANSI_RESET);
        }
        return count;
    }
}

