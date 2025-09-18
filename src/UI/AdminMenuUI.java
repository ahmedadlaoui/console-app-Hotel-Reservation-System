package UI;

import Repositories.InMemoryClientRepository;
import Services.AuthService;

import java.util.Scanner;

public class AdminMenuUI {
    private final Scanner input;
    private final InMemoryClientRepository userRepo;
    private final AuthService authService;

    public AdminMenuUI(InMemoryClientRepository userRepo, AuthService authService, Scanner input) {
        this.input = input;
        this.userRepo = userRepo;
        this.authService = authService;
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== ADMIN MENU ===");
            System.out.println("1 - Create hotel");
            System.out.println("2 - List hotels");
            System.out.println("3 - Update hotel");
            System.out.println("4 - Delete hotel");
            System.out.println("5 - Logout");
            System.out.println("6 - Exit");
            System.out.print("Please choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 6:
                        running = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                continue;
            }

            System.out.println("You chose option: " + choice);
            System.out.println("(Logic not implemented yet)");
        }
    }
}
