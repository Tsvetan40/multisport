package com.demo.multisport.services.center;

import com.demo.multisport.dao.CenterRepository;
import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.entities.center.Center;
import com.demo.multisport.entities.center.RelaxCenter;
import com.demo.multisport.entities.center.SportCenter;
import com.demo.multisport.exceptions.CenterNotFoundException;
import com.demo.multisport.mapper.AdminCenterMapper;
import com.demo.multisport.mapper.impl.CenterMapperImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CenterService {
    private final AdminCenterMapper adminCenterMapper;
    private final CenterRepository centerRepository;
    private final CenterMapperImpl centerMapperImpl;

    public void addSportCenterAdmin(CenterDto centerDto) {
        SportCenter newSportCenter =  adminCenterMapper.centerDtoToSportCenterCreateRecord(centerDto);
        this.saveCenterAdmin(newSportCenter);
    }

    public void addRelaxCenterAdmin(CenterDto centerDto) {
        RelaxCenter newRelaxCenter =  adminCenterMapper.centerDtoToRelaxCenterCreateRecord(centerDto);
        this.saveCenterAdmin(newRelaxCenter);
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

    public Optional<CenterDto> sportCenterToCenterDto(Long id ) {
        Optional<SportCenter> sportCenter = centerRepository.getSportCenterByAddress(id);
        if (sportCenter.isEmpty()) {
            throw new CenterNotFoundException("Center with id" + id + " not found");
        }

        return Optional.of(centerMapperImpl.sportCenterToCenterDtoExtractRecord(sportCenter.get()));
    }

    public Optional<CenterDto> relaxCenterToCenterDto(Long id) {
        Optional<RelaxCenter> relaxCenter = centerRepository.getRelaxCenterByAddress(id);
        if (relaxCenter.isEmpty()) {
            throw new CenterNotFoundException("Center with id" + id + " not found");
        }

        return Optional.of(centerMapperImpl.relaxCenterToCenterDtoExtractRecord(relaxCenter.get()));
    }

    public Set<CenterDto> getAllSportCenters() {
        return centerRepository.getAllSportCenters()
                .stream()
                .map(centerMapperImpl::sportCenterToCenterDtoExtractRecord)
                .collect(Collectors.toSet());
    }

    public Set<CenterDto> getAlRelaxCenters() {
        return centerRepository.getAllRelaxCenters()
                .stream()
                .map(centerMapperImpl::relaxCenterToCenterDtoExtractRecord)
                .collect(Collectors.toSet());
    }
}
