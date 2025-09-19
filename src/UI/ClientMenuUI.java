package UI;

import Entities.Hotel;
import Repositories.InMemoryClientRepository;
import Services.AuthService;
import Entities.User;
import Services.HotelService;
import Services.ReservationService;

import java.util.Scanner;

public class ClientMenuUI {
    private final Scanner input;
    private final InMemoryClientRepository userRepo;
    private final AuthService authService;
    private final HotelService hotelService;
    private final ReservationService reservationService;
    private User logged = null;

    public ClientMenuUI(InMemoryClientRepository userRepo, AuthService authService, Scanner input, User logged, HotelService hotelService, ReservationService reservationService) {
        this.input = input;
        this.userRepo = userRepo;
        this.authService = authService;
        this.logged = logged;
        this.hotelService = hotelService;
        this.reservationService = reservationService;
    }

    public void start() {

        while (this.logged != null) {
            System.out.println("\n=== CLIENT MENU ===");
            System.out.println("1 - List hotels");
            System.out.println("2 - List reservations");
            System.out.println("3 - Reserve a room");
            System.out.println("4 - Cancel a reservation");
            System.out.println("5 - Reservation history");
            System.out.println("6 - Update profile");
            System.out.println("7 - Change password");
            System.out.println("8 - Logout");
            System.out.print("Please choose an option: ");
            int choice;
            try {
                choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        Hotel[] hotelsToView = this.hotelService.GetAllHotels();
                        this.hotelService.displayHotels(hotelsToView);
                        break;
                    case 2:
                        this.reservationService.DisplayReservationsByClient(this.logged.getId());
                        break;
                    case 3:

                    case 7:
                        System.out.println("(You successfully logged out.)");
                        this.logged = null;
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
                continue;
            }

            System.out.println("You chose option: " + choice);
        }
    }
}
