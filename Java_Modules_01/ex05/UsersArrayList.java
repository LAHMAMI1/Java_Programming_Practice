public class UsersArrayList implements UsersList {
    private User[] users;
    private int size;

    public UsersArrayList() {
        this.users = new User[10];
        this.size = 0;
    }

    @Override
    public void addUser(User user) {
        if (user == null)
            throw new IllegalArgumentException("User cannot be null");

        if (size >= users.length) {
            int newSize = (3 * users.length) / 2;
            User[] newArr = new User[newSize];

            System.arraycopy(users, 0, newArr, 0, size);

            users = newArr;
        }

        users[size] = user;
        size++;
    }

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        for (int i = 0; i < size; i++) {
            if (id == users[i].getId())
                return users[i];
        }

        throw new UserNotFoundException();
    }

    @Override
    public User getUserByIndex(int index) throws UserNotFoundException {
        if ((index < 0 || index >= size) || (users[index] == null))
            throw new UserNotFoundException();

        return users[index];
    }

    @Override
    public int getNumOfUsers() {
        return size;
    }
}
