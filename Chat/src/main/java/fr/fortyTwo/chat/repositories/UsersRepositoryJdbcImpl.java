package fr.fortyTwo.chat.repositories;

import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

import fr.fortyTwo.chat.models.Chatroom;
import fr.fortyTwo.chat.models.User;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private final DataSource datasource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.datasource = dataSource;
    }

    private List<User> getStructuredUsersData(ResultSet result) throws SQLException {
        Map<Long, User> userMap = new HashMap<>();
        Map<Long, Set<Long>> createdRoomIds = new HashMap<>();
        Map<Long, Set<Long>> socializedRoomIds = new HashMap<>();

        while (result.next()) {

            // Create user if not exists
            Long userId = result.getLong("user_id");
            if (!userMap.containsKey(userId)) {
                User user = new User(
                        userId,
                        result.getString("login"),
                        result.getString("password"),
                        new ArrayList<>(),
                        new ArrayList<>());
                
                userMap.put(userId, user);
                createdRoomIds.put(userId, new HashSet<>());
                socializedRoomIds.put(userId, new HashSet<>());
            }

            // Add created room if present and not duplicate
            Long createdRoomId = result.getLong("created_room_id");
            if (!result.wasNull() && !createdRoomIds.get(userId).contains(createdRoomId)) {
                Chatroom createdRoom = new Chatroom(
                        createdRoomId,
                        result.getString("created_room_name"),
                        null,
                        null);
                
                userMap.get(userId).getCreatedRooms().add(createdRoom);
                createdRoomIds.get(userId).add(createdRoomId);
            }

            // Add socialized room if present and not duplicate
            Long socializedRoomId = result.getLong("socialized_room_id");
            if (!result.wasNull() && !socializedRoomIds.get(userId).contains(socializedRoomId)) {
                Chatroom socializedRoom = new Chatroom(
                        socializedRoomId,
                        result.getString("socialized_room_name"),
                        null,
                        null);
                userMap.get(userId).getSocializedRooms().add(socializedRoom);
                socializedRoomIds.get(userId).add(socializedRoomId);
            }
        }

        return new ArrayList<>(userMap.values());
    }

    @Override
    public List<User> findAll(int page, int size) {
        String sql = """
                WITH paginated_users AS (
                    SELECT user_id, login, password
                    FROM users
                    ORDER BY user_id
                    LIMIT ? OFFSET ?
                ), created_rooms AS (
                    SELECT
                        c.chatroom_id,
                        c.chatroom_name,
                        c.owner_id AS user_id
                    FROM chatrooms c
                    WHERE c.owner_id IN (SELECT user_id FROM paginated_users)
                ), socialized_rooms AS (
                    SELECT
                        c.chatroom_id,
                        c.chatroom_name,
                        uc.user_id
                    FROM chatrooms c
                    INNER JOIN user_chatrooms uc ON c.chatroom_id = uc.chatroom_id
                    WHERE uc.user_id IN (SELECT user_id FROM paginated_users)
                )
                SELECT
                    u.user_id,
                    u.login,
                    u.password,
                    cr.chatroom_id AS created_room_id,
                    cr.chatroom_name AS created_room_name,
                    sr.chatroom_id AS socialized_room_id,
                    sr.chatroom_name AS socialized_room_name
                FROM paginated_users u
                LEFT JOIN created_rooms cr ON u.user_id = cr.user_id
                LEFT JOIN socialized_rooms sr ON u.user_id = sr.user_id
                ORDER BY u.user_id;""";

        try (Connection con = datasource.getConnection()) {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, size);
            statement.setInt(2, page * size);

            try (ResultSet result = statement.executeQuery()) {
                return getStructuredUsersData(result);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Database error: ", e);
        }
    }
}
