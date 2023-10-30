package com.hrs.hotel.service.HotelService.services;

import com.hrs.hotel.service.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {

    // Add Hotel
    Hotel addHotel(Hotel hotel);

    // Get all
    List<Hotel> getHotels();

    // Get single Hotel
    Hotel getHotel(String hotelId);

    // Edit Hotel
    Hotel updateHotel(String hotelId, Hotel hotel);

    // Delete Hotel
    void deleteHotel(String hotelId);
}
