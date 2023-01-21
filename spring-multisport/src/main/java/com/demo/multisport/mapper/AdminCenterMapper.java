package com.demo.multisport.mapper;

import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.entities.center.RelaxCenter;
import com.demo.multisport.entities.center.SportCenter;

public interface AdminCenterMapper extends CenterMapper{

    SportCenter centerDtoToSportCenterCreateRecord(CenterDto centerDto);
    RelaxCenter centerDtoToRelaxCenterCreateRecord(CenterDto centerDto);
}
