// Using Thread extends method
public class Program extends Thread {
    private int counter;

    Program(int counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        // Initialise the manager to manage each turn
        Manager manager = new Manager();

        // Using Runnable implements method
        Egg e = new Egg(counter, manager);
        Thread egg = new Thread(e);

        Hen h = new Hen(counter, manager);
        Thread hen = new Thread(h);

        try {
            egg.start();
            hen.start();

            // Wait for the other thread to run the main thread
            egg.join();
            hen.join();

            for (int i = 0; i < counter; i++)
                System.out.println("Human");

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

    }

    public static void main(String[] args) {
        // Check argument and valid path
        if (args.length != 1) {
            System.err.println("Error: Invalid arguments");
            System.exit(-1);
        }

        String argument = args[0];

        if (!argument.startsWith("--count=")) {
            System.err.println("Error: Argument usage\n --count=<NUMBER>");
            System.exit(-1);
        }

        int count = 0;
        try {
            count = Integer.parseInt(argument.substring(8));
            if (count <= 0) {
                System.err.println("Error: Number must be positive");
                System.exit(-1);
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid number format");
            System.exit(-1);
        }

        Program main = new Program(count);
        main.start();
    }
}
