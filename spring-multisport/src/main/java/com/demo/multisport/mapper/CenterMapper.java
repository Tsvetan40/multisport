package com.demo.multisport.mapper;

import com.demo.multisport.dto.center.ICenterDto;
import com.demo.multisport.entities.center.Center;


public interface CenterMapper {
    ICenterDto centerToCenterDtoExtractRecord(Center center);
}
