import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class TransactionsService {
    private UsersList usersList;

    public TransactionsService(UsersList usersList) {
        this.usersList = usersList;
    }

    public void addUser(User user) {
        usersList.addUser(user);
    }

    public int retrieveUserBalance(int userId) {
        return usersList.getUserById(userId).getBalance();
    }

    public String getUserName(int userId) {
        return usersList.getUserById(userId).getName();
    }

    public void transferTransaction(int senderId, int recipientId, int amount) {
        // 1. Validate input
        if (amount <= 0)
            throw new IllegalTransactionException("Transfer amount must be positive");

        // 3. Check if sender has sufficient funds
        User sender = usersList.getUserById(senderId);
        if (sender.getBalance() < amount)
            throw new IllegalTransactionException("Insufficient funds");

        User recipient = usersList.getUserById(recipientId);

        // 4. Generate shared transaction ID
        UUID transactionId = UUID.randomUUID();

        // 5. Create two transactions with SAME ID
        Transaction debitTransaction = new Transaction(transactionId, recipient, sender, Transaction.Category.DEBIT,
                -amount);
        Transaction creditTransaction = new Transaction(transactionId, recipient, sender, Transaction.Category.CREDIT,
                amount);

        // 6. Add transactions to respective users
        sender.getTransactions().addTransaction(debitTransaction);
        recipient.getTransactions().addTransaction(creditTransaction);

        // 7. Update balances
        sender.setBalance(sender.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);
    }

    public Transaction[] getTransfers(int userId) {
        return usersList.getUserById(userId).getTransactions().toArray();
    }

    public void removeTransaction(int userId, UUID transactionId) {
        usersList.getUserById(userId).getTransactions().removeTransactionByID(transactionId);
    }

    public Transaction[] validityTransactions() {
        TransactionsList unpairedTransactions = new TransactionsLinkedList();
        ArrayList<Transaction> allTransactionsList = new ArrayList<Transaction>();
        HashMap<UUID, Integer> transactionCounts = new HashMap<UUID, Integer>();

        for (int i = 0; i < usersList.getNumOfUsers(); i++) {
            User user = usersList.getUserByIndex(i);
            Transaction[] transactions = user.getTransactions().toArray();

            for (Transaction transaction : transactions) {
                allTransactionsList.add(transaction);
                UUID id = transaction.getId();
                transactionCounts.put(id, transactionCounts.getOrDefault(id, 0) + 1);
            }
        }

        for (Transaction transaction : allTransactionsList) {
            UUID id = transaction.getId();
            if (transactionCounts.get(id) != 2)
                unpairedTransactions.addTransaction(transaction);
        }

        return unpairedTransactions.toArray();
    }
}
