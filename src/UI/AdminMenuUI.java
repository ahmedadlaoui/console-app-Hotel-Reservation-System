package UI;

import Repositories.InMemoryClientRepository;
import Services.AuthService;
import Entities.User;

import java.util.Scanner;

public class AdminMenuUI {
    private final Scanner input;
    private final InMemoryClientRepository userRepo;
    private final AuthService authService;
    private User logged = null;

    public AdminMenuUI(InMemoryClientRepository userRepo, AuthService authService, Scanner input, User logged) {
        this.input = input;
        this.userRepo = userRepo;
        this.authService = authService;
        this.logged = logged;
    }

    public void start() {
        while (this.logged != null) {
            System.out.println("\n=== ADMIN MENU ===");
            System.out.println("1 - Create hotel");
            System.out.println("2 - List hotels");
            System.out.println("3 - Update hotel");
            System.out.println("4 - Delete hotel");
            System.out.println("5 - Logout");
            System.out.print("Please choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 5:
                        System.out.println("(You succesfully logged out.)");
                        this.logged = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                continue;
            }

            System.out.println("You chose option: " + choice);
        }
    }
}
