package Entities;

import java.util.UUID;

public class Hotel {
    private final UUID hotelId;
    private String name;
    private String address;
    private int availableRooms;
    private double rating;

    // Constructor
    public Hotel(String name, String address, int availableRooms, double rating) {
        this.hotelId = UUID.randomUUID(); // auto-generate unique ID
        this.name = name;
        this.address = address;
        this.availableRooms = availableRooms;
        this.rating = rating;
    }

    // Getters
    public UUID getHotelId() {
        return hotelId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public double getRating() {
        return rating;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAvailableRooms(int availableRooms) {
        if (availableRooms >= 0) {
            this.availableRooms = availableRooms;
        } else {
            throw new IllegalArgumentException("Available rooms cannot be negative");
        }
    }
    public void IncrementAvailableRooms(){
        this.availableRooms++;
    }
    public void DecrementAvailableRooms(){
        this.availableRooms--;
    }

    public void setRating(double rating) {
        if (rating >= 0 && rating <= 5) {
            this.rating = rating;
        } else {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId=" + hotelId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", availableRooms=" + availableRooms +
                ", rating=" + rating +
                '}';
    }
}
