package ex01;
import java.io.*;
import java.util.*;

import static java.lang.Math.sqrt;
import static java.lang.System.exit;

public class Program {
    private static final String FILE_RESULT = "/Users/pavel/21projects/java_intencive/Java_Bootcamp._Day02-0/src/ex01/dictionary.txt";

    public static void main(String[] args) throws IOException {
        if (args.length != 2)
            exit(-1);
        String file1 = args[0];
        String file2 = args[1];

        FileOutputStream fileOutputStream = new FileOutputStream(FILE_RESULT, false);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

        Set<String> dictionary = new HashSet<>();
        Map<String, Long> fromFile1 = new HashMap<>();
        Map<String, Long> fromFile2 = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file1)))) {
            while (reader.ready()) {
                dictionary.addAll(Arrays.asList(reader.readLine().split(" ")));
            }
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file2)))) {
            while (reader.ready()) {
                dictionary.addAll(Arrays.asList(reader.readLine().split(" ")));
            }
        }
        dictionary.forEach(word -> fromFile1.put(word, 0L));
        dictionary.forEach(word -> fromFile2.put(word, 0L));

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file1)))) {
            while (reader.ready()) {
                Arrays.asList(reader.readLine().split(" ")).forEach(word ->
                fromFile1.put(word, fromFile1.get(word) + 1)
                );
            }
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file2)))) {
            while (reader.ready()) {
                Arrays.asList(reader.readLine().split(" ")).forEach(word ->
                        fromFile2.put(word, fromFile2.get(word) + 1)
                );
            }
        }

        System.out.printf("Similarity = %.4s\n", calculateSimilarity(fromFile1, fromFile2));
        bufferedWriter.write(dictionary.toString());
        bufferedWriter.flush();
        fileOutputStream.close();
    }

    static double calculateSimilarity(Map<String, Long> map1, Map<String, Long> map2) {
        long numerator = 0L;
        double denominator = 0;

        for (Map.Entry<String, Long> value : map1.entrySet()){
            numerator += value.getValue() * map2.get(value.getKey());
        }

        long modFor1 = 0L;
        for (Map.Entry<String, Long> value : map1.entrySet()){
            modFor1 += value.getValue() * value.getValue();
        }
        long modFor2 = 0L;
        for (Map.Entry<String, Long> value : map2.entrySet()){
            modFor2 += value.getValue() * value.getValue();
        }
        denominator = sqrt(modFor1) * sqrt(modFor2);

        if (denominator == 0 || numerator == 0)
            return 0;
        return numerator/denominator ;
    }
}
