package Repositories;

import Entities.Hotel;

public interface HotelRepository {
    public void Save(Hotel hotel);
    public Hotel[] FindAll();
//    public void delete(String email);
//    public Hotel findById(String id);
}
