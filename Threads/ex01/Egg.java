public class Egg implements Runnable {
    private int counter;
    private Manager manager;

    Egg(int counter, Manager manager) {
        this.counter = counter;
        this.manager = manager;
    }

    @Override
    public void run() {
        for (int i = 0; i < counter; i++)
            manager.printEgg();
    }
}
