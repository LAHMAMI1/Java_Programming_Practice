package fr.fortyTwo.chat.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import fr.fortyTwo.chat.models.Chatroom;
import fr.fortyTwo.chat.models.Message;
import fr.fortyTwo.chat.models.User;

import javax.sql.DataSource;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private final DataSource datasource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.datasource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) {
        // SQL Query to get the message
        String sql = "SELECT message_id, message_text, message_date, user_id, login, password, chatroom_id, chatroom_name "
                +
                "FROM messages " +
                "INNER JOIN users ON messages.author_id = users.user_id " +
                "INNER JOIN chatrooms ON messages.room_id = chatrooms.chatroom_id " +
                "WHERE message_id = ?";

        // Try-with-resources ensures the connection and statement are closed
        // automatically
        try (Connection con = datasource.getConnection()) {
            // Prepare the sql statement
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setLong(1, id);

            // Get the data
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                // Build User object
                User author = new User(
                        result.getLong("user_id"),
                        result.getString("login"),
                        result.getString("password"),
                        null,
                        null);

                // Build Chatroom object
                Chatroom room = new Chatroom(
                        result.getLong("chatroom_id"),
                        result.getString("chatroom_name"),
                        null,
                        null);

                // Build Message object
                Message message = new Message(
                        result.getLong("message_id"),
                        result.getString("message_text"),
                        result.getTimestamp("message_date").toLocalDateTime(),
                        author,
                        room);

                return Optional.of(message);
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }
}
