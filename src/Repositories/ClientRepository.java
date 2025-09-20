package Repositories;
import Entities.User;

import java.util.UUID;

public interface ClientRepository {
public void Save(User user);
public void Delete(String email);
public User[] FindAll();
public User FindByEmail(String email);
public User FindById(UUID id);

}
