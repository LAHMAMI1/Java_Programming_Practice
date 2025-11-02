public class SumThread extends Thread {
    private int[] arr;
    private int start, end;
    private int[] results; // Shared results array
    private int threadIndex;

    public SumThread(int[] arr, int start, int end, int[] results, int threadIndex) {
        this.arr = arr;
        this.start = start;
        this.end = end;
        this.results = results;
        this.threadIndex = threadIndex;
    }

    @Override
    public void run() {
        int sum = 0;
        for (int i = start; i < end; i++)
            sum += arr[i];

        results[threadIndex] = sum;
        System.out.println("Thread " + (threadIndex + 1) +
                ": from " + start + " to " + (end - 1) +
                " sum is " + sum);
    }
}
