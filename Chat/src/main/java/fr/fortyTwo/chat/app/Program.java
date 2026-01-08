package fr.fortyTwo.chat.app;

import fr.fortyTwo.chat.models.Message;
import fr.fortyTwo.chat.repositories.DatabaseConfig;
import fr.fortyTwo.chat.repositories.MessagesRepository;
import fr.fortyTwo.chat.repositories.MessagesRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.util.Optional;
import java.util.Scanner;

public class Program {
    
    public static void main(String[] args) {
        // Create DataSource using HikariCP
        DataSource dataSource = DatabaseConfig.createDataSource();
        
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
        } else {
            System.out.println("Message with ID " + messageId + " not found.");
        }
        
        scanner.close();
    }
}
