package Repositories;

import Entities.Hotel;

import java.util.UUID;

public interface HotelRepository {
    public void Save(Hotel hotel);

    public Hotel[] FindAll();

    public Hotel FindById(UUID id);

    public void DeleteHotel(UUID id);
//    public Hotel findById(String id);
}
