package Services;

import Entities.Reservation;
import Repositories.InMemoryReservationRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReservationService {
    private final InMemoryReservationRepository reservationRepo;

    public ReservationService() {
        this.reservationRepo = new InMemoryReservationRepository();
    }

    // Make a new reservation
    public Reservation MakeReservation(UUID clientId, UUID hotelId, int nights) {
        Reservation reservation = new Reservation(// reservation ID
                clientId,
                hotelId,
                nights
        );
        reservationRepo.Save(reservation);
        return reservation;
    }

    // Cancel a reservation
    public boolean CancelReservation(UUID reservationId, UUID clientId) {
        Reservation reservation = reservationRepo.FindById(reservationId);
        if (reservation != null && reservation.getClientId().equals(clientId)) {
            reservationRepo.Delete(reservationId);
            return true;
        }
        return false;
    }

    // Get all reservations for a specific client
    public Reservation[] GetReservationsByClient(UUID clientId) {
        return reservationRepo.FindByClientId(clientId);
    }

    // Get a single reservation by ID
    public Reservation FindReservationById(UUID reservationId) {
        return reservationRepo.FindById(reservationId);
    }

    public void DisplayReservationsByClient(UUID clientId) {
        Reservation[] reservations = GetReservationsByClient(clientId);

        if (reservations.length == 0) {
            System.out.println("No reservations found for this client.");
            return;
        }

        System.out.println("\n--- Your Reservations ---");
        System.out.printf("%-20s | %-20s | %-6s | %-20s%n", "Reservation ID", "Hotel ID", "Nights", "Date/Time");
        System.out.println("---------------------------------------------------------------------");

        for (Reservation r : reservations) {
            System.out.printf("%-20s | %-20s | %-6d | %-20s%n",
                    r.getId(),
                    r.getHotelId(),
                    r.getNights(),
                    r.getTimestamp()
            );
        }
    }
}
