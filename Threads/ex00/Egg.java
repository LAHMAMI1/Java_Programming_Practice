public class Egg implements Runnable {
    private int counter;

    Egg(int counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < counter; i++)
            System.out.println("Egg");
    }
}
