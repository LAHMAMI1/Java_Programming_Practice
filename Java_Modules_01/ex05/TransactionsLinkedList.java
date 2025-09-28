import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private Transaction head;

    @Override
    public void addTransaction(Transaction transaction) {
        if (transaction == null)
            throw new IllegalArgumentException("Transaction NULL");

        transaction.setNext(head);
        head = transaction;
    }

    @Override
    public void removeTransactionByID(UUID id) throws TransactionNotFoundException {
        if ((head != null) && (head.getId().equals(id))) {
            head = head.getNext();
            return;
        }

        Transaction current = head;
        while (current.getNext() != null) {
            if (current.getNext().getId().equals(id)) {
                current.setNext(current.getNext().getNext());
                return;
            }
            current = current.getNext();
        }

        throw new TransactionNotFoundException();
    }

    @Override
    public Transaction[] toArray() {
        // Count transactions
        int count = 0;
        Transaction current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }

        // Create array of correct size
        Transaction[] array = new Transaction[count];

        // Fill the array
        current = head;
        int i = 0;
        while (current != null) {
            array[i] = current;
            current = current.getNext();
            i++;
        }

        return array;
    }
}
