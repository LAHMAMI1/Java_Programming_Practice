public class Hen implements Runnable {
    private int counter;

    Hen(int counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < counter; i++)
            System.out.println("Hen");
    }
}
