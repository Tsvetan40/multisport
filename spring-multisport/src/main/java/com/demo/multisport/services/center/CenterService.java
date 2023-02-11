package com.demo.multisport.services.center;

import com.demo.multisport.dao.CenterRepository;
import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.dto.center.ICenterDto;
import com.demo.multisport.entities.center.Center;
import com.demo.multisport.entities.center.ICenter;
import com.demo.multisport.entities.center.RelaxCenter;
import com.demo.multisport.entities.center.SportCenter;
import com.demo.multisport.exceptions.CenterNotFoundException;
import com.demo.multisport.mapper.AdminCenterMapper;
import com.demo.multisport.mapper.CenterMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CenterService {
    private final AdminCenterMapper adminCenterMapper;
    private final CenterMapper centerMapper;
    private final CenterRepository centerRepository;

    public CenterService(@Qualifier("adminCenterMapper")AdminCenterMapper adminCenterMapper,
                         @Qualifier("centerMapper") CenterMapper centerMapper,
                         CenterRepository centerRepository) {
        this.adminCenterMapper = adminCenterMapper;
        this.centerMapper = centerMapper;
        this.centerRepository = centerRepository;
    }

    public void addCenterAdmin(CenterDto centerDto) {
        ICenter center = adminCenterMapper.centerDtoToCenterCreateRecord(centerDto);

        if (center instanceof SportCenter) {
            this.saveCenterAdmin((SportCenter)center);
            return;
        }

        this.saveCenterAdmin((RelaxCenter) center);
    }

    public void deleteCenterAdmin(String address) {
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

    public Optional<CenterDto> centerToCenterDto(Long id) {
        Center center = centerRepository.findById(id)
                                        .orElseThrow(() -> new CenterNotFoundException("Center with id" + id + " not found"));

        return Optional.of((CenterDto) centerMapper.centerToCenterDtoExtractRecord(center));
    }

    public Set<ICenterDto> getAllSportCenters() {
        return centerRepository.getAllSportCenters()
                .stream()
                .map(centerMapper::centerToCenterDtoExtractRecord)
                .collect(Collectors.toSet());
    }

    public Set<ICenterDto> getAlRelaxCenters() {
        return centerRepository.getAllRelaxCenters()
                .stream()
                .map(centerMapper::centerToCenterDtoExtractRecord)
                .collect(Collectors.toSet());
    }
}
