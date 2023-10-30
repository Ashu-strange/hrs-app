package com.hrs.hotel.service.HotelService.controllers;

import com.hrs.hotel.service.HotelService.entities.Hotel;
import com.hrs.hotel.service.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel){
        Hotel newHotel = hotelService.addHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(newHotel);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getHotels(){
        List<Hotel> hotels = hotelService.getHotels();
        return ResponseEntity.ok(hotels);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId){
        Hotel hotel = hotelService.getHotel(hotelId);
        return ResponseEntity.ok(hotel);
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable String hotelId, @RequestBody Hotel hotel){
        Hotel updatedHotel = hotelService.updateHotel(hotelId, hotel);
        return ResponseEntity.ok(updatedHotel);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<String> deleteHotel(@PathVariable String hotelId){
        hotelService.deleteHotel(hotelId);
        return ResponseEntity.ok("Hotel Deleted Successfully");
    }
}
