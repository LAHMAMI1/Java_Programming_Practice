import java.util.UUID;

interface TransactionsList {
    void addTransaction(Transaction transaction);
    void removeTransactionByID(UUID id) throws TransactionNotFoundException;
    Transaction[] toArray();
}
