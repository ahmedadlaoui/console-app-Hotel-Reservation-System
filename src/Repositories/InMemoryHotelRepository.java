package Repositories;
import Repositories.HotelRepository;

import Entities.Hotel;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryHotelRepository implements HotelRepository {
    private final Map<UUID, Hotel> hotels = new HashMap<>();

    public InMemoryHotelRepository() {
        // Hardcoded hotel for testing
        Hotel testHotel = new Hotel(
                "Test Hotel",
                "123 Main Street",
                10,
                0.00
        );
        this.hotels.put(testHotel.getHotelId(), testHotel);
    }

    @Override
    public void Save(Hotel hotel) {
        this.hotels.put(hotel.getHotelId(),hotel);
    }

    @Override
    public Hotel[] FindAll() {
        return this.hotels.values().toArray(new Hotel[0]);
    }

    @Override
    public Hotel FindById(UUID id) {
        return this.hotels.get(id);
    }

    @Override
    public void DeleteHotel(UUID id){
        this.hotels.remove(id);
    }
}
