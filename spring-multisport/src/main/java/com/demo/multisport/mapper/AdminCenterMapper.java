package com.demo.multisport.mapper;

import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.entities.center.ICenter;

public interface AdminCenterMapper extends CenterMapper {
    ICenter centerDtoToCenterCreateRecord(CenterDto centerDto);
}
