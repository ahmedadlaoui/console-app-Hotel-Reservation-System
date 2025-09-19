package Repositories;

import Entities.Hotel;

import java.util.UUID;

public interface HotelRepository {
    public void Save(Hotel hotel);
    public Hotel[] FindAll();
    public Hotel FindById(UUID id);
//    public void delete(String email);
//    public Hotel findById(String id);
}
