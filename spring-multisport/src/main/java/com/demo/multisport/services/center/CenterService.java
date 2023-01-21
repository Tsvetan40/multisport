package com.demo.multisport.services.center;

import com.demo.multisport.dao.CenterRepository;
import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.entities.center.Center;
import com.demo.multisport.entities.center.RelaxCenter;
import com.demo.multisport.entities.center.SportCenter;
import com.demo.multisport.exceptions.CenterNotFoundException;
import com.demo.multisport.mapper.AdminCenterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CenterService {
    private final AdminCenterMapper adminCenterMapper;
    private final CenterRepository centerRepository;

    public CenterDto getCenterDtoFromSportCenterAdmin(SportCenter sportCenter) {
        return adminCenterMapper.sportCenterToCenterDtoExtractRecord(sportCenter);
    }

    public CenterDto getCenterDtoFromRelaxCenterAdmin(RelaxCenter relaxCenter) {
        return adminCenterMapper.relaxCenterToCenterDtoExtractRecord(relaxCenter);
    }

    public void addSportCenterAdmin(CenterDto centerDto) {
        SportCenter newSportCenter =  adminCenterMapper.centerDtoToSportCenterCreateRecord(centerDto);
        this.saveCenterAdmin(newSportCenter);
    }

    public void addRelaxCenterAdmin(CenterDto centerDto) {
        RelaxCenter newRelaxCenter =  adminCenterMapper.centerDtoToRelaxCenterCreateRecord(centerDto);
        this.saveCenterAdmin(newRelaxCenter);
    }

    public void deleteCenter(String address) {
        Optional<Center> center = centerRepository.deleteCenterByAddress(address);
        if (center.isEmpty()) {
            throw new CenterNotFoundException("Center with " + address + " not found");
        }
    }

    private void saveCenterAdmin(Center center) {
        centerRepository.save(center);
    }

    public long countCentersByAddress(String address) {
        return centerRepository.countCentersByAddress(address);
    }
}
