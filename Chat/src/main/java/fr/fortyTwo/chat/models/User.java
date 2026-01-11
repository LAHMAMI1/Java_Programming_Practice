package fr.fortyTwo.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private Long id;
    private String login;
    private String password;

    private List<Chatroom> createdRooms;
    private List<Chatroom> socializedRooms;

    // Constructor
    public User(long id, String login, String password, List<Chatroom> createdRooms, List<Chatroom> socializedRooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.socializedRooms = socializedRooms;
    };

    // Getters and Setters
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return this.login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setCreatedRooms(List<Chatroom> createdRooms) {
        this.createdRooms = createdRooms;
    }

    public List<Chatroom> getCreatedRooms() {
        return this.createdRooms;
    }

    public void setSocializedRooms(List<Chatroom> socializedRooms) {
        this.socializedRooms = socializedRooms;
    }

    public List<Chatroom> getSocializedRooms() {
        return this.socializedRooms;
    }

    // overriding equals + hashCode + toString
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof User))
            return false;

        User user = (User) obj;

        return Objects.equals(this.id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "User : {" + '\n' +
                "id='" + this.id + "," + '\n' +
                "login=" + this.login + "," + '\n' +
                "password='" + this.password + "," + '\n' +
                "createdRooms='" + this.createdRooms + "," + '\n' +
                "SocializedRooms='" + this.socializedRooms + "," + '\n' +
                '}'+ '\n';
    }
};
