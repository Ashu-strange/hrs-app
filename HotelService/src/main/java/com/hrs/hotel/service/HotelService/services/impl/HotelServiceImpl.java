package com.hrs.hotel.service.HotelService.services.impl;

import com.hrs.hotel.service.HotelService.entities.Hotel;
import com.hrs.hotel.service.HotelService.exceptions.ResourceNotFoundException;
import com.hrs.hotel.service.HotelService.repositories.HotelServiceRepository;
import com.hrs.hotel.service.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelServiceRepository hotelRepository;

    @Override
    public Hotel addHotel(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("Hotel not found with the given Id"));
        return hotel;
    }

    @Override
    public Hotel updateHotel(String hotelId, Hotel hotel) {
        Hotel updatedHotel = hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("Hotel not found with given Id"));

        updatedHotel.setName(hotel.getName());
        updatedHotel.setLocation(hotel.getLocation());
        updatedHotel.setAbout(hotel.getAbout());

        return hotelRepository.save(updatedHotel);
    }

    @Override
    public void deleteHotel(String hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("Hotel not found with given Id"));
        hotelRepository.delete(hotel);
    }
}
