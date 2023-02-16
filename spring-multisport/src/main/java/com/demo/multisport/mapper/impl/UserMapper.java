package com.demo.multisport.mapper.impl;

import com.demo.multisport.dao.PlanRepository;
import com.demo.multisport.dto.PlanDto;
import com.demo.multisport.dto.user.UserDto;
import com.demo.multisport.entities.Plan;
import com.demo.multisport.entities.center.Center;
import com.demo.multisport.entities.user.User;
import com.demo.multisport.exceptions.PlanMapperException;
import com.demo.multisport.exceptions.plan.NoSuchPlanException;
import com.demo.multisport.mapper.CommentMapper;
import com.demo.multisport.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.stream.Collectors;



@Component
@RequiredArgsConstructor
public class UserMapper {
    private final CommentMapper commentMapper;
    private final PlanRepository planRepository;


    public UserDto userToUserDto(User user) {
        Plan planUser = user.getPlan();
        try {
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
                    .plan(PlanDto
                            .builder()
                            .name(planUser.getName())
                            .price(planUser.getPrice())
                            .imageBase64(FileUtil.convertFileToBase64(planUser.getPathFile()))
                            .centersAddresses(planUser.getCenters()
                                    .stream().map(Center::getAddress)
                                    .collect(Collectors.toSet()))
                            .build())
                    .build();
        } catch (IOException e) {
            throw new PlanMapperException("Cannot map plan in UserMapper");
        }

    }

    public User userDtoToUser(UserDto userDto) {
        return  User
                .builder()
                .firstName(userDto.getFirstName())
                .secondName(userDto.getSecondName())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .age(userDto.getAge())
                .status(userDto.getStatus())
                .role(userDto.getRole())
                .plan(userDto.getPlan() != null ? planRepository.getPlanByName(userDto.getPlan().getName())
                        .orElseThrow(() -> new NoSuchPlanException("Cannot map plan, no such plan name"))
                        : null)
                .comments(userDto.getComments() != null ?
                                                        userDto.getComments()
                                                        .stream()
                                                        .map(commentMapper::commentDtoToComment)
                                                        .collect(Collectors.toList())
                                                : null)
                .build();
    }
}
