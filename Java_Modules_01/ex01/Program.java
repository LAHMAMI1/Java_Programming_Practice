public class Program {
    public static void main(String[] args) {
        // Create users without manually giving an ID
        User john = new User("John", 1000);
        User mike = new User("Mike", 500);
        User anna = new User("Anna", 750);

        System.out.println(john.getId());
        System.out.println(mike.getId());
        System.out.println(anna.getId());
    }
}
