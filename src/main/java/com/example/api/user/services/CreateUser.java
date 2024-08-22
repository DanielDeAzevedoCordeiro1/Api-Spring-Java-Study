package com.example.api.user.services;

import com.example.api.user.User;
import com.example.api.userDto.UserDto;
import com.example.api.userRepository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CreateUser {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public CreateUser(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    public User execute(UserDto createUserDto) throws Exception {
        if (createUserDto.userName().toString().trim().isEmpty() | createUserDto.password().toString().trim().isEmpty()) {
            throw new BadRequestException("Vazio!");
        }

        if (createUserDto.userName().contains(" ") || createUserDto.password().contains(" ")) {
            throw new BadRequestException("Nao pode haver espacos em branco entre o nome e a senha");
        }

        var isConflited = userRepository.findByUserName(createUserDto.userName());

        if (isConflited.isPresent()) throw new Exception("Usuario Repetido");

        User newUser = new User();
        newUser.setUserName(createUserDto.userName());
        newUser.setPassword(passwordEncoder.encode(createUserDto.password()));

        return userRepository.save(newUser);
    }


}
