package Services;

import Repositories.InMemoryClientRepository;
import Entities.User;
import Utils.PasswordValidator;

import java.util.UUID;

public class AuthService {
    private final InMemoryClientRepository inMemoryClientRepository;
    private final PasswordValidator passwordValidator = new PasswordValidator();
    private User CurrentUser = null;

    public AuthService(InMemoryClientRepository inMemoryClientRepository) {
        this.inMemoryClientRepository = inMemoryClientRepository;
    }

    // Register a new user. Returns true if successful, false if email already exists
    public int SignUp(String username, String password, String email) {
        User existing = inMemoryClientRepository.FindByEmail(email);
        int ErrorCase = 0;
        if (existing != null) {
            ErrorCase = 1;
            return ErrorCase; // email already registered
        }
        if (!this.passwordValidator.validate(password)) {
            ErrorCase = 2;
            return ErrorCase;
        }
        ;
        User newUser = new User(username, password, email, false);
        inMemoryClientRepository.Save(newUser);
        return ErrorCase;
    }

    // Log in with email + password. Returns the User if ok, null otherwise
    public User SignIn(String email, String password) throws Exception {
        User found = inMemoryClientRepository.FindByEmail(email);
        this.CurrentUser = found;
        if (this.CurrentUser != null && this.CurrentUser.getPassword().equals(password)){
                return this.CurrentUser;
        }else{
            return null;
        }
    }

    public User UpdateProfile(UUID id, String NewEmail, String NewName) throws Exception {
        User NewUserCredentials = inMemoryClientRepository.FindById(id);
        NewUserCredentials.setEmail(NewEmail);
        NewUserCredentials.setUsername(NewName);
        this.inMemoryClientRepository.Save(NewUserCredentials);

        return NewUserCredentials;
    }
    public Boolean UpdatePassword(User user, String newPassword) {
        if (this.passwordValidator.validate(newPassword)) {
            user.setPassword(newPassword);
            this.inMemoryClientRepository.Save(user);
            return true;
        }else{
            return false;
        }
    }
}
