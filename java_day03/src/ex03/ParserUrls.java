package ex03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParserUrls {
    public static final String FILES_URLS_TXT = "/Users/pavel/42school_projects/Java_piscine_2/day03/ex03/files_urls.txt";
    public static Map<Integer, String> getUrls() throws IOException {
        Path signal = Paths.get(FILES_URLS_TXT);
        List<String> line = Files.readAllLines(signal);
        return line.stream().collect(
                Collectors.toMap(s -> Integer.valueOf(s.split(" ")[0]), s -> s.split(" ")[1]));
    }
}
