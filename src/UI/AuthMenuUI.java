package UI;

import java.util.Scanner;
import Entities.User;
import Repositories.InMemoryClientRepository;
import Services.AuthService;
import Services.HotelService;

public class AuthMenuUI {
    private final Scanner input;
    private final InMemoryClientRepository userRepo;
    private final AuthService authService;
    private final HotelService hotelService;

    public AuthMenuUI() {
        this.input = new Scanner(System.in);
        this.userRepo = new InMemoryClientRepository(); // single repository
        this.authService = new AuthService(userRepo);
        this.hotelService = new HotelService();
    }

    public void start() throws Exception {
        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1 - Sign Up");
            System.out.println("2 - Sign In");
            System.out.println("3 - Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter 1, 2, or 3.");
                continue;
            }

            switch (choice) {
                case 1: // SIGN UP
                    handleSignUp();
                    break;

                case 2: // SIGN IN
                    handleSignIn();
                    break;

                case 3: // EXIT
                    System.out.println("Goodbye!");
                    input.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void handleSignUp() {
        System.out.println("\n=== SIGN UP ===");
        System.out.print("Enter E-mail: ");
        String email = input.nextLine();

        System.out.print("Enter your full name: ");
        String username = input.nextLine();

        System.out.print("Choose a password: ");
        String password = input.nextLine();

        int success = authService.SignUp(username, password, email);
        if (success == 0) {
            System.out.println("✅ Registration successful. Returning to main menu.");
        }if(success == 1) {
            System.out.println("⚠️ That email is already registered. Try again.");
        }if(success == 2) {
            System.out.println("⚠️ Password must be at least 6 characters. Try again.");
        }
    }

    private void handleSignIn() throws Exception {
        System.out.println("\n=== SIGN IN ===");
        System.out.print("Enter E-mail: ");
        String email = input.nextLine();

        System.out.print("Enter Password: ");
        String password = input.nextLine();

        User logged = authService.SignIn(email, password);
        if (logged != null && !logged.isAdmin()) {
            System.out.println("✅ Welcome, " + logged.getUsername() + "!");
            ClientMenuUI clientmenuUI = new ClientMenuUI(this.userRepo,this.authService,this.input,logged);
            clientmenuUI.start();
        }else if(logged != null && logged.isAdmin()){
            System.out.println("✅ Welcome, " + logged.getUsername() + "!");
            AdminMenuUI adminmenuUI = new AdminMenuUI(this.userRepo,this.authService,this.input,logged,this.hotelService);
            adminmenuUI.start();
        }else {
            System.out.println("⚠️ Wrong email or password.");
        }
    }
}
