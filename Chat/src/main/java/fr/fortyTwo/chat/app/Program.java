package fr.fortyTwo.chat.app;

import fr.fortyTwo.chat.models.*;
import fr.fortyTwo.chat.repositories.*;
import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.*;

public class Program {

    public static void main(String[] args) {
        // Create DataSource using HikariCP
        DataSource dataSource = DatabaseConfig.createDataSource();

        // TEST 1: Find message by ID
        {
            // Create repository
            MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);

            // Get user input
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a message ID");
            System.out.print("-> ");

            Long messageId = scanner.nextLong();

            // Find message by ID
            Optional<Message> messageOptional = messagesRepository.findById(messageId);

            // Display result
            if (messageOptional.isPresent()) {
                Message message = messageOptional.get();
                System.out.println(message);
            } else
                System.out.println("Message with ID " + messageId + " not found.");

            scanner.close();
        }

        // TEST 2: Save new message and get it's ID
        {
            User creator = new User(5L, "user", "user", new ArrayList(), new ArrayList());
            User author = creator;

            Chatroom chatroom = new Chatroom(5L, "room", creator, new ArrayList());

            Message message = new Message(null, "Hello!", LocalDateTime.now(), author,
                    chatroom);

            MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
            messagesRepository.save(message);

            System.out.println(message.getId());
        }

        // TEST 3: Update message
        {
            MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
            Optional<Message> messageOptional = messagesRepository.findById(1L);
            if (messageOptional.isPresent()) {
                Message message = messageOptional.get();
                message.setText("Bye");
                message.setDateTime(null);
                messagesRepository.update(message);
                System.out.println("Message updated");
            } else
                System.err.println("The message doesn't exist");
        }

        // TEST 4: Find All users with Pagination
        {
            UsersRepository repository = new UsersRepositoryJdbcImpl(dataSource);

            System.out.println("Page 0--Size 3");
            List<User> page0 = repository.findAll(0, 3);
            for (User user : page0) {
                System.out.println(user);
                System.out.println("Created rooms: " + user.getCreatedRooms().size());
                System.out.println("Socialized rooms: " + user.getSocializedRooms().size());
            }

            System.out.println("\nPage 1--Size 3");
            List<User> page1 = repository.findAll(1, 3);
            for (User user : page1) {
                System.out.println(user);
            }
        }
    }
}
