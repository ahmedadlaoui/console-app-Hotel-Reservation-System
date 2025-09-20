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
        return this.id;
    }

    public Instant getTimestamp() {
        return this.timestamp;
    }

    public UUID getHotelId() {
        return this.hotelId;
    }

    public UUID getClientId() {
        return this.clientId;
    }

    public int getNights() {
        return this.nights;
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
