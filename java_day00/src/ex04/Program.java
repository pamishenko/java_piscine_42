import java.util.Scanner;

public class Program {
    static int[]    order;
    static char[]   characters;
    static int[]    charCount;
    static int      arraySize;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        initValues();
        scanChars();
        sortOrder();
        printGraph();
        scanner.close();
    }

    static void initValues() {
        order = new int[65535];
        characters = new char[65535];
        charCount = new int[65535];
        arraySize = 0;
    }

    static void scanChars() {
        char[] scanChar = scanner.nextLine().toCharArray();
        int tmpIndex;

        for (char c : scanChar) {
            tmpIndex = getIndexByChar(c);
            if (tmpIndex == -1) {
                addNewChar(c);
            } else {
                if (charCount[tmpIndex] < 99) {
                    charCount[tmpIndex]++;
                }
            }
        }
    }

    static int  getIndexByChar(char aChar) {
        for (int i = 0; i < arraySize; i++) {
            if (characters[i] == aChar)
                return i;
        }
        return -1;
    }

    static void addNewChar(char aChar) {
        characters[arraySize] = aChar;
        charCount[arraySize] = 1;
        arraySize++;
    }

    static void sortOrder() {
        int temp;

        for (int i = 0; i < arraySize; i++)
            order[i] = i;

        for (int i = 0; i < arraySize; i++) {
            for (int j = i; j < arraySize; j++) {
                if (charCount[order[j]] > charCount[order[i]]) {
                    temp = order[j];
                    order[j] = order[i];
                    order[i] = temp;
                }
            }
        }

        for (int i = 0; i < arraySize; i++) {
            for (int j = i; j < arraySize; j++) {
                if (charCount[order[i]] == charCount[order[j]])
                    if (characters[order[i]] > characters[order[j]]) {
                        temp = order[j];
                        order[j] = order[i];
                        order[i] = temp;
                    }
            }
        }
    }

     static double getCorrection() {
        int i;
        double max = 0;
         for (i = 0; i < 10; i++) {
             if (i == arraySize)
                 break;
             if (max < charCount[order[i]])
                 max = charCount[order[i]];
         }
         return (10 / max);
     }

    private static void printGraph() {
        for (int i = 11; i > 0 && arraySize > 0; i--) {
            for (int j = 0; j < 10 && j < arraySize; j++) {
                if ((int)(charCount[order[j]] * getCorrection()) == i - 1) {
                    System.out.printf("%3d", charCount[order[j]]);
                } else if (charCount[order[j]]*getCorrection() > i - 1){
                    System.out.printf("%3s", "#");
                }
            }
            System.out.println();
        }
        for (int j = 0; j < 10 && j < arraySize; j++) {
            System.out.printf("%3s", characters[order[j]]);
        }
        System.out.println();
    }
}
