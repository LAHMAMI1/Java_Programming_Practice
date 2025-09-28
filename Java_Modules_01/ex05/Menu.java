import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private boolean isDev;
    private TransactionsService service;

    public Menu(boolean isDev, TransactionsService service) {
        this.isDev = isDev;
        this.service = service;
    }

    private void addAUser(Scanner sc) {
        sc.nextLine();
        System.out.println("Enter a user name and a balance");

        String name = sc.next();
        int balance = sc.nextInt();
        User user = new User(name, balance);

        service.addUser(user);

        System.out.println("User with id = " + user.getId() + " is added");
        System.out.println("---------------------------------------------------------");
    }

    private void viewUserBalances(Scanner sc) {
        sc.nextLine();
        System.out.println("Enter a user ID");

        int userId = sc.nextInt();
        int balance = service.retrieveUserBalance(userId);
        String name = service.getUserName(userId);

        System.out.println(name + " - " + balance);
        System.out.println("---------------------------------------------------------");
    }

    private void performATransfer(Scanner sc) {
        sc.nextLine();
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");

        int senderId = sc.nextInt();
        int recipientId = sc.nextInt();
        int amount = sc.nextInt();

        service.transferTransaction(senderId, recipientId, amount);
        System.out.println("The transfer is completed");
        System.out.println("---------------------------------------------------------");
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 7) {

            if (isDev) {
                System.out.println("1. Add a user");
                System.out.println("2. View user balances");
                System.out.println("3. Perform a transfer");
                System.out.println("4. View all transactions for a specific user");
                System.out.println("5. DEV - remove a transfer by ID");
                System.out.println("6. DEV - check transfer validity");
                System.out.println("7. Finish execution");

                try {
                    choice = sc.nextInt();

                    switch (choice) {
                        case 1:
                            addAUser(sc);
                            break;
                        case 2:
                            viewUserBalances(sc);
                            break;
                        case 3:
                            performATransfer(sc);
                            break;
                        case 4:
                            sc.nextLine();
                            System.out.println("Enter a user ID");

                            int userId = sc.nextInt();
                            
                            break;
                        case 5:
                            System.out.println("test 5");
                            break;
                        case 6:
                            System.out.println("test 6");
                            break;
                        case 7:
                            System.out.println("test 7");
                            break;
                        default:
                            System.out.println("Please enter a number between 1 and 7");
                            System.out.println("---------------------------------------------------------");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("That's not a number!");
                    System.out.println("---------------------------------------------------------");
                    sc.nextLine();
                }
            }
        }
        sc.close();
    }
}
