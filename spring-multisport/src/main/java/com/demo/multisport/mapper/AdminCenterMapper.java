package com.demo.multisport.mapper;

import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.entities.center.RelaxCenter;
import com.demo.multisport.entities.center.SportCenter;

public interface AdminCenterMapper {
    CenterDto sportCenterToCenterDtoExtractRecord(SportCenter sportCenter);
    CenterDto relaxCenterToCenterDtoExtractRecord(RelaxCenter relaxCenter);

    SportCenter centerDtoToSportCenterCreateRecord(CenterDto centerDto);
    RelaxCenter centerDtoToRelaxCenterCreateRecord(CenterDto centerDto);
}
