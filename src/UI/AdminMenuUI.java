package UI;

import Entities.Hotel;
import Entities.Reservation;
import Repositories.InMemoryClientRepository;
import Services.AuthService;
import Entities.User;
import Services.HotelService;
import Services.ReservationService;
import java.util.UUID;
import java.util.Scanner;

public class AdminMenuUI {
    private final Scanner input;
    private final InMemoryClientRepository userRepo;
    private final AuthService authService;
    private final HotelService hotelService;
    private User logged = null;
    private ReservationService reservationService;

    public AdminMenuUI(InMemoryClientRepository userRepo, AuthService authService, Scanner input, User logged, HotelService hotelService, ReservationService reservationService) {
        this.input = input;
        this.userRepo = userRepo;
        this.authService = authService;
        this.logged = logged;
        this.hotelService = hotelService;
        this.reservationService = reservationService;
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
                        System.out.println("✅ Hotel created successfully, ID : " + newHotel.getHotelId());
                        break;

                    case 2:
                        Hotel[] hotelsToView = this.hotelService.GetAllHotels();
                        this.hotelService.displayHotels(hotelsToView);
                        break;

                    case 3:
                        System.out.print("Enter hotel id : ");
                        String hotelidString = input.nextLine();
                        try {
                            UUID hotelId = UUID.fromString(hotelidString);
                            Hotel hotelToUpdate = this.hotelService.CheckIfexistsHotel(hotelId);
                            if (hotelToUpdate == null) {
                                System.out.println("No hotel with this ID is registered. Try again!");
                                break;
                            }
                            System.out.print("Enter new hotel name:");
                            String NewName = input.nextLine();
                            System.out.print("Enter new hotel address:");
                            String NewAddress = input.nextLine();
                            System.out.print("Enter new available rooms:");
                            int NewRooms;
                            try {
                                NewRooms = Integer.parseInt(input.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("⚠️ Invalid number. Hotel not created.");
                                break;
                            }
                            System.out.println(this.hotelService.UpdateHotel(hotelToUpdate, NewName, NewAddress, NewRooms));

                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid hotel ID format.");
                        }
                        break;

                    case 4:
                        System.out.print("Enter hotel id : ");
                        String hotelToDeleteString = input.nextLine();
                        try {
                            UUID hoteToDeletelId = UUID.fromString(hotelToDeleteString);
                            System.out.println(this.hotelService.DeleteHotel(hoteToDeletelId));
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid hotel ID format.");
                        }
                        break;
                    case 5:
                        System.out.println("(You succesfully logged out.)");
                        this.logged = null;
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                continue;
            }

            System.out.println("You chose option: " + choice);
        }
    }
}
