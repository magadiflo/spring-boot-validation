package dev.magadiflo.app.service.impl;

import dev.magadiflo.app.exception.UserNotFoundException;
import dev.magadiflo.app.model.dto.UserRequest;
import dev.magadiflo.app.model.dto.UserResponse;
import dev.magadiflo.app.model.entity.User;
import dev.magadiflo.app.repository.UserRepository;
import dev.magadiflo.app.service.UserService;
import dev.magadiflo.app.util.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponse> findAllUsers() {
        return this.userRepository.findAll().stream()
                .map(this.userMapper::toUserResponse)
                .toList();
    }

    @Override
    public UserResponse findUserById(Long userId) {
        return this.userRepository.findById(userId)
                .map(this.userMapper::toUserResponse)
                .orElseThrow(() -> new UserNotFoundException("No existe el usuario buscado con id " + userId));
    }

    @Override
    @Transactional
    public UserResponse saveUser(UserRequest userRequest) {
        User userDB = this.userRepository.save(this.userMapper.toUser(userRequest));
        return this.userMapper.toUserResponse(userDB);
    }

    @Override
    @Transactional
    public UserResponse updateUser(Long userId, UserRequest userRequest) {
        return this.userRepository.findById(userId)
                .map(userDB -> {
                    User user = this.userMapper.toUser(userRequest);
                    user.setId(userId);
                    return user;
                })
                .map(this.userRepository::save)
                .map(this.userMapper::toUserResponse)
                .orElseThrow(() -> new UserNotFoundException("No existe el usuario para actualizar con id " + userId));
    }

    @Override
    @Transactional
    public void deleteUserById(Long userId) {
        User userDB = this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No existe el usuario para eliminar con id " + userId));
        this.userRepository.deleteById(userId);
    }
}
