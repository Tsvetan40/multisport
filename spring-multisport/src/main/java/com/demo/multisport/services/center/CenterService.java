package com.demo.multisport.services.center;

import com.demo.multisport.dao.CenterRepository;
import com.demo.multisport.entities.center.Center;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CenterService {

    private final CenterRepository centerRepository;


    @Autowired
    public CenterService(CenterRepository centerRepository) {
        this.centerRepository = centerRepository;
    }

    public Center getCenterByAddressAndType(String address, String type) {
        return centerRepository.getCenterByAddressAndType(address, type).get();
    }
}
