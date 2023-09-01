package com.example.birthday.services;

import com.example.birthday.UserDto;
import com.example.birthday.repository.UserEntity;
import com.example.birthday.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserDto user) {


        //Let's check if user already registered with us  // Password1! {null}jifrjgoidrd.vkrwjgojrope
//        if(checkIfUserExist(user.getEmail())){
//            throw new UserAlreadyExistException();
//        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userEntity);
    }


    @Override
    public Optional<UserDto> findUser(String username) {
        return Optional.empty();
    }
}
