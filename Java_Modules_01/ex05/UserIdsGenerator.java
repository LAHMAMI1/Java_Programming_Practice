public class  UserIdsGenerator {
    private static UserIdsGenerator instance;
    private int lastId = 0;

    private UserIdsGenerator() {}

    public static UserIdsGenerator getInstance() {
        if (instance == null)
            instance = new UserIdsGenerator();
        
        return instance;
    }

    public int generateId() {
        return ++lastId; // so that the first user gets 1
    }
}
