package com.demo.multisport.mapper.impl;

import com.demo.multisport.dto.user.UserDto;
import com.demo.multisport.entities.user.User;
import com.demo.multisport.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;



@Component
@RequiredArgsConstructor
public class UserMapper {
    private final CommentMapper commentMapper;

    public UserDto userToUserDto(User user) {
        return UserDto
                .builder()
                .firstName(user.getFirstName())
                .secondName(user.getSecondName())
                .email(user.getEmail())
                .password(user.getPassword())
                .age(user.getAge())
                .role(user.getRole())
                .status(user.getStatus())
                .comments(user.getComments()
                        .stream()
                        .map(commentMapper::commentToCommentDto)
                        .collect(Collectors.toList()))
                .plan(user.getPlan())
                .build();

    }

    public User userDtoToUser(UserDto userDto) {
        return  User.builder()
                .firstName(userDto.getFirstName())
                .secondName(userDto.getSecondName())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .age(userDto.getAge())
                .status(userDto.getStatus())
                .role(userDto.getRole())
                .plan(userDto.getPlan())//this should be fixed later
                .comments(userDto.getComments() != null ?
                                                        userDto.getComments()
                                                        .stream()
                                                        .map(commentMapper::commentDtoToComment)
                                                        .collect(Collectors.toList())
                                                : null)
                .build();
    }
}
