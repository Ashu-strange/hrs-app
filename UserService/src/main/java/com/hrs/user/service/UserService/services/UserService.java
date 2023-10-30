package com.hrs.user.service.UserService.services;

import com.hrs.user.service.UserService.entities.User;

import java.util.List;

public interface UserService {
    // User Operations

    // Add user
    User saveUser(User user);

    // Get All users
    List<User> getUsers();

    // Get User
    User getUser(String userId);

    // Update User
    User updateUser(String userId, User updatedUser);

    // Delete User
    void deleteUser(String userId);
}
