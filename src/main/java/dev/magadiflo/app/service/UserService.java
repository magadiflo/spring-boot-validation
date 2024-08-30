package dev.magadiflo.app.service;

import dev.magadiflo.app.model.dto.UserRequest;
import dev.magadiflo.app.model.dto.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> findAllUsers();

    UserResponse findUserById(Long userId);

    UserResponse saveUser(UserRequest userRequest);

    UserResponse updateUser(Long userId, UserRequest userRequest);

    void deleteUserById(Long userId);
}
