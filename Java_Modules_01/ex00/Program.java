public class Program {
    public static void main(String[] args) {
        // Create users
        User john = new User(1, "John", 1000);
        User mike = new User(2, "Mike", 500);

        // Print users id
        System.out.println(john.getName());
        System.out.println(mike.getBalance());

        // Create transactions
        Transaction t1 = new Transaction(mike, john, Transaction.Category.DEBIT, -200);
        Transaction t2 = new Transaction(john, mike, Transaction.Category.CREDIT, 200);

        // Print transactions id
        System.out.println(t1.getAmount());
        System.out.println(t2.getSender().getName());
    }
}
