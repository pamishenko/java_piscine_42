package ex00;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Program {

    public static Map<String, int[]> map;
    private static int maxLenSignature;

    private static final String FILE_RESULT = "/Users/pavel/21projects/java_intencive/Java_Bootcamp._Day02-0/src/ex00/result.txt";
    private static final String FILE_SIGNAL = "/Users/pavel/21projects/java_intencive/Java_Bootcamp._Day02-0/src/ex00/signatures.txt";

    public static void main(String[] args) throws IOException {
        init();
        System.out.print("-> ");
        Scanner scanner = new Scanner(System.in);
        FileOutputStream fileOutputStream = new FileOutputStream(FILE_RESULT, false);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

        String fileName = scanner.nextLine();

        while (!fileName.equals("42")) {
            String signature = mappingBytes(getBytesFromFile(fileName));
            if (!signature.equals("")) {
                bufferedWriter.write(signature + "\n");
                bufferedWriter.flush();
                System.out.println("PROCESSED");
            }
            else
                System.out.println("UNDEFINED");
            System.out.print("-> ");
            fileName = scanner.nextLine();
        }
        bufferedWriter.close();
        fileOutputStream.close();
        scanner.close();
    }

    static void init() throws IOException {
        map = new HashMap<>();
        Path signal = Paths.get(FILE_SIGNAL);
        List<String> line = Files.readAllLines(signal);
        line.forEach(it -> map.put(it.split(",")[0], getBytesFromStringArray(it.split(" "))));
    }

    public static int[] getBytesFromFile(String fileName) {
        int[] buffer = new int[40];

        try(InputStream fis = new FileInputStream(fileName)) {
            for (int i = 0; i < 40; i++) {
                buffer[i] = fis.read();
            }
        } catch (IOException e) {
            System.err.println("File not found");;
        }
        return buffer;
    }

    static int[] getBytesFromStringArray(String[] str){
        if (str.length < maxLenSignature)
            maxLenSignature = str.length;
        int[] bytes = new int[str.length - 1];
        for (int i = 1; i < str.length; i++){
            bytes[i - 1] = Integer.valueOf(str[i], 16);
        }
        return bytes;
    }
    public static String mappingBytes(int[] arr) {
        boolean result;
        String format = "";
        for (Map.Entry<String, int[]> map : map.entrySet()) {
            result = true;
            for (int i = 0; i < map.getValue().length; i++) {
                if (arr[i] != map.getValue()[i]){
                    result = false;
                    break;
                }
            }
            if (result) {
                format = map.getKey();
                break;
            }
        }
        return format;
    }
}
