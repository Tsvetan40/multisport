package com.demo.multisport.services.page;


import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.entities.center.RelaxCenter;
import com.demo.multisport.entities.center.SportCenter;

public interface PageService {
    CenterDto getCenterDtoFromSportCenter(SportCenter sportCenter);
    CenterDto getCenterDtoFromRelaxCenter(RelaxCenter relaxCenter);
}
