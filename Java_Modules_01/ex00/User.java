public class User {
    private int id;
    private String name;
    private int balance;

    public User(int id, String name, int balance) {
        if (balance < 0)
            throw new IllegalArgumentException("Balance cannot be negative");

        this.id = id;
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