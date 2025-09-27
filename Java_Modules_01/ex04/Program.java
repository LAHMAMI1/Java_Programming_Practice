import java.util.UUID;

public class Program {
    public static void main(String[] args) {

        // Initialize the service
        UsersList usersList = new UsersArrayList();
        TransactionsService service = new TransactionsService(usersList);

        // Test 1: Adding users
        System.out.println("Test 1: Adding users to the service");
        User alice = new User("Alice", 1000);
        User bob = new User("Bob", 500);
        User charlie = new User("Charlie", 750);

        service.addUser(alice);
        service.addUser(bob);
        service.addUser(charlie);

        System.out.println("Added 3 users:");
        System.out.println("- Alice (ID: " + alice.getId() + ", Balance: $" + alice.getBalance() + ")");
        System.out.println("- Bob (ID: " + bob.getId() + ", Balance: $" + bob.getBalance() + ")");
        System.out.println("- Charlie (ID: " + charlie.getId() + ", Balance: $" + charlie.getBalance() + ")");
        System.out.println();

        // Test 2: Retrieve user balances
        System.out.println("Test 2: Retrieving user balances");
        try {
            System.out.println("Alice's balance: $" + service.retrieveUserBalance(alice.getId()));
            System.out.println("Bob's balance: $" + service.retrieveUserBalance(bob.getId()));
            System.out.println("Charlie's balance: $" + service.retrieveUserBalance(charlie.getId()));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();

        // Test 3: Valid transfers
        System.out.println("Test 3: Performing valid transfers");
        try {
            System.out.println("Transfer $200 from Alice to Bob");
            service.transferTransaction(alice.getId(), bob.getId(), 200);
            System.out.println("Transfer completed successfully");

            System.out.println("Transfer $100 from Bob to Charlie");
            service.transferTransaction(bob.getId(), charlie.getId(), 100);
            System.out.println("Transfer completed successfully");

            System.out.println("Updated balances:");
            System.out.println("- Alice: $" + service.retrieveUserBalance(alice.getId()));
            System.out.println("- Bob: $" + service.retrieveUserBalance(bob.getId()));
            System.out.println("- Charlie: $" + service.retrieveUserBalance(charlie.getId()));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();

        // Test 4: Insufficient funds transfer
        System.out.println("Test 4: Testing insufficient funds (should fail)");
        try {
            System.out.println("Attempting to transfer $2000 from Bob (who only has $600)");
            service.transferTransaction(bob.getId(), alice.getId(), 2000);
        } catch (IllegalTransactionException e) {
            System.out.println("Correctly prevented transfer: " + e.getMessage());
        }
        System.out.println();

        // Test 5: Invalid transfer amount
        System.out.println("Test 5: Testing invalid transfer amounts");
        try {
            System.out.println("Attempting to transfer $0");
            service.transferTransaction(alice.getId(), bob.getId(), 0);
        } catch (IllegalTransactionException e) {
            System.out.println("Correctly prevented zero transfer: " + e.getMessage());
        }

        try {
            System.out.println("Attempting to transfer negative amount");
            service.transferTransaction(alice.getId(), bob.getId(), -100);
        } catch (IllegalTransactionException e) {
            System.out.println("Correctly prevented negative transfer: " + e.getMessage());
        }
        System.out.println();

        // Test 6: Retrieve user transaction history
        System.out.println("Test 6: Retrieving user transaction histories");
        try {
            System.out.println("Alice's transactions:");
            Transaction[] aliceTransactions = service.getTransfers(alice.getId());
            for (Transaction t : aliceTransactions)
                System.out.println("  - " + t.getCategory() + ": $" + t.getAmount() + " (ID: " + t.getId() + ")");

            System.out.println("Bob's transactions:");
            Transaction[] bobTransactions = service.getTransfers(bob.getId());
            for (Transaction t : bobTransactions)
                System.out.println("  - " + t.getCategory() + ": $" + t.getAmount() + " (ID: " + t.getId() + ")");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();

        // Test 7: Check validity (should be valid at this point)
        System.out.println("Test 7: Checking transaction validity (should be clean)");
        Transaction[] invalidTransactions = service.validityTransactions();
        System.out.println("Number of unpaired transactions: " + invalidTransactions.length);
        if (invalidTransactions.length == 0)
            System.out.println("All transactions are properly paired!");
        else {
            System.out.println("Found unpaired transactions:");
            for (Transaction t : invalidTransactions)
                System.out.println("  - " + t.getCategory() + ": $" + t.getAmount() + " (ID: " + t.getId() + ")");
        }
        System.out.println();

        // Test 8: Remove a transaction and check validity
        System.out.println("Test 8: Simulating data corruption (removing one transaction)");
        try {
            Transaction[] aliceTransactions = service.getTransfers(alice.getId());
            if (aliceTransactions.length > 0) {
                UUID transactionToRemove = aliceTransactions[0].getId();
                System.out.println("Removing transaction with ID: " + transactionToRemove);

                service.removeTransaction(alice.getId(), transactionToRemove);
                System.out.println("Transaction removed from Alice");

                // Now check validity - should find unpaired transaction
                System.out.println("Checking validity after corruption");
                Transaction[] unpairedTransactions = service.validityTransactions();
                System.out.println("Number of unpaired transactions: " + unpairedTransactions.length);

                if (unpairedTransactions.length > 0) {
                    System.out.println("Successfully detected data corruption:");
                    for (Transaction t : unpairedTransactions) {
                        System.out.println("  - Unpaired " + t.getCategory() + ": $" + t.getAmount() +
                                " from " + t.getSender().getName() +
                                " to " + t.getRecipient().getName() +
                                " (ID: " + t.getId() + ")");
                    }
                } else
                    System.out.println("Failed to detect corruption");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();

        // Test 9: Invalid user operations
        System.out.println("Test 9: Testing invalid user operations");
        try {
            System.out.println("Attempting to get balance for non-existent user...");
            service.retrieveUserBalance(999);
        } catch (Exception e) {
            System.out.println("Correctly handled invalid user: " + e.getMessage());
        }

        try {
            System.out.println("Attempting transfer with non-existent sender...");
            service.transferTransaction(999, bob.getId(), 100);
        } catch (Exception e) {
            System.out.println("Correctly handled invalid sender: " + e.getMessage());
        }
        System.out.println();
    }
}
