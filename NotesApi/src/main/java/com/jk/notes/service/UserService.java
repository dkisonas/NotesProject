package com.jk.notes.service;


import com.jk.notes.model.user.User;
import com.jk.notes.payload.mapper.UserMapper;
import com.jk.notes.payload.response.UserDto;
import com.jk.notes.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper = new UserMapper();

    public UserDto getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return userMapper.convertToDto(user.get());
        }
        throw new UsernameNotFoundException("Username does not exist");
    }
}
