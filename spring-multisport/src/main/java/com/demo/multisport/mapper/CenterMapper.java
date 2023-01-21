package com.demo.multisport.mapper;

import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.entities.center.RelaxCenter;
import com.demo.multisport.entities.center.SportCenter;
import com.demo.multisport.entities.page.Rating;


public interface CenterMapper {
    SportCenter centerDtoToSportCenter(CenterDto centerDto);
    RelaxCenter centerDtoToRelaxCenter(CenterDto centerDto);

    CenterDto sportCenterToCenterDto(SportCenter sportCenter);
    CenterDto relaxCenterToCenterDto(RelaxCenter relaxCenter);

    Double map(Rating rating);
}
