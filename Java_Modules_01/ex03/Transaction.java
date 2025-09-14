import java.util.UUID;

public class Transaction {
    public enum Category {
        DEBIT,
        CREDIT
    };

    private UUID id;
    private User recipient;
    private User sender;
    private Category category;
    private int amount;
    private Transaction next;

    public Transaction(User recipient, User sender, Category category, int amount) {
        if (category == Category.DEBIT && amount > 0)
            throw new IllegalArgumentException("Debit transaction must have negative amount");
        if (category == Category.CREDIT && amount < 0)
            throw new IllegalArgumentException("Credit transaction must have positive amount");
            
        this.id = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.category = category;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getSender() {
        return sender;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setAmount(int amount) {
        if (category == Category.DEBIT && amount > 0)
            throw new IllegalArgumentException("Debit transaction must have negative amount");
        if (category == Category.CREDIT && amount < 0)
            throw new IllegalArgumentException("Credit transaction must have positive amount");
        
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public Transaction getNext() {
        return next;
    }

    public void setNext(Transaction next) {
        this.next = next;
    }
}