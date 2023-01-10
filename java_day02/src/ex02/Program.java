package ex02;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Program {
    static String baseDir;
    static Path currentPath;
    static Path tempPath;

    public static void main(String[] args) throws IOException {

        baseDir = parseFlag(args).get("--current-folder");
        if (null == baseDir)
            baseDir = "/Users/pavel/test";
        currentPath = Paths.get(baseDir);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            System.out.print("-> ");
            String[] command = reader.readLine().split(" ");
            if (command.length < 1)
                continue;
            switch (command[0]) {
                case "ls":
                {
                    try (Stream<Path> entries = Files.list(currentPath)) {
                        myLs(entries);
                    }
                    break;
                }
                case "cd":
                    myCd(command);
                    break;
                case "mv":
                    myMv(command);
                    break;
                case "exit":
                    return;
                default: {
                    break;
                }
            }
        }
    }

    static void myMv(String[] command){
        if (command.length != 3)
            return;
        tempPath = Paths.get(currentPath + "/" + command[2]);
        if (Files.isDirectory(tempPath)) {
            moveFile(
                    currentPath + "/" + command[1],
                    currentPath + "/" + command[2] + "/" + command[1]
            );
        }
        else {
            File f = new File(currentPath + "/" + command[1]);
            File dest = new File(currentPath + "/" + command[2]);
            f.renameTo(dest);
        }
    }
    static void myCd(String[] command){
        baseDir += "/" + command[1];
        currentPath = Paths.get(baseDir);
        System.out.println(currentPath);
    }

    static void myLs(Stream<Path> entries){
        entries.forEach(
                it -> {
                    try {
                        if (Files.isDirectory(it))
                            System.out.printf(
                                    "%s %d KB\n",
                                    it.getFileName(),
                                    getSizeDir(it) / 1024
                            );
                        else
                            System.out.printf(
                                    "%s %d KB\n",
                                    it.getFileName(),
                                    Files.size(it) / 1024
                            );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
    static long getSizeDir(Path path) {
        long size = 0;
        try (Stream<Path> walk = Files.walk(path)) {
            size = walk
                    .filter(Files::isRegularFile)
                    .mapToLong(p -> {
                        try {
                            return Files.size(p);
                        } catch (IOException e) {
                            System.out.printf("Невозможно получить размер файла %s%n%s", p, e);
                            return 0L;
                        }
                    })
                    .sum();
        } catch (IOException e) {
            System.out.printf("Ошибка при подсчёте размера директории %s", e);
        }
        return size;
    }

    public static Map<String, String> parseFlag(String[] args){
        Map<String, String> flags = new HashMap<>();
        try {
            Arrays.stream(args).forEach(it ->
                    flags.put(
                            it.split("=")[0],
                            it.split("=")[1]
                    )
            );
        }catch (IndexOutOfBoundsException e){
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
        return flags;
    }

    static public void moveFile(String sourcePath, String targetPath) {
        try {
            Files.move(Paths.get(sourcePath), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
