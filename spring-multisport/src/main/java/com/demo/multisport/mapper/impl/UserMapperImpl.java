package com.demo.multisport.mapper.impl;

import com.demo.multisport.dao.UserRepository;
import com.demo.multisport.dto.user.UserDto;
import com.demo.multisport.entities.user.User;
import com.demo.multisport.exceptions.user.UserNotFoundException;
import com.demo.multisport.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import java.util.stream.Collectors;

@Component
@Qualifier("myUserMapper")
@RequiredArgsConstructor
public class UserMapperImpl {
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;

    public UserDto userToUserDto(User user) {
        return UserDto
                .builder()
                .firstName(user.getFirstName())
                .secondName(user.getSecondName())
                .email(user.getEmail())
                .password(user.getPassword())
                .age(user.getAge())
                .comments(user.getComments()
                        .stream()
                        .map(commentMapper::commentToCommentDto)
                        .collect(Collectors.toList()))
                .plan(user.getPlan())
                .build();

    }

    public User userDtoToUser(UserDto userDto) {
//        Optional<User> user =  userRepository.findUserByEmail(userDto.getEmail());
//
//        return user.isEmpty() ? null : user.get();
        return userRepository.findUserByEmail(userDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Mapping user unsuccessfully"));
    }
}
