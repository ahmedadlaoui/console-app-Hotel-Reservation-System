package Services;
import Entities.Hotel;
import Repositories.HotelRepository;
import Repositories.ClientRepository;
import Repositories.InMemoryHotelRepository;
import java.util.UUID;

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
    public void displayHotels(Hotel[] hotels) {
        if (hotels == null || hotels.length == 0) {
            System.out.println("⚠️ No hotels available.");
        }

        System.out.println("\n--- Hotels ---");
        System.out.printf("%-15s | %-20s | %-6s | %-6s%n", "Name", "Address", "Rooms", "Rating");
        System.out.println("---------------------------------------------------------------");

        for (Hotel h : hotels) {
            System.out.printf("%-15s | %-20s | %-6d | %-6.2f%n",
                    h.getName(),
                    h.getAddress(),
                    h.getAvailableRooms(),
                    h.getRating());
        }
    }

    public Hotel CheckIfexistsHotel(UUID hotelid) {
        return this.hotelRepository.FindById(hotelid);
    }

    public String UpdateHotel(Hotel hotelToUpdate, String name, String address, int rooms) {
            hotelToUpdate.setName(name);
            hotelToUpdate.setAddress(address);
            hotelToUpdate.setAvailableRooms(rooms);
            return "Hotel updated successfully!";
    }
}

