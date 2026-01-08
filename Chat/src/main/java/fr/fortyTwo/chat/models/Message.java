package fr.fortyTwo.chat.models;

import java.util.Objects;
import java.time.LocalDateTime;

public class Message {
    private Long id;
    private String text;
    private LocalDateTime dateTime;

    private User author;
    private Chatroom room;

    // Constructor
    public Message(Long id, String text, LocalDateTime dateTime, User author, Chatroom room) {
        this.id = id;
        this.text = text;
        this.dateTime = dateTime;

        this.author = author;
        this.room = room;
    }

    // Getters and Setters
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getAuthor() {
        return this.author;
    }

    public void setRoom(Chatroom room) {
        this.room = room;
    }

    public Chatroom getRoom() {
        return this.room;
    }

    // overriding equals + hashCode + toString
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof Message))
            return false;

        Message message = (Message) obj;

        return Objects.equals(this.id, message.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "Message : {" + '\n' +
                "id=" + this.id + "," + '\n' +
                "author=" + this.author + "," + '\n' +
                "room=" + this.room + "," + '\n' +
                "text=" + this.text + "," + '\n' +
                "dateTime=" + this.dateTime + "," + '\n' +
                '}';
    }
}
