package com.hrs.hotel.service.HotelService.repositories;

import com.hrs.hotel.service.HotelService.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelServiceRepository extends JpaRepository<Hotel, String> {
}
