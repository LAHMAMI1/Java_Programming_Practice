import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class DownloadThread extends Thread {
    private DownloadQueue queue;
    private List<String> urls;

    public DownloadThread(DownloadQueue queue, List<String> urls) {
        this.queue = queue;
        this.urls = urls;
    }

    private String extractFileName(String urlString, int fileNum) {
        String[] spiltUrl = urlString.split("/");
        String fileName = spiltUrl[spiltUrl.length - 1];

        // If no extension or empty, use default name
        if (fileName.isEmpty() || !fileName.contains("."))
            fileName = "file_" + fileNum;

        return fileName;
    }

    private void performDownload(String urlString, int fileNum) {
        try {
            URI uri = new URI(urlString);
            URL url = uri.toURL();

            String fileName = extractFileName(urlString, fileNum);

            // Download the file
            try (InputStream in = url.openStream()) {
                Files.copy(in, Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (Exception e) {
            System.err.println("Error in performDownload: " + e);
        }

    }

    private void downloadFile(int fileNum) {
        String threadName = Thread.currentThread().getName();
        String url = urls.get(fileNum - 1);

        System.out.println(threadName + " start download file number " + fileNum);

        // Download logic
        performDownload(url, fileNum);

        System.out.println(threadName + " finish download file number " + fileNum);
    }

    @Override
    public void run() {
        while (true) {
            Integer fileNum = queue.getCurrentFileNumber();

            if (fileNum == null)
                break;

            downloadFile(fileNum);
        }
    }
}