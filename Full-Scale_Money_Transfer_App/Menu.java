import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private boolean isDev;
    private TransactionsService service;

    public Menu(boolean isDev, TransactionsService service) {
        this.isDev = isDev;
        this.service = service;
    }

    private void addAUser(Scanner sc) {
        try {
            sc.nextLine();
            System.out.println("Enter a user name and a balance");

            String name = sc.next();
            int balance = sc.nextInt();
            User user = new User(name, balance);

            service.addUser(user);

            System.out.println("User with id = " + user.getId() + " is added");
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input format. Please enter a valid name and numeric balance.");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("Error: Failed to add user - " + e.getMessage());
        }
        System.out.println("---------------------------------------------------------");
    }

    private void viewUserBalances(Scanner sc) {
        try {
            sc.nextLine();
            System.out.println("Enter a user ID");

            int userId = sc.nextInt();
            int balance = service.retrieveUserBalance(userId);
            String name = service.getUserName(userId);

            System.out.println(name + " - " + balance);
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input format. Please enter a valid numeric user ID.");
            sc.nextLine();
        } catch (UserNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Failed to retrieve user balance - " + e.getMessage());
        }
        System.out.println("---------------------------------------------------------");
    }

    private void performATransfer(Scanner sc) {
        try {
            sc.nextLine();
            System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");

            int senderId = sc.nextInt();
            int recipientId = sc.nextInt();
            int amount = sc.nextInt();

            service.transferTransaction(senderId, recipientId, amount);
            System.out.println("The transfer is completed");
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input format. Please enter valid numeric values for IDs and amount.");
            sc.nextLine();
        } catch (UserNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalTransactionException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Failed to perform transfer - " + e.getMessage());
        }
        System.out.println("---------------------------------------------------------");
    }

    private void viewAllTransactions(Scanner sc) {
        try {
            sc.nextLine();
            System.out.println("Enter a user ID");

            int userId = sc.nextInt();
            Transaction[] transactions = service.getTransfers(userId);

            for (Transaction transaction : transactions) {
                String recipientName = transaction.getRecipient().getName();
                int recipientId = transaction.getRecipient().getId();
                int amount = transaction.getAmount();
                UUID transactionId = transaction.getId();

                System.out.println(
                        "To " + recipientName + "(id = " + recipientId + ") " + amount + " with id = " + transactionId);
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input format. Please enter a valid numeric user ID.");
            sc.nextLine();
        } catch (UserNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Failed to retrieve transactions - " + e.getMessage());
        }
        System.out.println("---------------------------------------------------------");
    }

    private void removeATransferById(Scanner sc) {
        try {
            sc.nextLine();
            System.out.println("Enter a user ID and a transfer ID");

            int userId = sc.nextInt();
            String inputTrId = sc.next();
            UUID transferId = UUID.fromString(inputTrId);

            Transaction[] transactions = service.getTransfers(userId);
            String recipientName = null;
            int recipientId = 0;
            int amount = 0;
            for (Transaction transaction : transactions) {
                if (transaction.getId().equals(transferId)) {
                    recipientName = transaction.getRecipient().getName();
                    recipientId = transaction.getRecipient().getId();
                    amount = transaction.getAmount();
                    break;
                }
            }

            service.removeTransaction(userId, transferId);
            System.out.println("Transfer to " + recipientName + "(id = " + recipientId + ") " + (-amount) + " removed");
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input format. Please enter a valid numeric user ID.");
            sc.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid UUID format. Please enter a valid transaction ID.");
        } catch (UserNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (TransactionNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Failed to remove transaction - " + e.getMessage());
        }
        System.out.println("---------------------------------------------------------");
    }

    void checkTransferValidity(Scanner sc) {
        try {
            sc.nextLine();
            System.out.println("Check results:");

            Transaction[] unpairedTransactions = service.validityTransactions();
            if (unpairedTransactions.length > 0) {
                for (Transaction transaction : unpairedTransactions) {
                    String recipientName = transaction.getRecipient().getName();
                    int recipientId = transaction.getRecipient().getId();
                    String transactionId = transaction.getId().toString();
                    String senderName = transaction.getSender().getName();
                    int senderId = transaction.getSender().getId();
                    int amount = transaction.getAmount();

                    System.out.println(recipientName + "(id = " + recipientId + ") has an unacknowledged transfer id = "
                            + transactionId + " from " + senderName + "(id = " + senderId + ") for " + amount);
                }
            } else
                System.out.println("All Transactions are valid");
        } catch (Exception e) {
            System.out.println("Error: Failed to check transfer validity - " + e.getMessage());
        }
        System.out.println("---------------------------------------------------------");
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 7) {

            System.out.println("1. Add a user");
            System.out.println("2. View user balances");
            System.out.println("3. Perform a transfer");
            System.out.println("4. View all transactions for a specific user");
            if (isDev) {
                System.out.println("5. DEV - remove a transfer by ID");
                System.out.println("6. DEV - check transfer validity");
            }
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
                        viewAllTransactions(sc);
                        break;
                    case 5:
                        if (isDev)
                            removeATransferById(sc);
                        else {
                            System.out.println("Invalid option. Please choose from available menu options.");
                            System.out.println("---------------------------------------------------------");
                        }
                        break;
                    case 6:
                        if (isDev)
                            checkTransferValidity(sc);
                        else {
                            System.out.println("Invalid option. Please choose from available menu options.");
                            System.out.println("---------------------------------------------------------");
                        }
                        break;
                    case 7:
                        break;
                    default:
                        if (isDev)
                            System.out.println("Please enter a number between 1 and 7");
                        else
                            System.out.println("Please enter a number between 1 and 5");
                        System.out.println("---------------------------------------------------------");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a valid menu option number.");
                System.out.println("---------------------------------------------------------");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Error: An unexpected error occurred - " + e.getMessage());
                System.out.println("---------------------------------------------------------");
                sc.nextLine();
            }
        }
        sc.close();
    }
}
