public class User {
    private final int id;
    private String name;
    private int balance;

    public User(String name, int balance) {
        if (balance < 0)
            throw new IllegalArgumentException("Balance cannot be negative");

        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBalance(int balance) {
        if (balance < 0)
            throw new IllegalArgumentException("Balance cannot be negative");
        
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }
}