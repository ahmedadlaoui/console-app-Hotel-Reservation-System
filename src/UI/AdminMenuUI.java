package UI;

import Entities.Hotel;
import Repositories.InMemoryClientRepository;
import Services.AuthService;
import Entities.User;
import Services.HotelService;

import java.util.Scanner;

public class AdminMenuUI {
    private final Scanner input;
    private final InMemoryClientRepository userRepo;
    private final AuthService authService;
    private User logged = null;
    private final HotelService hotelService;

    public AdminMenuUI(InMemoryClientRepository userRepo, AuthService authService, Scanner input, User logged, HotelService hotelService) {
        this.input = input;
        this.userRepo = userRepo;
        this.authService = authService;
        this.logged = logged;
        this.hotelService = hotelService;
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
                    case 1:
                        System.out.print("Enter hotel name:");
                        String hotelName = input.nextLine();

                        System.out.print("Enter hotel address:");
                        String hotelAddress = input.nextLine();

                        System.out.print("Enter available rooms:");
                        int availableRooms;
                        try {
                            availableRooms = Integer.parseInt(input.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("⚠️ Invalid number. Hotel not created.");
                            break;
                        }
                        Hotel newHotel = new Hotel(hotelName, hotelAddress, availableRooms, 0.0);
                        hotelService.AddHotel(newHotel);
                        System.out.println("✅ Hotel created successfully: " + newHotel.getHotelId());
                        break;
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
