import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        
        // Create some users for testing
        User alice = new User("Alice", 1000);
        User bob = new User("Bob", 500);
        User charlie = new User("Charlie", 750);
        
        // Create transactions list
        TransactionsList transactionsList = new TransactionsLinkedList();
        
        // Test 1: Adding transactions
        System.out.println("Test 1: Adding transactions");
        
        Transaction t1 = new Transaction(alice, bob, Transaction.Category.DEBIT, -100);
        Transaction t2 = new Transaction(bob, alice, Transaction.Category.CREDIT, 100);
        Transaction t3 = new Transaction(charlie, alice, Transaction.Category.DEBIT, -50);
        Transaction t4 = new Transaction(alice, charlie, Transaction.Category.CREDIT, 50);
        
        transactionsList.addTransaction(t1);
        transactionsList.addTransaction(t2);
        transactionsList.addTransaction(t3);
        transactionsList.addTransaction(t4);
        
        System.out.println("Added 4 transactions successfully");
        System.out.println();
        
        // Test 2: toArray() method
        System.out.println("Test 2: Converting to array");
        Transaction[] transactions = transactionsList.toArray();
        System.out.println("Array length: " + transactions.length);
        
        for (int i = 0; i < transactions.length; i++) {
            Transaction t = transactions[i];
            System.out.println("Transaction " + (i+1) + ":");
            System.out.println("  ID: " + t.getId());
            System.out.println("  From: " + t.getSender().getName() + " To: " + t.getRecipient().getName());
            System.out.println("  Category: " + t.getCategory() + ", Amount: " + t.getAmount());
            System.out.println();
        }
        
        // Test 3: Remove transaction (middle)
        System.out.println("Test 3: Removing transaction from middle");
        UUID idToRemove = t2.getId();
        System.out.println("Removing transaction with ID: " + idToRemove);
        
        try {
            transactionsList.removeTransactionByID(idToRemove);
            System.out.println("Transaction removed successfully");
            
            Transaction[] afterRemoval = transactionsList.toArray();
            System.out.println("Array length after removal: " + afterRemoval.length);
        } catch (TransactionNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();
        
        // Test 4: Remove transaction (head)
        System.out.println("Test 4: Removing head transaction");
        Transaction[] currentTransactions = transactionsList.toArray();
        if (currentTransactions.length > 0) {
            UUID headId = currentTransactions[0].getId();
            System.out.println("Removing head transaction with ID: " + headId);
            
            try {
                transactionsList.removeTransactionByID(headId);
                System.out.println("Head transaction removed successfully");
                
                Transaction[] afterHeadRemoval = transactionsList.toArray();
                System.out.println("Array length after head removal: " + afterHeadRemoval.length);
            } catch (TransactionNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        System.out.println();
        
        // Test 5: Try to remove non-existent transaction
        System.out.println("Test 5: Removing non-existent transaction (should throw exception)");
        UUID fakeId = UUID.randomUUID();
        System.out.println("Trying to remove fake ID: " + fakeId);
        
        try {
            transactionsList.removeTransactionByID(fakeId);
            System.out.println("ERROR: Should have thrown exception!");
        } catch (TransactionNotFoundException e) {
            System.out.println("Correctly caught exception: " + e.getMessage());
        }
        System.out.println();
        
        // Test 6: Test empty list behavior
        System.out.println("Test 6: Testing empty list");
        TransactionsList emptyList = new TransactionsLinkedList();
        Transaction[] emptyArray = emptyList.toArray();
        System.out.println("Empty list array length: " + emptyArray.length);
        
        try {
            emptyList.removeTransactionByID(UUID.randomUUID());
            System.out.println("ERROR: Should have thrown exception for empty list!");
        } catch (TransactionNotFoundException e) {
            System.out.println("Empty list correctly throws exception: " + e.getMessage());
        }
        System.out.println();
    }
}
