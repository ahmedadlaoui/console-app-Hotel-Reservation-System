package UI;

import Repositories.InMemoryClientRepository;
import Services.AuthService;

import java.util.Scanner;

public class ClientMenuUI {
    private final Scanner input;
    private final InMemoryClientRepository userRepo;
    private final AuthService authService;

    public ClientMenuUI(InMemoryClientRepository userRepo, AuthService authService, Scanner input) {
        this.input = input;
        this.userRepo = userRepo;
        this.authService = authService;
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== CLIENT MENU ===");
            System.out.println("2 - List hotels");
            System.out.println("3 - Reserve a room");
            System.out.println("4 - Cancel a reservation");
            System.out.println("5 - Reservation history");
            System.out.println("6 - Update profile");
            System.out.println("7 - Change password");
            System.out.println("8 - Logout");
            System.out.println("9 - Exit");
            System.out.print("Please choose an option: ");
            // Simple input reading (no logic yet)
            int choice;
            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
                continue;
            }

            System.out.println("You chose option: " + choice);
            System.out.println("(Logic not implemented yet)");
        }
    }
}
