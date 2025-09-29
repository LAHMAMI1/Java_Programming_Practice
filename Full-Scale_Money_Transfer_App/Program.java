public class Program {
    public static void main(String[] args) {
        boolean isDev = false;

        if (args.length > 0) {
            if (args[0].equals("--profile=dev"))
            isDev = true;
            else {
                System.err.println("Error: Invalid arguments");
                System.exit(-1);
            }
        }
        
        UsersList usersList = new UsersArrayList();
        TransactionsService service = new TransactionsService(usersList);
        
        Menu menu = new Menu(isDev, service);
        menu.run();
    }
}