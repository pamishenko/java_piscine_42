package ex03;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import static ex03.Program.*;

public class FilesService extends Thread {
    public static final String TARGET_DIR = "/Users/pavel/42school_projects/Java_piscine_2/day03/ex03/Download/";

    int threadId;

    public FilesService(int threadId) {
        this.threadId = threadId;
    }

    @Override
    public void run() {
        while (currentId.get() != countUrlsToDownload) {
            File directory = new File(TARGET_DIR);
            if (!directory.exists())
                directory.mkdir();
            int id = currentId.getAndAdd(1);
            System.out.printf("Thread-%d start download file number %d\n", threadId, id);
            try (BufferedInputStream in = new BufferedInputStream(new URL(urls.get(id)).openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(TARGET_DIR + parseFileName(urls.get(id)))) {
                byte[] dataBuffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
            } catch (IOException e) {
                System.err.println(Arrays.toString(e.getStackTrace()));
            }
            System.out.printf("Thread-%d finish download file number %d\n", threadId, id);
        }
    }

    public String parseFileName(String url){
        return url.split("/")[url.split("/").length - 1];
    }
}
