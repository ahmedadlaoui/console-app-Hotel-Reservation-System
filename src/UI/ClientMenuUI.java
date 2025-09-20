package UI;

import Entities.Hotel;
import Entities.Reservation;
import Repositories.InMemoryClientRepository;
import Repositories.InMemoryHotelRepository;
import Repositories.InMemoryReservationRepository;
import Services.AuthService;
import Entities.User;
import Services.HotelService;
import Services.ReservationService;

import java.util.Scanner;
import java.util.UUID;

public class ClientMenuUI {
    private final Scanner input;
    private final InMemoryClientRepository userRepo;
    private final AuthService authService;
    private final HotelService hotelService;
    private final ReservationService reservationService;
    private final InMemoryHotelRepository inMemoryHotelRepository;
    private final InMemoryReservationRepository inMemoryReservationRepository;
    private User logged = null;

    public ClientMenuUI(InMemoryClientRepository userRepo, AuthService authService, Scanner input, User logged, HotelService hotelService, ReservationService reservationService, InMemoryHotelRepository inMemoryHotelRepository, InMemoryReservationRepository inMemoryReservationRepository) {
        this.input = input;
        this.userRepo = userRepo;
        this.authService = authService;
        this.logged = logged;
        this.hotelService = hotelService;
        this.reservationService = reservationService;
        this.inMemoryHotelRepository = inMemoryHotelRepository;
        this.inMemoryReservationRepository = inMemoryReservationRepository;
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
                        System.out.print("Please enter the hotel ID :");
                        String hotelidString = input.nextLine();
                        System.out.print("How many nights do you want to reserve :");
                        String NumberOfNights = input.nextLine();
                        try {
                            UUID hotelId = UUID.fromString(hotelidString);
                            int numberOfNights = Integer.parseInt(NumberOfNights);
//                            System.out.println(hotelId + " " + numberOfNights);
                            Reservation ReservationMade = this.reservationService.MakeReservation(logged.getId(), hotelId, numberOfNights);
                            System.out.println("You successfully reserved a room. reservation ID : " + ReservationMade);

                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid Hotel ID or number of Nights");
                        }
                        break;
                    case 4:
                        System.out.print("Please enter the Id of the reservation you want to cancel :");
                        String IdReservationToCancelString = input.nextLine();
                        try {
                            UUID IdReservationToCancel = UUID.fromString(IdReservationToCancelString);
                            boolean ResultOfCancelation = this.reservationService.CancelReservation( IdReservationToCancel, logged.getId());
                            if(ResultOfCancelation){
                                System.out.println("Reservation Cancelled successfully! ");
                            }else{
                                System.out.println("No reservation registered under this ID .");
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid Reservation ID");
                        }
                        break;
                    case 8:
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
