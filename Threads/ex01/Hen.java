public class Hen implements Runnable {
    private int counter;
    private Manager manager;

    Hen(int counter, Manager manager) {
        this.counter = counter;
        this.manager = manager;
    }

    @Override
    public void run() {
        for (int i = 0; i < counter; i++)
            manager.printHen();
    }
}
