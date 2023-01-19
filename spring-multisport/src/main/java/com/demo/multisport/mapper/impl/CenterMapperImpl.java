package com.demo.multisport.mapper.impl;

import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.entities.center.RelaxCenter;
import com.demo.multisport.entities.center.SportCenter;
import com.demo.multisport.mapper.CenterMapper;
import org.springframework.stereotype.Component;

//this is for the part of the app, where only users has access
@Component
public class CenterMapperImpl implements CenterMapper {
    @Override
    public SportCenter centerDtoToSportCenter(CenterDto centerDto) {
        SportCenter
                .builder()
                .name(centerDto.getName())
                .address(centerDto.getAddress())
                .description(centerDto.getDescription())
                .pictures(centerDto.getPictures())
                //plan -> user part no plan at all, admin part whole plan
                //comments -> user part comment content and user name, admin part comment content, user email
                //rating -> user part rating, admin part rating
                .build();


        return null;
    }

    @Override
    public RelaxCenter centerDtoToRelaxCenter(CenterDto centerDto) {
        return null;
    }

    @Override
    public CenterDto sportCenterToCenterDto(SportCenter sportCenter) {
        return null;
    }

    @Override
    public CenterDto relaxCenterToCenterDto(RelaxCenter relaxCenter) {
        return null;
    }
}
