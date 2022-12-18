package com.jk.notes.payload.mapper;

import com.jk.notes.model.user.User;
import com.jk.notes.payload.response.UserDto;

public class UserMapper {

    public UserDto convertToDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .name(user.getName())
                .lastName(user.getLastName())
                .build();
    }
}
