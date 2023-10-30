package com.hrs.user.service.UserService.services.impl;

import com.hrs.user.service.UserService.entities.Hotel;
import com.hrs.user.service.UserService.entities.Rating;
import com.hrs.user.service.UserService.entities.User;
import com.hrs.user.service.UserService.exceptions.ResourceNotFoundException;
import com.hrs.user.service.UserService.externalServices.HotelService;
import com.hrs.user.service.UserService.repositories.UserRepository;
import com.hrs.user.service.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        List<User> usersList = users.stream().map(user->{
            Rating[] ratings = restTemplate.getForObject("http://RATING-SERVICE/ratings/user/"+user.getUserId(), Rating[].class);
            List<Rating> userRatings = Arrays.stream(ratings).toList();

            List<Rating> ratingList = userRatings.stream().map(rating->{
                Hotel hotel = hotelService.getHotel(rating.getHotelId());
                rating.setHotel(hotel);
                return rating;
            }).collect(Collectors.toList());
            user.setRatings(ratingList);
            return user;
        }).collect(Collectors.toList());
        return usersList;
    }

    @Override
    public User getUser(String userId) {
        User user =  userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with the given id"));
        Rating[] ratings = restTemplate.getForObject("http://RATING-SERVICE/ratings/user/"+userId, Rating[].class);
        List<Rating> userRatings = Arrays.stream(ratings).toList();

        List<Rating> ratingList = userRatings.stream().map(rating->{
//            ResponseEntity<Hotel> hotel = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }

    @Override
    public User updateUser(String userId, User updatedUser) {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with the given id"));

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setAbout(updatedUser.getAbout());

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with the given id"));
        userRepository.delete(user);
    }
}
