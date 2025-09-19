package Repositories;
import Repositories.HotelRepository;

import Entities.Hotel;
import Entities.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryHotelRepository implements HotelRepository {
    private final Map<UUID, Hotel> hotels = new HashMap<>();

    @Override
    public void Save(Hotel hotel) {
        this.hotels.put(hotel.getHotelId(),hotel);
    }

    @Override
    public Hotel[] FindAll() {
        return this.hotels.values().toArray(new Hotel[0]);
    }
    // 06 50 50 34 83

    @Override
    public Hotel FindById(UUID id) {
        return this.hotels.get(id);
    }
}
