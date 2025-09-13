public class Program {
    public static void main(String[] args) {

        // Create the users list
        UsersList usersList = new UsersArrayList();

        // Test 1: Adding users and basic functionality
        System.out.println("Test 1: Adding users");
        System.out.println("Number of users initially: " + usersList.getNumOfUsers());

        usersList.addUser(new User("Alice", 1000));
        usersList.addUser(new User("Bob", 500));
        usersList.addUser(new User("Charlie", 750));

        System.out.println("Added 3 users. Number of users now: " + usersList.getNumOfUsers());
        System.out.println();

        // Test 2: Retrieve users by index
        System.out.println("Test 2: Retrieving users by index");
        try {
            for (int i = 0; i < usersList.getNumOfUsers(); i++) {
                User user = usersList.getUserByIndex(i);
                System.out.println("Index " + i + ": " + user.getName() +
                        " (ID: " + user.getId() + ", Balance: " + user.getBalance() + ")");
            }
        } catch (UserNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();

        // Test 3: Retrieve users by ID
        System.out.println("Test 3: Retrieving users by ID");
        try {
            User user1 = usersList.getUserById(1);
            System.out.println("User with ID 1: " + user1.getName());

            User user2 = usersList.getUserById(2);
            System.out.println("User with ID 2: " + user2.getName());

            User user3 = usersList.getUserById(3);
            System.out.println("User with ID 3: " + user3.getName());
        } catch (UserNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();

        // Test 4: Test invalid ID
        System.out.println("Test 4: Testing invalid ID (should throw exception)");
        try {
            User invalidUser = usersList.getUserById(999);
            System.out.println("Found user: " + invalidUser.getName()); // Should not reach here
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        // Test 5: Test invalid index
        System.out.println("Test 5: Testing invalid indices (should throw exceptions)");
        try {
            User invalidUser = usersList.getUserByIndex(-1);
            System.out.println("Found user: " + invalidUser.getName()); // Should not reach here
        } catch (UserNotFoundException e) {
            System.out.println("for index -1: " + e.getMessage());
        }

        try {
            User invalidUser = usersList.getUserByIndex(10);
            System.out.println("Found user: " + invalidUser.getName()); // Should not reach here
        } catch (UserNotFoundException e) {
            System.out.println("for index 10: " + e.getMessage());
        }
        System.out.println();

        // Test 6: Test dynamic array resizing (add more than 10 users)
        System.out.println("Test 6: Testing dynamic array resizing (adding 12 more users)");
        System.out.println("Current users: " + usersList.getNumOfUsers());

        for (int i = 4; i <= 15; i++)
            usersList.addUser(new User("User" + i, i * 100));

        System.out.println("After adding 12 more users: " + usersList.getNumOfUsers());

        // Test 7: Verify all users are still accessible after resize
        System.out.println("Test 7: Verifying all users after resize");
        try {
            System.out.println("First user: " + usersList.getUserByIndex(0).getName());
            System.out.println("Last user: " + usersList.getUserByIndex(usersList.getNumOfUsers() - 1).getName());
            System.out.println("User with ID 5: " + usersList.getUserById(5).getName());
            System.out.println("User with ID 15: " + usersList.getUserById(15).getName());
        } catch (UserNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();
    }
}
