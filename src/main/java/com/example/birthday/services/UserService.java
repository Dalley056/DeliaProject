package com.example.birthday.services;

import com.example.birthday.UserDto;

import java.util.Optional;

public interface UserService {
    void register(final UserDto user);

    Optional<UserDto> findUser(String username);

}
