package Entities;

import java.util.UUID;

public class User {
    private final UUID id;
    private String username;
    private String password;
    private String email;
    private boolean isAdmin;

    // Constructor with explicit UUID (useful when loading from DB or existing data)
    public User(String username, String password, String email, UUID id, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.id = id;
        this.isAdmin = isAdmin;
    }

    // Constructor for normal new users (UUID is auto-generated)
    public User(String username, String password, String email, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
        this.id = UUID.randomUUID();
    }

    // Getters
    public UUID getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public boolean isAdmin() {
        return this.isAdmin;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
