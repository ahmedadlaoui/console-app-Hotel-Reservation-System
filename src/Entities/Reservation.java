package Entities;

import java.time.Instant;
import java.util.UUID;

public class Reservation {
    private UUID id;
    private Instant timestamp;   // when the reservation was made
    private UUID hotelId;        // reference to Hotel
    private UUID clientId;       // reference to User (client)
    private int nights;          // number of nights

    public Reservation(UUID hotelId, UUID clientId, int nights) {
        this.id = UUID.randomUUID();
        this.timestamp = Instant.now();
        this.hotelId = hotelId;
        this.clientId = clientId;
        this.nights = nights;
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public UUID getHotelId() {
        return hotelId;
    }

    public UUID getClientId() {
        return clientId;
    }

    public int getNights() {
        return nights;
    }

    // Setters
    public void setNights(int nights) {
        if (nights > 0) {
            this.nights = nights;
        }
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", hotelId=" + hotelId +
                ", clientId=" + clientId +
                ", nights=" + nights +
                '}';
    }
}
