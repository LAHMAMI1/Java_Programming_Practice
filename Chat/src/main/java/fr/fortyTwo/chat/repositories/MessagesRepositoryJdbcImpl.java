package fr.fortyTwo.chat.repositories;

import java.sql.*;
import java.util.Optional;
import fr.fortyTwo.chat.models.*;
import fr.fortyTwo.chat.exceptions.NotSavedSubEntityException;

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
            throw new RuntimeException("Database error: ", e);
        }
    }

    private boolean authorExist(User author) {

        if (author == null || author.getId() == null)
            throw new NotSavedSubEntityException("ERROR:\nThe author is null");

        String sql = "SELECT EXISTS(SELECT 1 FROM users WHERE user_id = ?)";

        try (Connection con = datasource.getConnection()) {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setLong(1, author.getId());

            try (ResultSet result = statement.executeQuery()) {
                if (result.next())
                    return result.getBoolean(1);
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Database error: ", e);
        }
    }

    private boolean roomExist(Chatroom room) {

        if (room == null || room.getId() == null)
            throw new NotSavedSubEntityException("ERROR:\nThe room is null");

        String sql = "SELECT EXISTS(SELECT 1 FROM chatrooms WHERE chatroom_id = ?)";

        try (Connection con = datasource.getConnection()) {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setLong(1, room.getId());

            try (ResultSet result = statement.executeQuery()) {
                if (result.next())
                    return result.getBoolean(1);
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Database error: ", e);
        }
    }

    @Override
    public void save(Message message) {
        if (!authorExist(message.getAuthor()))
            throw new NotSavedSubEntityException(
                    "Author with ID " + message.getAuthor().getId() + " does not exist in database");

        if (!roomExist(message.getRoom()))
            throw new NotSavedSubEntityException(
                    "Chatroom with ID " + message.getRoom().getId() + " does not exist in database");

        String sql = "INSERT INTO messages (message_text, message_date, author_id, room_id) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection con = datasource.getConnection()) {
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, message.getText());
            statement.setTimestamp(2, java.sql.Timestamp.valueOf(message.getDateTime()));
            statement.setLong(3, message.getAuthor().getId());
            statement.setLong(4, message.getRoom().getId());

            // Execute the query
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0)
                throw new SQLException("Insert failed, no rows affected.");
            // get the generated id
            ResultSet generatedKey = statement.getGeneratedKeys();
            if (generatedKey.next())
                message.setId(generatedKey.getLong(1));

        } catch (SQLException e) {
            throw new RuntimeException("Database error: ", e);
        }
    }

    @Override
    public void update(Message message) {
        if (message.getId() == null)
            throw new NotSavedSubEntityException("The message ID cannot be null");
        if (message.getAuthor() == null || message.getAuthor().getId() == null)
            throw new NotSavedSubEntityException("Message author must have a valid ID");
        if (message.getRoom() == null || message.getRoom().getId() == null)
            throw new NotSavedSubEntityException("Message room must have a valid ID");
        if (!authorExist(message.getAuthor()))
            throw new NotSavedSubEntityException(
                    "Author with ID " + message.getAuthor().getId() + " does not exist in database");
        if (!roomExist(message.getRoom()))
            throw new NotSavedSubEntityException(
                    "Chatroom with ID " + message.getRoom().getId() + " does not exist in database");

        String sql = "UPDATE messages " +
                "SET message_text = ?, " +
                "message_date = ?, " +
                "author_id = ?, " +
                "room_id = ? " +
                "WHERE message_id = ?";

        try (Connection con = datasource.getConnection()) {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, message.getText());
            
            if (message.getDateTime() == null)
                statement.setNull(2, java.sql.Types.TIMESTAMP);
            else
                statement.setTimestamp(2, java.sql.Timestamp.valueOf(message.getDateTime()));
            
            statement.setLong(3, message.getAuthor().getId());
            statement.setLong(4, message.getRoom().getId());
            statement.setLong(5, message.getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0)
                throw new SQLException("Update failed, no rows affected.");

        } catch (SQLException e) {
            throw new RuntimeException("Database error: ", e);
        }
    }
}
