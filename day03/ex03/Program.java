package ex03;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Program {
        static Map<Integer, String> urls;

    static {
        try {
            urls = ParserUrls.getUrls();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int countUrlsToDownload = urls.size() + 1;
    volatile static AtomicInteger currentId = new AtomicInteger(1);
    public static void main(String[] args) throws IOException {
        int countThreads = Utils.parseArgs(args);
        FilesService[] threads = new FilesService[countThreads];
        for (int i = 0; i < countThreads; i++){
            threads[i] = new FilesService(i);
        }
        Arrays.stream(threads).forEach(FilesService::start);
    }

}
