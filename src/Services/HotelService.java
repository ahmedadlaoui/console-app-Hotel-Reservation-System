package Services;
import Entities.Hotel;
import Repositories.HotelRepository;
import Repositories.ClientRepository;
import Repositories.InMemoryHotelRepository;

public class HotelService {
    private final HotelRepository hotelRepository;

    public HotelService() {
        this.hotelRepository = new InMemoryHotelRepository();
    }

    public void AddHotel(Hotel hotel) {
        this.hotelRepository.Save(hotel); // delegate saving to repo
    }

    public Hotel[] GetAllHotels() {
        return this.hotelRepository.FindAll();
    }
}

