package Repositories;
import Entities.User;

public interface ClientRepository {
public void Save(User user);
public void Delete(String email);
public User[] FindAll();
public User FindByEmail(String email);
//public User FindByEmail(String email);
//public User FindByUsername(String username);
}
