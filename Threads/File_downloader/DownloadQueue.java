public class DownloadQueue {
    private int currentFileNumber = 0;
    private int totalFiles;
    
    public DownloadQueue(int totalFiles) {
        this.totalFiles = totalFiles;
    }

    public synchronized Integer getCurrentFileNumber() {
        if (currentFileNumber < totalFiles)
            return ++currentFileNumber;
        return null;
    }
}