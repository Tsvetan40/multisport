package com.demo.multisport.mapper;

import com.demo.multisport.dto.UserDto;
import com.demo.multisport.entities.user.User;
import lombok.NonNull;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    UserDto userToUserDto(User user);
    User userDtoToUser( UserDto userDto);
}
