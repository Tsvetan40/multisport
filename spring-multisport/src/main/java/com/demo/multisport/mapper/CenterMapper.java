package com.demo.multisport.mapper;

import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.entities.center.RelaxCenter;
import com.demo.multisport.entities.center.SportCenter;


public interface CenterMapper {
    CenterDto sportCenterToCenterDtoExtractRecord(SportCenter sportCenter);
    CenterDto relaxCenterToCenterDtoExtractRecord(RelaxCenter relaxCenter);

}
