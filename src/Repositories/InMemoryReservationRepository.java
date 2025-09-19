package Repositories;

import Entities.Reservation;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryReservationRepository implements ReservationRepository {
    private final Map<UUID, Reservation> reservations = new HashMap<>();

    @Override
    public void Save(Reservation reservation) {
        this.reservations.put(reservation.getId(), reservation);
    }

    @Override
    public Reservation FindById(UUID id) {
        return this.reservations.get(id);
    }

    @Override
    public Reservation[] FindAll() {
        return this.reservations.values().toArray(new Reservation[0]);
    }

    @Override
    public void Delete(UUID id) {
        this.reservations.remove(id);
    }

    @Override
    public Reservation[] FindByClientId(UUID clientId) {
        return this.reservations.values()
                .stream()
                .filter(r -> r.getClientId().equals(clientId))
                .toArray(Reservation[]::new);
    }

    @Override
    public Reservation[] FindByHotelId(UUID hotelId) {
        return this.reservations.values()
                .stream()
                .filter(r -> r.getHotelId().equals(hotelId))
                .toArray(Reservation[]::new);
    }
}
