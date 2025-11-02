// Shared object to coordinate between threads
public class Manager {
    private boolean eggTurn = true;

    public synchronized void printEgg() {
        while (!eggTurn) {
            try {
                wait(); // Sleep thread
            } catch (InterruptedException e) {
                System.err.println("Egg: " + e.getMessage());
            }
        }

        System.out.println("Egg");
        eggTurn = false;
        notify(); // Wake up thread
    }

    public synchronized void printHen() {
        while (eggTurn) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("Hen " + e.getMessage());
            }
        }

        System.out.println("Hen");
        eggTurn = true;
        notify();
    }
}
