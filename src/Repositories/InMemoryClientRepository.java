package Repositories;

import Entities.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class InMemoryClientRepository implements ClientRepository {
    private final Map<UUID, User> users = new HashMap<>();
    private User SearchedForUser;

    public InMemoryClientRepository() {
        // Create a hardcoded admin user
        User admin = new User("admin", "admin", "admin@gmail.com", true);
        this.users.put(admin.getId(), admin);
    }


    @Override
    public void Save(User user) {
        this.users.put(user.getId(), user);
    }

    @Override
    public User[] FindAll() {
        return this.users.values().toArray(new User[0]);
    }

    @Override
    public User FindByEmail(String email) {
        for (User user : users.values()) { // iterate all users
            if (user.getEmail().equalsIgnoreCase(email)) {
                this.SearchedForUser = user;
                return user; // found
            }
        }
        return null;
    }

    public void Delete(String email) {
    }
}

