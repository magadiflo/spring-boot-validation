package dev.magadiflo.app.util;

import dev.magadiflo.app.model.dto.UserRequest;
import dev.magadiflo.app.model.dto.UserResponse;
import dev.magadiflo.app.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthdate(user.getBirthdate())
                .dni(user.getDni())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .age(user.getAge())
                .salary(user.getSalary())
                .active(user.getActive())
                .build();
    }

    public User toUser(UserRequest userRequest) {
        return User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .birthdate(userRequest.getBirthdate())
                .dni(userRequest.getDni())
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .age(userRequest.getAge())
                .salary(userRequest.getSalary())
                .active(userRequest.getActive())
                .build();
    }
}
