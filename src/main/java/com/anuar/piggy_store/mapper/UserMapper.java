package com.anuar.piggy_store.mapper;

import org.springframework.stereotype.Component;

import com.anuar.piggy_store.domain.User;
import com.anuar.piggy_store.dto.request.UserDto;

@Component
public class UserMapper {

    public static User fromUserDto(UserDto userDto) {
        return new User(
        null,
        userDto.email(),
        userDto.password(), 
        true
        );
    }
}
