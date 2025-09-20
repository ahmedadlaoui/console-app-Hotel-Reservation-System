package Repositories;

import Entities.Reservation;

import java.util.List;
import java.util.UUID;

public interface ReservationRepository {

    void Save(Reservation reservation);

    Reservation FindById(UUID id);

    Reservation[] FindAll();

    void Delete(UUID id);

    Reservation[] FindByClientId(UUID clientId);

    Reservation[] FindByHotelId(UUID hotelId);

}
