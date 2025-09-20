package Services;

import Entities.Hotel;
import Entities.Reservation;
import Repositories.InMemoryHotelRepository;
import Repositories.InMemoryReservationRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ReservationService {
    private final InMemoryReservationRepository reservationRepo;
    private final InMemoryHotelRepository hotelRepo;

    public ReservationService(InMemoryHotelRepository hotelRepo,InMemoryReservationRepository reservationRepo) {
        this.reservationRepo = reservationRepo;
        this.hotelRepo = hotelRepo;
    }

    // Make a new reservation
    public Reservation MakeReservation(UUID clientId, UUID hotelId, int nights) {

        Hotel SelectedHotel = this.hotelRepo.FindById(hotelId);
        if (SelectedHotel == null) {
            System.out.println("Hotel Not Found");
            return null;
        }
        if (SelectedHotel.getAvailableRooms() == 0) {
            System.out.println("No Rooms Available in this Hotel");
            return null;
        }
        Reservation reservation = new Reservation(
                clientId,
                hotelId,
                nights
        );
        SelectedHotel.DecrementAvailableRooms();
        this.reservationRepo.Save(reservation);
        return reservation;
    }

    // Cancel a reservation
    public Reservation CancelReservation(UUID reservationId, UUID clientId,UUID hotelId) {

        Hotel selectedHotel = this.hotelRepo.FindById(hotelId);
        selectedHotel.IncrementAvailableRooms();

        this.reservationRepo.Delete(reservationId);
        return this.reservationRepo.FindById(reservationId);

//        if (reservation == null || !reservation.getClientId().equals(clientId)) {
//            return false;
//        }
//        Hotel selectedHotel = this.hotelRepo.FindById(hotelId);
//
//        if (selectedHotel != null) {
//            selectedHotel.IncrementAvailableRooms();
//        }
//        this.reservationRepo.Delete(reservationId);
//        return true;
    }


    // Get all reservations for a specific client
    public Reservation[] GetReservationsByClient(UUID clientId) {
        return this.reservationRepo.FindByClientId(clientId);
    }

    // Get a single reservation by ID
    public Reservation FindReservationById(UUID reservationId) {
        return this.reservationRepo.FindById(reservationId);
    }

    public void DisplayReservationsByClient(UUID clientId) {
        Reservation[] reservations = this.GetReservationsByClient(clientId);

        if ( reservations == null || reservations.length == 0 ) {
            System.out.println("No reservations found for this client.");
            return;
        }

        System.out.println("\n--- Your Reservations ---");
        System.out.printf("%-20s | %-20s | %-6s | %-20s%n", "Reservation ID", "Hotel ID", "Nights", "Date/Time");
        System.out.println("---------------------------------------------------------------------");

        for (Reservation r : reservations) {
            System.out.printf("%-40s | %-36s | %-6d | %-25s%n",
                    r.getId(),
                    r.getHotelId(),
                    r.getNights(),
                    r.getTimestamp()
            );

        }
    }
}
